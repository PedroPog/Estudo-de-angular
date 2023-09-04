package com.api.pix.service;

import com.api.pix.model.Empresa;
import com.api.pix.model.Usuario;
import com.api.pix.repository.EmpresaRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listartodos(  ) {
        return empresaRepository.findAll();
    }

    public ResponseEntity<Empresa> findById(@NotNull Long id){
        return empresaRepository.findById(id)
                .map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }
}
