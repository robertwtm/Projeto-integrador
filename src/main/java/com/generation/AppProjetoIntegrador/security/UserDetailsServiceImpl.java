package com.generation.AppProjetoIntegrador.security;

import com.generation.AppProjetoIntegrador.model.Usuario;
import com.generation.AppProjetoIntegrador.repository.UsuarioRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> optional = userRepository.findByUsuario(userName);
		if (optional.isPresent()) {
			return new UserDetailsImpl(optional.get());
		} else {
			throw new UsernameNotFoundException("Usuario não existe");
		}
	}
}