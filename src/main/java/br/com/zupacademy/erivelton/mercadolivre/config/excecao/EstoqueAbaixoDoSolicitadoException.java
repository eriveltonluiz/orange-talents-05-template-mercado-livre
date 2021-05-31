package br.com.zupacademy.erivelton.mercadolivre.config.excecao;

public class EstoqueAbaixoDoSolicitadoException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public EstoqueAbaixoDoSolicitadoException() {
		super("Não há estoque suficiente para essa quantidade");
	}
}
