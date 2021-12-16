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
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> optional = userRepository.findByEmail(email);
		optional.orElseThrow(() -> new UsernameNotFoundException(email + " not found."));

		return optional.map(UserDetailsImpl::new).get();
	}
}