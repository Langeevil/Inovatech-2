package br.com.faculdadeinovatech.inovatech2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.faculdadeinovatech.inovatech2.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLoginUsuario(String loginUsuario);

    Optional<Usuario> findByTokenResetSenha(String tokenResetSenha);

}
