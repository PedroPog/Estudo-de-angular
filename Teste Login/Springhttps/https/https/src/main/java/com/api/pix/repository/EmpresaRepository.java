package com.api.pix.repository;

import com.api.pix.model.Empresa;
import com.api.pix.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
