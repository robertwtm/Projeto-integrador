package com.generation.AppProjetoIntegrador.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_tema")
public class Tema {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_tema;
	
	@NotNull
 	@Size(min = 5, max = 45)
	@NotBlank
	private String materia;
	
 	@Size(min = 5, max = 100)
	private String descricao;
 	
 	@Size(min = 5, max = 100)
	private String url_imagem;

}
