package br.com.IngressoFacilAPI.services.autenticacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.repositories.autenticacao.UsuarioRepository;
import br.com.IngressoFacilAPI.util.Util;

class AutenticacaoServiceTest extends ServiceTestConfig{
	
	@InjectMocks
	private AutenticacaoService autenticacaoService;
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Test
	void loadUserByUsername_DeveRetornarUmUsername_QuandoUsernameExiste() {
		BDDMockito.when(usuarioRepository.findByEmail(ArgumentMatchers.anyString()))
		.thenReturn(Optional.of(Util.criarUsuario()));
	
		assertThat(autenticacaoService.loadUserByUsername(Util.criarUsuario().getEmail())).isNotNull();
	}
	
	@Test
	void loadUserByUsername_DeveLancarException_QuandoUsernameNaoExiste() {
		BDDMockito.when(usuarioRepository.findByEmail(ArgumentMatchers.anyString()))
		.thenReturn(Optional.empty());
	
		assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> autenticacaoService.loadUserByUsername(Util.criarUsuario().getEmail()));
	}

}
