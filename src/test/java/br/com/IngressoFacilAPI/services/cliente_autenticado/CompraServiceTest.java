package br.com.IngressoFacilAPI.services.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.exceptions.CarrinhoVazioException;
import br.com.IngressoFacilAPI.exceptions.SemEstoqueException;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.util.Util;

class CompraServiceTest extends ServiceTestConfig {
	@InjectMocks
	private CompraService compraService;
	@Mock
	private CarrinhoService carrinhoServiceMock;
	@Mock
	private EventoService eventoServiceMock;
	@Mock
	private IngressoService ingressoServiceMock;
	
	@Test
	void processoDeCompraDeIngressos_DeveLancarCarrinhoVazioException_QuandoCarrinhoRecebidoEstiverVazio() {
		BDDMockito.when(carrinhoServiceMock.retornaEventosDoCarrinho(ArgumentMatchers.anyString()))
		.thenReturn(new ArrayList<Carrinho>());
		
		assertThatExceptionOfType(CarrinhoVazioException.class).isThrownBy(()-> compraService.processoDeCompraDeIngressos(Util.criarCliente().getEmail()));
	}
	
	@Test
	void processoDeCompraDeIngressos_DeveLancarSemEstoqueException_QuandoNaoHaEstoqueSuficienteEmUmDosItensDoCarrinho() {
		BDDMockito.when(carrinhoServiceMock.retornaEventosDoCarrinho(ArgumentMatchers.anyString()))
		.thenReturn(List.of(Util.criarCarrinho()));
		
		Evento evento = Util.criarEvento();
		evento.setQuantidadeIngressosDisponiveis(1);
		BDDMockito.when(eventoServiceMock.procurarPeloId(ArgumentMatchers.anyLong())).thenReturn(evento);
		
		assertThatExceptionOfType(SemEstoqueException.class).isThrownBy(()-> compraService.processoDeCompraDeIngressos(Util.criarCliente().getEmail()));
	}

	@Test
	void processoDeCompraDeIngressos_DeveChegarAteOMetodoTransformarCarrinhoEmIngresso_QuandoOCarrinhoExisteEHaEstoqueParaTodosItens() {
		BDDMockito.when(carrinhoServiceMock.retornaEventosDoCarrinho(ArgumentMatchers.anyString()))
		.thenReturn(List.of(Util.criarCarrinho()));
		BDDMockito.when(eventoServiceMock.procurarPeloId(ArgumentMatchers.anyLong())).thenReturn(Util.criarEvento());
		
		compraService.processoDeCompraDeIngressos(Util.criarCliente().getEmail());
		
		BDDMockito.verify(ingressoServiceMock).transformarCarrinhoEmIngresso(List.of(Util.criarCarrinho()), Util.criarCliente().getEmail());
	}
}
