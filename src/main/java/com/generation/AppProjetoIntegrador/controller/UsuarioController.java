package com.generation.AppProjetoIntegrador.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.generation.AppProjetoIntegrador.model.UserLogin;
import com.generation.AppProjetoIntegrador.model.Usuario;
import com.generation.AppProjetoIntegrador.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findByIdUsuario(@PathVariable Long idUsuario) {
		return repository.findById(idUsuario).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(usuario));
	}

	@PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody UserLogin newUser){
    	return services.registerUser(newUser);
    }

	@PutMapping
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(usuario));
	}
    
    @PutMapping("/credentials")
    public ResponseEntity<Usuario> credentials(@Valid @RequestBody UserLogin user){
    	return services.getCredentials(user);
    }
	
	@DeleteMapping("{id}")
	public void deleteUsuario(@PathVariable Long idUsuario) {
		repository.deleteById(idUsuario);
	}

}
