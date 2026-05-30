package br.com.faculdadeinovatech.inovatech2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.faculdadeinovatech.inovatech2.entity.Usuario;
import br.com.faculdadeinovatech.inovatech2.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        String loginNormalizado = login == null ? "" : login.trim().toLowerCase();

        Usuario usuario = usuarioRepository.findByLoginUsuario(loginNormalizado)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        return new UserDetailsImpl(usuario);
    }

}
