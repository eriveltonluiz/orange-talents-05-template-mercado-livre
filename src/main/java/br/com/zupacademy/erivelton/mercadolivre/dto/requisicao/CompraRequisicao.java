package br.com.zupacademy.erivelton.mercadolivre.dto.requisicao;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Compra;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.enums.TipoPagamento;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.ExisteId;

public class CompraRequisicao {
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@NotNull
	@ExisteId(classe = Produto.class)
	private Long produtoId;
	
	@NotNull
	private TipoPagamento tipoPagamento;
	
	public CompraRequisicao(@Positive @NotNull Integer quantidade, @NotNull Long produtoId,
			@NotBlank TipoPagamento tipoPagamento) {
		this.quantidade = quantidade;
		this.produtoId = produtoId;
		this.tipoPagamento = tipoPagamento;
	}

	public Long getProdutoId() {
		return produtoId;
	}
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	
	public Compra paraEntidade(Produto produto, Usuario comprador) {
		BigDecimal valorTotal = produto.getValor().multiply(new BigDecimal(quantidade));
		return new Compra(quantidade, valorTotal, tipoPagamento, produto, comprador);
	}
}
