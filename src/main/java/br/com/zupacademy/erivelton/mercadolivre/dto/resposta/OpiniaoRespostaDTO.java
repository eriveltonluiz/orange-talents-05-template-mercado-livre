package br.com.zupacademy.erivelton.mercadolivre.dto.resposta;

public class OpiniaoRespostaDTO {
	
	private String titulo;

	private String descricao;
	
	public OpiniaoRespostaDTO(String titulo, String descricao) {
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
