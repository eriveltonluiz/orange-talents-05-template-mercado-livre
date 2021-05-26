package br.com.zupacademy.erivelton.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequisicao {

	@Email
	@NotBlank
	private String login;
	
	@Size(min = 6)
	@NotBlank
	private String senha;
	
	public String getLogin() {
		return login;
	}
	
	public String getSenha() {
		return senha;
	}
}
