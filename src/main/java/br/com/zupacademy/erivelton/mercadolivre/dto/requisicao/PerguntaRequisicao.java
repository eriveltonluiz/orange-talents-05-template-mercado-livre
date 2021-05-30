package br.com.zupacademy.erivelton.mercadolivre.dto.requisicao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.ExisteId;

public class PerguntaRequisicao {

	@NotBlank
	private String titulo;
	
	@NotNull
	@ExisteId(classe = Produto.class)
	private Long produtoId;

	public PerguntaRequisicao(@NotBlank String titulo, @NotNull Long produtoId) {
		this.titulo = titulo;
		this.produtoId = produtoId;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public Long getProdutoId() {
		return produtoId;
	}
	
}
