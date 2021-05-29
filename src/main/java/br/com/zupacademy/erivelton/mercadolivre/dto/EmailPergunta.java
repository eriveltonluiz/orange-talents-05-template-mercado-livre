package br.com.zupacademy.erivelton.mercadolivre.dto;

public class EmailPergunta {
	
	private String remetente;
	private String titulo;
	private String destinatario;

	public EmailPergunta(String titulo, String remetente, String destinatario) {
		this.titulo = titulo;
		this.remetente = remetente;
		this.destinatario = destinatario;
	}

	@Override
	public String toString() {
		return "Remetente: " + remetente + "\nTítulo: " + titulo + "\nDestinatário: " + destinatario;
	}
	
}
