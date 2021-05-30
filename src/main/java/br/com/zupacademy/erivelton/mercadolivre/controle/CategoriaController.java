package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.dto.requisicao.CategoriaRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Categoria;

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "/categorias")
	@Transactional
	public void salvarCategoria(@Valid @RequestBody CategoriaRequisicao categoriaRequisicao) {
		Categoria categoria = new Categoria(categoriaRequisicao.getNome());
		
		if(categoriaRequisicao.getCategoriaMaeId() != null) {
			Categoria categoriaMae = em.find(Categoria.class, categoriaRequisicao.getCategoriaMaeId());
			categoria.setCategoria(categoriaMae);
		}
		
		em.persist(categoria);
	}
}
