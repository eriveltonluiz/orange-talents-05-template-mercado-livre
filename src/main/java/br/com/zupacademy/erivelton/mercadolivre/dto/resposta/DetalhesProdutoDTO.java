package br.com.zupacademy.erivelton.mercadolivre.dto.resposta;

import java.math.BigDecimal;
import java.util.List;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;

public class DetalhesProdutoDTO {

	private List<String> linksImagens;
	private String produto;
	private BigDecimal preco;
	private List<CaracteristicasRespostaDTO> caracteristicas;
	private String descricao;
	private BigDecimal mediaNotas;
	private Integer totalNotas;
	private List<OpiniaoRespostaDTO> opinioes;
	private List<PerguntaDTO> perguntas;

	public DetalhesProdutoDTO(Produto produto) {
		this.linksImagens = produto.getLinksImagens();
		this.produto = produto.getNome();
		this.preco = produto.getValor();
		this.caracteristicas = produto.getCaracteristicasDTO();
		this.descricao = produto.getDescricao();
		this.mediaNotas = produto.mediaNotasOpinioes();
		this.totalNotas = produto.quantidadeNotas();
		this.opinioes = produto.getOpinioesDTO();
		this.perguntas = produto.getPerguntasDTO();
	}

	public List<String> getLinksImagens() {
		return linksImagens;
	}

	public String getProduto() {
		return produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}
	
	public List<CaracteristicasRespostaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public BigDecimal getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public List<OpiniaoRespostaDTO> getOpinioes() {
		return opinioes;
	}

	public List<PerguntaDTO> getPerguntas() {
		return perguntas;
	}

}
