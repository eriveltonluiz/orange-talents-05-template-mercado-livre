package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.OpiniaoRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Opiniao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;

@RestController
public class OpiniaoControle {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/opinioes")
	@Transactional
	public String salvar(@Valid @RequestBody OpiniaoRequisicao requisicao, @AuthenticationPrincipal Usuario usuarioLogado) {
		Produto produto = em.find(Produto.class, requisicao.getProdutoId());
		Opiniao opiniao = requisicao.paraEntidade(usuarioLogado, produto);
		
		em.persist(opiniao);
		
		return opiniao.toString();
	}
	
}
