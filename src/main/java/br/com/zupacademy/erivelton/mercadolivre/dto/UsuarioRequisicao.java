package br.com.zupacademy.erivelton.mercadolivre.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.UniqueValue;

public class UsuarioRequisicao {

	@Email
	@NotBlank
	@UniqueValue(classe = Usuario.class, atributo = "login")
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
