package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.EmailPergunta;
import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.PerguntaRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Pergunta;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;

@RestController
public class PerguntaUsuarioControle {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/perguntas")
	@Transactional
	public String salvar(@Valid @RequestBody PerguntaRequisicao requisicao, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = em.find(Produto.class, requisicao.getProdutoId());
		Pergunta pergunta = new Pergunta(requisicao.getTitulo(), usuarioLogado, produto);
		
		EmailPergunta emailPergunta = produto.dadosEmail(usuarioLogado, requisicao);
		System.out.println(emailPergunta.toString());
		
		em.persist(pergunta);
		
		return pergunta.toString();
	}
}
