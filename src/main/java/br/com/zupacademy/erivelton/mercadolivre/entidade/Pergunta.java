package br.com.zupacademy.erivelton.mercadolivre.entidade;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String titulo;
	
	@SuppressWarnings("unused")
	private OffsetDateTime instantePergunta = OffsetDateTime.now();
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;

	public Pergunta(@NotBlank String titulo, Usuario usuario, Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Pergunta [titulo=" + titulo + "]";
	}
	
}
