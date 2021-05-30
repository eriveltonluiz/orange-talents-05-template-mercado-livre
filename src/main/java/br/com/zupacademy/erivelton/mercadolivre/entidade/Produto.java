package br.com.zupacademy.erivelton.mercadolivre.entidade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.EmailPergunta;
import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.PerguntaRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.dto.resposta.CaracteristicasRespostaDTO;
import br.com.zupacademy.erivelton.mercadolivre.dto.resposta.OpiniaoRespostaDTO;
import br.com.zupacademy.erivelton.mercadolivre.dto.resposta.PerguntaDTO;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@PositiveOrZero
	private Integer quantidadeDisponivel;
	
	@Lob
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private Set<Caracteristica> caracteristicas;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens;
	
	@OneToMany(mappedBy = "produto")
	private List<Opiniao> opinioes = new ArrayList<Opiniao>();
	
	@OneToMany(mappedBy = "produto")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;
	
	@SuppressWarnings("unused")
	private OffsetDateTime instanteCadastro = OffsetDateTime.now();

	@Deprecated
	public Produto() {
	}
	
	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidadeDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@Size(min = 3) Set<Caracteristica> caracteristicas, Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.caracteristicas = caracteristicas;
		this.categoria = categoria;
		this.usuario = usuario;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public List<String> getLinksImagens() {
		return imagens.stream()
				.map(imagem -> imagem.getLinkImagem())
				.collect(Collectors.toList());
	}
	
	public List<CaracteristicasRespostaDTO> getCaracteristicasDTO() {
		return caracteristicas.stream()
				.map(c -> new CaracteristicasRespostaDTO(c.getNome(), c.getDescricao()))
				.collect(Collectors.toList());
	}
	
	public List<OpiniaoRespostaDTO> getOpinioesDTO() {
		return opinioes.stream()
				.map(prod -> new OpiniaoRespostaDTO(prod.getTitulo(), prod.getDescricao()))
				.collect(Collectors.toList());
	}
	
	public int quantidadeNotas() {
		return opinioes.size();
	}
	
	public BigDecimal mediaNotasOpinioes() {
		Double media = opinioes.stream()
				.mapToDouble(o -> o.getNota().doubleValue())
				.average()
				.getAsDouble();
		
		return new BigDecimal(media).setScale(2, RoundingMode.HALF_EVEN);
	}
	
	public List<PerguntaDTO> getPerguntasDTO() {
		return perguntas.stream()
				.map(pergunta -> new PerguntaDTO(pergunta.getTitulo(), pergunta.getUsuarioLogin()))
				.collect(Collectors.toList());
	}
	
	public void adicionarCaracteristicasAoTodo(Set<Caracteristica> caracteristicas) {
		this.caracteristicas.addAll(caracteristicas);
	}
	
	public void adicionarLinksImagens(Set<MultipartFile> imagensRequisicao) {
		this.imagens.addAll(imagensRequisicao.stream()
				.map(imagem -> new Imagem("https://www.produtos.com/" + imagem.getOriginalFilename(), this))
				.collect(Collectors.toSet()));
	}
	
	public EmailPergunta dadosEmail(Usuario usuarioLogado, PerguntaRequisicao pergunta) {
		return new EmailPergunta(pergunta.getTitulo(), usuarioLogado.getUsername(), this.usuario.getUsername());
	}

	public boolean verificarDono(Usuario usuarioLogado) {
		return this.usuario.equals(usuarioLogado);
	}

	@Override
	public String toString() {
		return "Produto [nome=" + nome + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
