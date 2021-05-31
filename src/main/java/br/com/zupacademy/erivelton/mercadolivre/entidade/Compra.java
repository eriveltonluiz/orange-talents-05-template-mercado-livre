package br.com.zupacademy.erivelton.mercadolivre.entidade;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.erivelton.mercadolivre.enums.StatusCompra;
import br.com.zupacademy.erivelton.mercadolivre.enums.TipoPagamento;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive
	@NotNull
	private Integer quantidade;
	
	@SuppressWarnings("unused")
	private BigDecimal preco;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagemento;
	
	@Enumerated(EnumType.STRING)
	private StatusCompra status = StatusCompra.INICIADA;
	
	@ManyToOne
	private Produto produto;
	
	@ManyToOne
	private Usuario comprador;

	@Deprecated
	public Compra() {
	}
	
	public Compra(@Positive @NotNull Integer quantidade, BigDecimal preco, TipoPagamento tipoPagemento,
			Produto produto, Usuario comprador) {
		this.quantidade = quantidade;
		this.preco = preco;
		this.tipoPagemento = tipoPagemento;
		this.produto = produto;
		this.comprador = comprador;
	}
	
}
