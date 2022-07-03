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
import br.com.IngressoFacilAPI.entities.carrinho.dto.CarrinhoDto;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.services.cliente_autenticado.CarrinhoService;
import br.com.IngressoFacilAPI.util.Util;

class CarrinhoControllerTest extends ControllerAndServiceTestConfig {
	
	@InjectMocks
	private CarrinhoController carrinhoController;
	@Mock
	private CarrinhoService carrinhoServiceMock;
	@Mock
	private Principal principalMock;
	
	@Test
	void atualizarCarrinho_DeveRetornarUmResponseEntityComStatusOkEOItensDoCarrinhoQueFoiAtualizado_QuandoRecebeUmCarrinhoFormValidadoEClienteEstaAutenticado() {
		BDDMockito.when(carrinhoServiceMock.atualizarCarrinho(ArgumentMatchers.any(CarrinhoForm.class), ArgumentMatchers.anyString()))
		.thenReturn(Util.criarCarrinho());
		BDDMockito.when(principalMock.getName())
		.thenReturn(Util.criarCliente().getEmail());	
		
		ResponseEntity<CarrinhoDto> carrinhoAtualizado = carrinhoController.atualizarCarrinho(Util.criarCarrinhoForm(), principalMock);
		
		assertThat(carrinhoAtualizado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(carrinhoAtualizado.getBody()).isExactlyInstanceOf(CarrinhoDto.class).isNotNull();
		assertThat(carrinhoAtualizado.getBody().getQuantidadeIngressos()).isEqualTo(Util.criarCarrinho().getQuantidadeIngressos());
	}
	
	@Test
	void procurar_DeveRetornarUmResponseEntityComStatusOkEUmaListaDeCarrinhoDto_QuandoClienteEstaAutenticado() {
		BDDMockito.when(carrinhoServiceMock.retornaEventosDoCarrinho(ArgumentMatchers.anyString()))
		.thenReturn(List.of(Util.criarCarrinho()));
		BDDMockito.when(principalMock.getName())
		.thenReturn(Util.criarCliente().getEmail());	
		
		ResponseEntity<List<CarrinhoDto>> listaCarrinhoDto = carrinhoController.procurar(principalMock);
		
		assertThat(listaCarrinhoDto.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listaCarrinhoDto.getBody()).isNotNull().isNotEmpty();
		assertThat(listaCarrinhoDto.getBody().get(0)).isExactlyInstanceOf(CarrinhoDto.class);
		assertThat(listaCarrinhoDto.getBody().get(0).getQuantidadeIngressos()).isEqualTo(Util.criarCarrinho().getQuantidadeIngressos());
	}

}
