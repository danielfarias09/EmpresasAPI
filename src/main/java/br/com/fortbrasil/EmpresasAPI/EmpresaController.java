package br.com.fortbrasil.EmpresasAPI;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.fortbrasil.EmpresasAPI.model.Empresa;
import br.com.fortbrasil.EmpresasAPI.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Empresa> list(@Param("nome") String nome, @Param("endereco") String endereco) {
		List<Empresa> empresas = getEmpresa(nome,endereco);
		return empresas;
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> create(@RequestBody Empresa empresa) {
		empresaRepository.save(empresa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<HttpStatus> edit(@PathVariable("id") Long id, @RequestBody Empresa empresa) {
		empresaRepository.save(empresa);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:4200")
	public Optional<Empresa> get(@PathVariable("id") Long id) {
		return empresaRepository.findById(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
		empresaRepository.deleteById(id);
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
	
	private List<Empresa> getEmpresa(String nome,String endereco){
		if(!nome.equals("") && endereco.equals("")){
			return empresaRepository.findByNomeIgnoreCaseContaining(nome);
		}else if(nome.equals("") && !endereco.equals("")){
			return empresaRepository.findByEndereco_LogradouroIgnoreCaseContaining(endereco);
		}else if(!nome.equals("") && !endereco.equals("")){
			return empresaRepository.findByEndereco_LogradouroIgnoreCaseContainingAndNomeIgnoreCaseContaining(endereco, nome);
		}else{
			return empresaRepository.findAll();
		}
}
}
