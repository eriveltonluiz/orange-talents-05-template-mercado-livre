package br.com.zupacademy.erivelton.mercadolivre.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private BigDecimal nota;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String descricao;

	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Produto produto;

	@Deprecated
	public Opiniao() {
	}
	
	public Opiniao(@NotNull @DecimalMin("1") @DecimalMax("5") BigDecimal nota, @NotBlank String titulo,
			@NotBlank String descricao, Usuario usuario, Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Opiniao [titulo=" + titulo + ", descricao=" + descricao + "]";
	}
	
}
