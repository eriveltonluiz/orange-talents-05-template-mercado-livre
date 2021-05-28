package br.com.zupacademy.erivelton.mercadolivre.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Caracteristica;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Categoria;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.ExisteId;

public class ProdutoRequisicao {

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidadeDisponivel;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@Size(min = 3)
	private Set<CaracteristicaRequisicao> caracteristicas;

	@ExisteId(classe = Categoria.class)
	private Long categoriaId;

	public ProdutoRequisicao(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidadeDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@Size(min = 3) Set<CaracteristicaRequisicao> caracteristicas, @Size(min = 3) Long categoriaId) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.categoriaId = categoriaId;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}
	
	public Produto paraEntidade(Categoria categoria, Usuario usuario) {
		Set<Caracteristica> caracteristicasEntidade = new HashSet<Caracteristica>();
		Produto produto = new Produto(nome, valor, quantidadeDisponivel, descricao, caracteristicasEntidade, categoria, usuario);

		caracteristicasEntidade = caracteristicas.stream()
				.map(caracteristica -> new Caracteristica(caracteristica.getNome(), caracteristica.getDescricao(), produto))
				.collect(Collectors.toSet());

		produto.adicionarCaracteristicasAoTodo(caracteristicasEntidade);
		
		return produto;
	}
}
