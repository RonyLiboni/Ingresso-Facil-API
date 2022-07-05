package br.com.IngressoFacilAPI.controllers.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThat;

import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.ingresso.dto.IngressoDto;
import br.com.IngressoFacilAPI.services.cliente_autenticado.CompraService;
import br.com.IngressoFacilAPI.util.Util;

class CompraControllerTest extends ServiceTestConfig {
	@InjectMocks
	private CompraController compraController;
	@Mock
	private CompraService compraServiceMock;
	@Mock
	private Principal principalMock;

	@Test
	void comprarIngresso_DeveRetornarUmResponseEntityComStatusOkEUmaListaDeIngressoDto_QuandoClienteEstaAutenticado() {
		BDDMockito.when(principalMock.getName())
		.thenReturn(Util.criarCliente().getEmail());
		BDDMockito.when(compraServiceMock.processoDeCompraDeIngressos(ArgumentMatchers.anyString()))
		.thenReturn(List.of(Util.criarIngresso()));
		
		ResponseEntity<List<IngressoDto>> listaIngressos = compraController.comprarIngressos(principalMock);
		
		assertThat(listaIngressos.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listaIngressos.getBody().get(0)).isExactlyInstanceOf(IngressoDto.class).isNotNull();
		assertThat(listaIngressos.getBody().get(0).getQuantidadeIngressos()).isEqualTo(Util.criarIngresso().getQuantidadeIngressos());
	}

}
