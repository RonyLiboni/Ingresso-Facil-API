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

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.dto.HistoricoDto;
import br.com.IngressoFacilAPI.services.cadastro_clientes.ClienteService;
import br.com.IngressoFacilAPI.util.Util;

class HistoricoControllerTest extends ControllerAndServiceTestConfig {
	@InjectMocks
	private HistoricoController historicoController;
	@Mock
	private ClienteService clienteServiceMock;
	@Mock
	private Principal principalMock;
		
	@Test
	void retornaHistoricoDeComprasDoCliente_DeveRetornarUmResponseEntityComStatusOkEHistoricoDto_QuandoClienteEstaAutenticado() {
		BDDMockito.when(principalMock.getName())
		.thenReturn(Util.criarCliente().getEmail());
		Cliente cliente = Util.criarCliente();
		cliente.setIngressos(List.of(Util.criarIngresso()));
		BDDMockito.when(clienteServiceMock.procurarPeloEmail(ArgumentMatchers.anyString()))
		.thenReturn(cliente);
		
		ResponseEntity<HistoricoDto> historicoCliente = historicoController.retornaHistoricoDeComprasDoCliente(principalMock);
		
		assertThat(historicoCliente.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(historicoCliente.getBody().getIngressos().get(0).getQuantidadeIngressos()).isEqualTo(Util.criarIngresso().getQuantidadeIngressos());
	}

}
