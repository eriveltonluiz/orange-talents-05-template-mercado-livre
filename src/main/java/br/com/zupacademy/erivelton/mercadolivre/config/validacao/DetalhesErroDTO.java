package br.com.zupacademy.erivelton.mercadolivre.config.validacao;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetalhesErroDTO {
	
	private String campo;
	private String mensagem;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm.ss")
	private OffsetDateTime momentoErro;

	public DetalhesErroDTO(String campo, String mensagem, OffsetDateTime momentoErro) {
		this.campo = campo;
		this.mensagem = mensagem;
		this.momentoErro = momentoErro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public OffsetDateTime getMomentoErro() {
		return momentoErro;
	}

}
