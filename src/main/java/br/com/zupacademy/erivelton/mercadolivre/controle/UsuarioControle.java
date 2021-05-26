package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.dto.UsuarioRequisicao;
import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;

@RestController
public class UsuarioControle {
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping(value = "usuarios")
	@Transactional
	public void salvarUsuario(@Valid @RequestBody UsuarioRequisicao usuarioRequisicao) {
		Usuario usuario = new Usuario(usuarioRequisicao.getLogin(), new BCryptPasswordEncoder().encode(usuarioRequisicao.getSenha()));
		em.persist(usuario);
	}
}
