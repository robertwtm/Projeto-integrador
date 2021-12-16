package com.generation.AppProjetoIntegrador.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRegisterDTO {

	private @NotBlank @Size(min = 2, max = 25) String nameCompleto;
	private @NotBlank @Email String email;
	private @NotBlank @Size(min = 2, max = 25) String senha;

	public UsuarioRegisterDTO() {
	}

	public UsuarioRegisterDTO(String nameCompleto, String email, String senha) {
		this.nameCompleto = nameCompleto;
		this.email = email;
		this.senha = senha;
	}

	public String getNameCompleto() {
		return nameCompleto;
	}

	public void setNameCompleto(String nameCompleto) {
		this.nameCompleto = nameCompleto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
