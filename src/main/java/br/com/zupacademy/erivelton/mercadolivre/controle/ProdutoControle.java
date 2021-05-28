package br.com.zupacademy.erivelton.mercadolivre.controle;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.erivelton.mercadolivre.dto.ImagemRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.dto.ProdutoRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Categoria;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.ExisteId;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoControle {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public String salvar(@Valid @RequestBody ProdutoRequisicao produtoRequisicao, @AuthenticationPrincipal Usuario usuario) {
		Categoria categoria = em.find(Categoria.class, produtoRequisicao.getCategoriaId());
		Produto produto = produtoRequisicao.paraEntidade(categoria, usuario);
		
		em.persist(produto);
		
		return produto.toString();
	}
	 
	@PostMapping(value = "/imagens/{id}")
	@Transactional
	@ExisteId(classe = Produto.class)
	public void imagem(@Valid ImagemRequisicao requisicao, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioLogado) throws IOException {
		Produto produto = em.find(Produto.class, id);
		
		if(produto == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID não encontrado");
		}
		
		produto.adicionarLinksImagens(requisicao.getImagens());
		
		if(!produto.verificarDono(usuarioLogado)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Usuário não tem acesso para adicionar imagens para este produto");
		}
		
		em.merge(produto);
	}
	
}
