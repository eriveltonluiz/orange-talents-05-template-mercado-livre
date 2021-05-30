package br.com.zupacademy.erivelton.mercadolivre.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Produto;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long>{
	
}
