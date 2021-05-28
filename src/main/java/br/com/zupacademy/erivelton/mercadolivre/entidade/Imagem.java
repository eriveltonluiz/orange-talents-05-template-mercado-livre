package br.com.zupacademy.erivelton.mercadolivre.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String linkImagem;
	
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Imagem() {
	}
	
	public Imagem(@NotBlank String linkImagem, Produto produto) {
		this.linkImagem = linkImagem;
		this.produto = produto;
	}
	
}
