package com.generation.AppProjetoIntegrador.services;

import java.nio.charset.Charset;
import java.util.Optional;
import javax.validation.Valid;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.generation.AppProjetoIntegrador.model.Usuario;
import com.generation.AppProjetoIntegrador.model.dto.UsuarioCredentialsDTO;
import com.generation.AppProjetoIntegrador.model.dto.UsuarioLoginDTO;
import com.generation.AppProjetoIntegrador.model.dto.UsuarioRegisterDTO;
import com.generation.AppProjetoIntegrador.repository.UsuarioRepository;

@Service
public class UsuarioServices {

	private @Autowired UsuarioRepository repository;
	private UsuarioCredentialsDTO credentials;
	private Usuario user;

	private static String encryptPassword(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(senha);
	}

	private static String generatorToken(String email, String senha) {
		String structure = email + ":" + senha; // gustaco@email.com:134652
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII"))); // Z3VzdGFjb0BlbWFpbC5jb206MTM0NjUy
		return new String(structureBase64);
	}

	private static String generatorTokenBasic(String email, String senha) {
		String structure = email + ":" + senha; // gustaco@email.com:134652
		byte[] structureBase64 = Base64.encodeBase64(structure.getBytes(Charset.forName("US-ASCII"))); // Z3VzdGFjb0BlbWFpbC5jb206MTM0NjUy
		return "Basic " + new String(structureBase64);
	}

	public ResponseEntity<Usuario> registerUser(@Valid UsuarioRegisterDTO newUser) {
		Optional<Usuario> optional = repository.findByEmail(newUser.getEmail());

		if (optional.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Usuario ja existente, cadastre com outro email!");
		} else {
			user = new Usuario();
			user.setNomeCompleto(newUser.getNameCompleto());
			user.setEmail(newUser.getEmail());
			user.setToken(generatorToken(newUser.getEmail(), newUser.getSenha()));
			user.setSenha(encryptPassword(newUser.getSenha()));
			return ResponseEntity.status(201).body(repository.save(user));
		}

	}

	public ResponseEntity<UsuarioCredentialsDTO> getCredentials(@Valid UsuarioLoginDTO userDto) {
		return repository.findByEmail(userDto.getEmail()).map(resp -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

			if (encoder.matches(userDto.getSenha(), resp.getSenha())) {

				credentials = new UsuarioCredentialsDTO();
				credentials.setIdUsuario(resp.getIdUsuario());
				credentials.setEmail(resp.getEmail());
				credentials.setToken(resp.getToken());
				credentials.setTokenBasic(generatorTokenBasic(userDto.getEmail(), userDto.getSenha()));

				return ResponseEntity.status(200).body(credentials);
			} else {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta!");
			}

		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email incorreto!"));

	}

}
