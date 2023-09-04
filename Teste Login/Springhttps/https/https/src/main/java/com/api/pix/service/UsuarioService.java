package com.api.pix.service;

import com.api.pix.model.Usuario;
import com.api.pix.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public List<Usuario> listartodos(  ) {
        return usuarioRepository.findAll();
    }


}
