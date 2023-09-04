package com.api.pix.controller;

import com.api.pix.model.Empresa;
import com.api.pix.model.Usuario;
import com.api.pix.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;


@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value="salvarusuario" )
    private Usuario helloWorld(@RequestBody Usuario usuario){
        String senhatemp = new BCryptPasswordEncoder().encode(usuario.getSenha());
        usuario.setSenha(senhatemp);
System.out.println(""+ usuario );
//return usuario;
return usuarioService.save(usuario);
    }

    @GetMapping(value="listarusuario" )
    private List<Usuario>  listarusuario(){


//return usuario;
        return usuarioService.listartodos();
    }


}
