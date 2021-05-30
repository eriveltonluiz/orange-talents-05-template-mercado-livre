package br.com.zupacademy.erivelton.mercadolivre.dto.resposta;

public class CaracteristicasRespostaDTO {

	private String nome;
	
	private String descricao;

	public CaracteristicasRespostaDTO(String nome, String descricao) {
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
