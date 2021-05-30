package br.com.zupacademy.erivelton.mercadolivre.entidade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Caracteristica {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Lob
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Caracteristica() {
		// TODO Auto-generated constructor stub
	}
	
	public Caracteristica(@NotBlank String nome, @NotBlank @Size(max = 1000) String descricao, Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
