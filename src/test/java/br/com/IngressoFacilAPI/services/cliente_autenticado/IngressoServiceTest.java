package br.com.IngressoFacilAPI.services.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.exceptions.CarrinhoVazioException;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.services.cadastro_clientes.ClienteService;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailSenderService;
import br.com.IngressoFacilAPI.util.Util;

class IngressoServiceTest extends ControllerAndServiceTestConfig {
	@InjectMocks
	private IngressoService ingressoService;
	@Mock
	private ClienteService clienteServiceMock;
	@Mock
	private EventoService eventoServiceMock;
	@Mock
	private EmailSenderService emailSenderServiceMock;
	
	@Test
	void transformarCarrinhoEmIngresso_DeveRetornarUmaListaDeIngresso_QuandoRecebeUmaListaDeCarrinhoNaoVazia() {	
		BDDMockito.when(eventoServiceMock.procurarPeloId(ArgumentMatchers.anyLong())).thenReturn(Util.criarEvento());
		
		List<Ingresso> ingressos = ingressoService.transformarCarrinhoEmIngresso(List.of(Util.criarCarrinho()), Util.criarCliente().getEmail());
		
		assertThat(ingressos.get(0).getEvento().getId()).isEqualTo(Util.criarEvento().getId());
		assertThat(ingressos.get(0).getQuantidadeIngressos()).isEqualTo(Util.criarCarrinho().getQuantidadeIngressos());
	}

	@Test
	void transformarCarrinhoEmIngresso_DeveLancarCarrinhoVazioException_QuandoRecebeUmaListaDeCarrinhoNaoVazia() {
		assertThatExceptionOfType(CarrinhoVazioException.class).isThrownBy(()-> ingressoService.transformarCarrinhoEmIngresso(new ArrayList<Carrinho>(), Util.criarCliente().getEmail()));
	}
}
