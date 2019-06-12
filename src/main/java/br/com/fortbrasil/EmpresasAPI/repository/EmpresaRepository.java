package br.com.fortbrasil.EmpresasAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fortbrasil.EmpresasAPI.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa,Long>{
	
	List<Empresa> findByEndereco_LogradouroIgnoreCaseContaining(String endereco);
	
	List<Empresa> findByNomeIgnoreCaseContaining(String nome);
	
	List<Empresa> findByEndereco_LogradouroIgnoreCaseContainingAndNomeIgnoreCaseContaining(String cpf, String endereco);

}
