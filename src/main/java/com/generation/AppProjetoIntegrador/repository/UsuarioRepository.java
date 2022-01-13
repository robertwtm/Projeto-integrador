package com.generation.AppProjetoIntegrador.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.generation.AppProjetoIntegrador.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Optional<Usuario> findByEmail(String email);

	public Optional<Usuario> findByToken(String token);

	public List<Usuario> findAllByNomeCompletoContainingIgnoreCase(String nomeCompleto);
}
