package br.com.zupacademy.erivelton.mercadolivre.dto.requisicao;

import javax.validation.constraints.NotBlank;

public class CaracteristicaRequisicao {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public CaracteristicaRequisicao(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
