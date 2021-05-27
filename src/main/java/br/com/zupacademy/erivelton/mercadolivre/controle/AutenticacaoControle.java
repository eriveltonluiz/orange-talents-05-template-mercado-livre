package br.com.zupacademy.erivelton.mercadolivre.controle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.erivelton.mercadolivre.config.seguranca.TokenComponent;
import br.com.zupacademy.erivelton.mercadolivre.dto.UsuarioRequisicaoLogin;

@RestController
public class AutenticacaoControle {

	@Autowired
	private TokenComponent tokenComponent;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value = "/autenticacao")
	public String logar(@Valid @RequestBody UsuarioRequisicaoLogin usuarioRequisicao) {
		UsernamePasswordAuthenticationToken dadosLogin = usuarioRequisicao.converter();

		Authentication authentication = authenticationManager.authenticate(dadosLogin);
		String token = tokenComponent.gerarToken(authentication);
		return token;
	}
}
