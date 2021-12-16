package com.generation.AppProjetoIntegrador.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.AppProjetoIntegrador.model.Tema;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long> {

	public List<Tema> findAllByMateriaContainingIgnoreCase(String materia);

}
