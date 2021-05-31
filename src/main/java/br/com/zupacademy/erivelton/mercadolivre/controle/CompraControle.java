package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.config.excecao.EstoqueAbaixoDoSolicitadoException;
import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.CompraRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Compra;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.enums.TipoPagamento;

@RestController
public class CompraControle {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/compras")
	@Transactional
	public String comprar(@RequestBody @Valid CompraRequisicao requisicao, @AuthenticationPrincipal Usuario comprador) {
		Produto produto = em.find(Produto.class, requisicao.getProdutoId());
		if(!produto.abaterEstoque(requisicao.getQuantidade())) {
			throw new EstoqueAbaixoDoSolicitadoException();
		}
		
		Compra compra = requisicao.paraEntidade(produto, comprador);
		
		em.persist(compra);
		if(requisicao.getTipoPagamento().equals(TipoPagamento.PAYPAL)) {
			return "paypal.com?buyerId=" + produto.getId() + "&redirectUrl=http://localhost:8080/retorno-pagamento-paypal/" + produto.getId();
		} else {
			return "pagseguro.com?returnId=" + produto.getId() + "&redirectUrl=http://localhost:8080/retorno-pagamento-pagseguro/" + produto.getId();
		}
	}
}
