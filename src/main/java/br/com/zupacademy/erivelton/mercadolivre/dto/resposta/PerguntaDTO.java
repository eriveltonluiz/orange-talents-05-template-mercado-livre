package br.com.zupacademy.erivelton.mercadolivre.dto.resposta;

public class PerguntaDTO {
	
	private String pergunta;
	
	private String usuario;

	public PerguntaDTO(String pergunta, String usuario) {
		this.pergunta = pergunta;
		this.usuario = usuario;
	}

	public String getPergunta() {
		return pergunta;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
}
