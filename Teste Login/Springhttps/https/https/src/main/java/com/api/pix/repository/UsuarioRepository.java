package com.api.pix.repository;

import com.api.pix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query(value = "select u from Usuario u where u.login = ?1")
    Usuario findUserByLogin(String login);
}
