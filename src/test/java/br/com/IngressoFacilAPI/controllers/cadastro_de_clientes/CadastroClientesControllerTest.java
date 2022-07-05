package br.com.IngressoFacilAPI.controllers.cadastro_de_clientes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.cliente.dto.ClienteDto;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.services.cadastro_clientes.ClienteService;
import br.com.IngressoFacilAPI.util.Util;

class CadastroClientesControllerTest extends ServiceTestConfig {
	
	@InjectMocks
	private CadastroClientesController cadastroClientesController;
	@Mock
	private ClienteService clienteServiceMock;
		
	@Test
	void cadastrarCliente_DeveRetornarUmResponseEntityComStatusCreatedEUmClienteDto_QuandoReceberUmClienteCadastroFormValidado() {
		BDDMockito.when(clienteServiceMock.cadastrarCliente(ArgumentMatchers.any(ClienteCadastroForm.class)))
		.thenReturn(Util.criarCliente());
		
		ResponseEntity<ClienteDto> clienteCadastrado = cadastroClientesController.cadastrarCliente(Util.criarClienteForm());
		
		assertThat(clienteCadastrado.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(clienteCadastrado.getBody()).isExactlyInstanceOf(ClienteDto.class);
		assertThat(clienteCadastrado.getBody().getEmail()).isEqualTo(Util.criarClienteForm().getEmail());
	}

}
