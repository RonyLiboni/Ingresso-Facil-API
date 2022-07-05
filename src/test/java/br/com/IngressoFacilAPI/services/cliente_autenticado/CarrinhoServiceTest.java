package br.com.IngressoFacilAPI.services.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.CarrinhoRepository;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.util.Util;

class CarrinhoServiceTest extends ServiceTestConfig {
	@InjectMocks
	private CarrinhoService carrinhoService;
	@Mock
	private CarrinhoRepository carrinhoRepositoryMock;
	@Mock
	private EventoService eventoServiceMock;
	@Captor
	private ArgumentCaptor<Carrinho> captor;
	
	@Test
	void apagarCarrinhoInteiro_DeveApagarTodosItensDoCarrinho_QuandoRecebeUmEmailDeClienteValidadoEHaDoisOuMaisItensNoCarrinho() {
		BDDMockito.when(carrinhoRepositoryMock.findByClienteEmail(ArgumentMatchers.anyString()))
		.thenReturn(List.of(Util.criarCarrinho(), Util.criarCarrinho()));
			
		carrinhoService.apagarCarrinhoInteiro(Util.criarCliente().getEmail());
	
		BDDMockito.verify(carrinhoRepositoryMock, times(2)).delete(Util.criarCarrinho());
	}
	
	@Test
	void apagarCarrinhoInteiro_DeveApagarTodosItensDoCarrinho_QuandoRecebeUmEmailDeClienteValidadoENaoHaItensNoCarrinho() {
		BDDMockito.when(carrinhoRepositoryMock.findByClienteEmail(ArgumentMatchers.anyString()))
		.thenReturn(new ArrayList<Carrinho>());
			
		carrinhoService.apagarCarrinhoInteiro(Util.criarCliente().getEmail());
	
		BDDMockito.verify(carrinhoRepositoryMock, times(0)).delete(Util.criarCarrinho());
	}
	
	@Test
	void atualizarCarrinho_DeveApagarItensDoCarrinho_QuandoAQuantidadeDeIngressosPedidaEhMenorQueUmEoCarrinhoJaTemOItemNele() {
		BDDMockito.when(carrinhoRepositoryMock.findByEventoIdAndClienteEmail(1L, Util.criarCliente().getEmail()))
		.thenReturn(Optional.of(Util.criarCarrinho()));
		
		Carrinho carrinhoAtualizado = carrinhoService.atualizarCarrinho(Util.criarCarrinhoFormComQuantidadeZero(), Util.criarCliente().getEmail());
		
		BDDMockito.verify(carrinhoRepositoryMock, times(1)).delete(Util.criarCarrinho());
		
		assertThat(carrinhoAtualizado.getQuantidadeIngressos()).isEqualTo(0);
	}
	
	@Test
	void atualizarCarrinho_DeveApagarItensDoCarrinho_QuandoAQuantidadeDeIngressosPedidaEhMenorQueUmEoCarrinhoNaoTemOItemNele() {
		BDDMockito.when(carrinhoRepositoryMock.findByEventoIdAndClienteEmail(1L, Util.criarCliente().getEmail()))
		.thenReturn(Optional.empty());
		
		Carrinho carrinhoAtualizado = carrinhoService.atualizarCarrinho(Util.criarCarrinhoFormComQuantidadeZero(), Util.criarCliente().getEmail());
		
		BDDMockito.verify(carrinhoRepositoryMock, times(1)).delete(new Carrinho());
		
		assertThat(carrinhoAtualizado.getQuantidadeIngressos()).isEqualTo(0);
	}
	
	@Test
	void atualizarCarrinho_DeveAlterarAQuantidadeDeIngressos_QuandoOCarrinhoExisteETemOItemNeleEAQuantidadePedidaEhMaiorQueZero() {
		Carrinho carrinho = Util.criarCarrinho();
		carrinho.setId(1L);
		BDDMockito.when(carrinhoRepositoryMock.findByEventoIdAndClienteEmail(1L, Util.criarCliente().getEmail()))
		.thenReturn(Optional.of(carrinho));
		
		carrinhoService.atualizarCarrinho(Util.criarCarrinhoForm(), Util.criarCliente().getEmail());
		
		BDDMockito.verify(carrinhoRepositoryMock, times(1)).save(captor.capture());
		assertThat(captor.getValue().getQuantidadeIngressos()).isEqualTo(Util.criarCarrinhoForm().getQuantidadeIngressos());
		assertThat(captor.getValue().getId()).isEqualTo(1L);
	}
	
	@Test
	void atualizarCarrinho_DeveCriarUmNovoCarrinhoComOItemDentro_QuandoOItemNaoExisteNoCarrinhoEAQuantidadePedidaEhMaiorQueZero() {
		BDDMockito.when(carrinhoRepositoryMock.findByEventoIdAndClienteEmail(1L, Util.criarCliente().getEmail()))
		.thenReturn(Optional.of(Util.criarCarrinho()));
		
		carrinhoService.atualizarCarrinho(Util.criarCarrinhoForm(), Util.criarCliente().getEmail());
		
		BDDMockito.verify(carrinhoRepositoryMock).save(captor.capture());
			
		assertThat(captor.getValue().getQuantidadeIngressos()).isEqualTo(Util.criarCarrinhoForm().getQuantidadeIngressos());
		assertThat(captor.getValue().getId()).isEqualTo(null);
		BDDMockito.verify(carrinhoRepositoryMock, times(1)).save(captor.getValue());
	}
	
	

}
