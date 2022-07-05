package br.com.IngressoFacilAPI.services.autenticacao;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.repositories.autenticacao.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Profile({"default","testes"})
public class AutenticacaoService implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usuarioRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("Dados inválidos!"));
	}
}
