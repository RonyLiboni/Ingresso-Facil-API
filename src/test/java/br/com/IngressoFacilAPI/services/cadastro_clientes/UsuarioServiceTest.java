package br.com.IngressoFacilAPI.services.cadastro_clientes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import br.com.IngressoFacilAPI.repositories.autenticacao.UsuarioRepository;
import br.com.IngressoFacilAPI.util.Util;

class UsuarioServiceTest extends ControllerAndServiceTestConfig {
	@InjectMocks
	private UsuarioService usuarioService;
	@Mock
	private UsuarioRepository usuarioRepositoryMock;
	@Mock
	private PasswordEncoder passwordEncoderMock;
	@Captor
	private ArgumentCaptor<Usuario> captor;
	
	@Test
	void criarUsuario_DeveRetornarUmUsuario_QuandoRecebeClienteCadastroFormValidado() {
		usuarioService.criarUsuario(Util.criarClienteForm());
		BDDMockito.verify(usuarioRepositoryMock).save(captor.capture());
		assertThat(captor.getValue()).isExactlyInstanceOf(Usuario.class);
		assertThat(captor.getValue().getEmail()).isEqualTo(Util.criarClienteForm().getEmail());
	}

}
