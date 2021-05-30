package br.com.zupacademy.erivelton.mercadolivre.controle;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.erivelton.mercadolivre.dto.resposta.DetalhesProdutoDTO;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;
import br.com.zupacademy.erivelton.mercadolivre.repositorio.ProdutoRepositorio;

@RestController
public class DetalhesProdutoControle {

	@Autowired
	private ProdutoRepositorio repositorio;

	@GetMapping(value = "detalhes/produtos/{id}")
	public DetalhesProdutoDTO detalharProduto(@PathVariable Long id) {
		Optional<Produto> produto = repositorio.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}

		DetalhesProdutoDTO dto = new DetalhesProdutoDTO(produto.get());

		return dto;
	}
}
