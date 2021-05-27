package br.com.zupacademy.erivelton.mercadolivre.config.seguranca;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.repositorio.UsuarioRepositorio;

public class JwtAutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenComponent tokenComponent;
	
	private UsuarioRepositorio usuarioRepositorio;

	public JwtAutenticacaoViaTokenFilter(TokenComponent tokenComponent, UsuarioRepositorio usuarioRepositorio) {
		this.tokenComponent = tokenComponent;
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		boolean valido = tokenComponent.tokenValido(token);
		
		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenComponent.getIdUsuario(token);
		Usuario usuario = usuarioRepositorio.findById(idUsuario).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
