package br.com.zupacademy.erivelton.mercadolivre.config.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.zupacademy.erivelton.mercadolivre.entidade.Usuario;
import br.com.zupacademy.erivelton.mercadolivre.repositorio.UsuarioRepositorio;

@Component
public class UsuarioComponent implements UserDetailsService{

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepositorio.findByLogin(username);
		
		if(usuario.isPresent()) {
			return (UserDetails) usuario.get();
		}
		
		throw new UsernameNotFoundException("Usuário não encontrado!!");
	}

}
