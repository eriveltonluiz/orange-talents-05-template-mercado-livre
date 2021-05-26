package br.com.zupacademy.erivelton.mercadolivre.entidade;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotBlank
	private String login;

	@Size(min = 6)
	@NotBlank
	private String senha;

	@PastOrPresent
	private OffsetDateTime instanteCadastro = OffsetDateTime.now();

	public Usuario(@Email @NotBlank String login, @Size(min = 6) @NotBlank String senha) {
		this.login = login;
		this.senha = senha;
	}

}
