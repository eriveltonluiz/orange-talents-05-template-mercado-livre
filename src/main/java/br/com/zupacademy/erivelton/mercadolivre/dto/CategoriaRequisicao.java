package br.com.zupacademy.erivelton.mercadolivre.dto;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Categoria;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.UniqueValue;

public class CategoriaRequisicao {
	
	@NotBlank
	@UniqueValue(classe = Categoria.class, atributo = "nome")
	private String nome;
	
	private Long categoriaMaeId;
	
	public String getNome() {
		return nome;
	}
	
	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}
}
