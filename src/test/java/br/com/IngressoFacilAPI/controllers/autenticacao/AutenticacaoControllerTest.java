package br.com.IngressoFacilAPI.controllers.autenticacao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.token.dto.TokenDto;
import br.com.IngressoFacilAPI.services.autenticacao.TokenService;
import br.com.IngressoFacilAPI.util.Util;

class AutenticacaoControllerTest extends ServiceTestConfig {
	
	@InjectMocks
	private AutenticacaoController autenticacaoController;
	@Mock
	private TokenService tokenServiceMock;
	@Mock
	private AuthenticationManager authManagerMock;
	
	@Test
	void autenticar_DeveRetornarUmResponseEntityComStatusOKETokenDto_QuandoDadosDoUsuarioConferemComBancoDeDados() {
		BDDMockito.when(tokenServiceMock.criarToken(Util.criarLoginForm(), authManagerMock))
		.thenReturn(Util.criarToken());
		
		ResponseEntity<TokenDto> autenticar = autenticacaoController.autenticar(Util.criarLoginForm());
		
		assertThat(autenticar.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(autenticar.getBody().getToken()).isEqualTo(Util.criarToken());
		assertThat(autenticar.getBody().getTipo()).isEqualTo("Bearer");
		assertThat(autenticar.getBody()).isExactlyInstanceOf(TokenDto.class);		
	}
	


}
