package br.com.zupacademy.erivelton.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class UsuarioRequisicaoLogin {
	
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

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.login, this.senha);
	}
}
