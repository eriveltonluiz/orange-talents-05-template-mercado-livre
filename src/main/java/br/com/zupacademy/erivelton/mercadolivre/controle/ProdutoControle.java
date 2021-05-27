package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.dto.ProdutoRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Categoria;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;

@RestController
public class ProdutoControle {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/produtos")
	@Transactional
	public String salvar(@Valid @RequestBody ProdutoRequisicao produtoRequisicao, @AuthenticationPrincipal Usuario usuario) {
		Categoria categoria = em.find(Categoria.class, produtoRequisicao.getCategoriaId());
		Produto produto = produtoRequisicao.paraEntidade(categoria, usuario);
		
		em.persist(produto);
		
		return produto.toString();
	}
	
}
