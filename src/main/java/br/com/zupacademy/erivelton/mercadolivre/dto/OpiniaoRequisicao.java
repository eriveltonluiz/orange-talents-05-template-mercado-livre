package br.com.zupacademy.erivelton.mercadolivre.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Opiniao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.ExisteId;

public class OpiniaoRequisicao {

	@NotNull
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private BigDecimal nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@ExisteId(classe = Produto.class)
	@NotNull
	private Long produtoId;

	public OpiniaoRequisicao(@DecimalMin("1") @DecimalMax("5") BigDecimal nota, @NotBlank String titulo,
			@NotBlank String descricao, @NotBlank Long produtoId) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produtoId = produtoId;
	}

	public Long getProdutoId() {
		return produtoId;
	}

	public Opiniao paraEntidade(Usuario usuario, Produto produto) {
		return new Opiniao(nota, titulo, descricao, usuario, produto);
	}
}
