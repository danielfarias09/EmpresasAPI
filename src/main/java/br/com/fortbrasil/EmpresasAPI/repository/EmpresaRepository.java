package br.com.fortbrasil.EmpresasAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fortbrasil.EmpresasAPI.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long>{

}
