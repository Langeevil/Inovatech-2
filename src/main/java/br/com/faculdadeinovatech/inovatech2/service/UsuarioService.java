package br.com.faculdadeinovatech.inovatech2.service;

import java.time.LocalDateTime;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.entity.Usuario;
import br.com.faculdadeinovatech.inovatech2.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) {
        // Criptografa a senha antes de salvar
        usuario.setSenhaUsuario(passwordEncoder.encode(usuario.getSenhaUsuario()));
        return usuarioRepository.save(usuario);
    }

    public Optional<String> criarTokenResetSenha(String email) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByLoginUsuario(email);

        if (usuarioEncontrado.isEmpty()) {
            return Optional.empty();
        }

        Usuario usuario = usuarioEncontrado.get();
        String token = UUID.randomUUID().toString();

        usuario.setTokenResetSenha(token);
        usuario.setTokenResetSenhaExpiracao(LocalDateTime.now().plusMinutes(5));
        usuarioRepository.save(usuario);

        return Optional.of(token);
    }

    public boolean tokenResetSenhaValido(String token) {
        return buscarUsuarioPorTokenValido(token).isPresent();
    }

    public long segundosRestantesTokenResetSenha(String token) {
        return buscarUsuarioPorTokenValido(token)
                .map(usuario -> Duration.between(LocalDateTime.now(), usuario.getTokenResetSenhaExpiracao()).toSeconds())
                .filter(segundos -> segundos > 0)
                .orElse(0L);
    }

    public boolean alterarSenhaPorToken(String token, String novaSenha) {
        Optional<Usuario> usuarioEncontrado = buscarUsuarioPorTokenValido(token);

        if (usuarioEncontrado.isEmpty()) {
            return false;
        }

        Usuario usuario = usuarioEncontrado.get();
        usuario.setSenhaUsuario(passwordEncoder.encode(novaSenha));
        usuario.setTokenResetSenha(null);
        usuario.setTokenResetSenhaExpiracao(null);
        usuarioRepository.save(usuario);

        return true;
    }

    private Optional<Usuario> buscarUsuarioPorTokenValido(String token) {
        if (token == null || token.isBlank()) {
            return Optional.empty();
        }

        return usuarioRepository.findByTokenResetSenha(token)
                .filter(usuario -> usuario.getTokenResetSenhaExpiracao() != null)
                .filter(usuario -> usuario.getTokenResetSenhaExpiracao().isAfter(LocalDateTime.now()));
    }
    
    
}
