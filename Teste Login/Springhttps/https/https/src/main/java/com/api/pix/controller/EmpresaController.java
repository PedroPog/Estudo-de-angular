package com.api.pix.controller;

import com.api.pix.ExceptionApiPix;
import com.api.pix.model.Empresa;
import com.api.pix.model.Usuario;
import com.api.pix.repository.EmpresaRepository;
import com.api.pix.service.EmpresaService;
import com.api.pix.service.UsuarioService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;
    private EmpresaRepository empresaRepository;

    @GetMapping(value="salvarempresa" )
    private Empresa helloWorld(@RequestBody Empresa empresa){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String nome;

        if (principal instanceof UserDetails) {
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }
        System.out.println("nome---"+nome);
System.out.println(""+ empresa );
//return usuario;
return empresaService.save(empresa);
    }


    @GetMapping(value="listarempresa" )
    private List<Empresa> listarempresa() throws ExceptionApiPix {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String nome;

        if (principal instanceof UserDetails) {
            nome = ((UserDetails)principal).getUsername();
        } else {
            nome = principal.toString();
        }
        System.out.println("nome---"+nome);
        //System.out.println(""+ empresa );
        int i =05;
        if (i==0) {
            throw new ExceptionApiPix("Erro ao listar");
        }

        return empresaService.listartodos();
    }
    @GetMapping(value="{id}")
    public ResponseEntity<Empresa> findById(@NotNull Long id){
        return empresaService.findById(id);
    }


}
