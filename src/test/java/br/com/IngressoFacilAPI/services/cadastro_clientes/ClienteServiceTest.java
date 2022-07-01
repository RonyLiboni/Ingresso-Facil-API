package br.com.IngressoFacilAPI.services.cadastro_clientes;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailSenderService;
import br.com.IngressoFacilAPI.util.Util;

class ClienteServiceTest extends ControllerAndServiceTestConfig {

	@InjectMocks
	private ClienteService clienteService;
	@Mock
	private ClienteRepository clienteRepositoryMock;
	@Captor
	private ArgumentCaptor<Cliente> captor;
	@Mock
	private UsuarioService usuarioServiceMock;
	@Mock
	private EmailSenderService emailSenderServiceMock; 

	@Test
	void procurarPeloId_DeveRetornarUmLocal_QuandoIdExiste() {
		BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(Util.criarCliente()));

		assertThat(clienteService.procurarPeloId(1L)).isNotNull();
	}

	@Test
	void procurarPeloId_DeveLancarException_QuandoIdNaoExiste() {
		BDDMockito.when(clienteRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(IdNotFoundException.class).isThrownBy(() -> clienteService.procurarPeloId(1L));
	}

	@Test
	void adicionarIngresso_DeveRetornarUmClienteComIngressoCriado_QuandoEnviadoUmaListaDeIngressos() {
		BDDMockito.when(clienteRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Util.criarCliente()));
		
		clienteService.adicionarIngresso(List.of(Util.criarIngresso()), "email");
		
		BDDMockito.verify(clienteRepositoryMock).save(captor.capture());
		Cliente cliente = captor.getValue();
		
		assertThat(cliente.getIngressos()).isNotNull().isNotEmpty();
		assertThat(cliente.getIngressos().get(0).getEvento().getNome()).isEqualTo(Util.criarEvento().getNome());
	}
	
	@Test
	void adicionarIngresso_DeveRetornarUmClienteSemIngresso_QuandoEnviadoUmaListaDeIngressosVazia() {
		BDDMockito.when(clienteRepositoryMock.findByEmail(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(Util.criarCliente()));
		
		clienteService.adicionarIngresso(new ArrayList<Ingresso>(), "email");
		
		BDDMockito.verify(clienteRepositoryMock).save(captor.capture());
		Cliente cliente = captor.getValue();
		
		assertThat(cliente.getIngressos()).isEmpty();
	}
	
	@Test
	void cadastrarCliente_DeveRetornarUmCliente_QuandoRecebeUmClienteCadastroFormValidado() {
		clienteService.cadastrarCliente(Util.criarClienteForm());
		BDDMockito.verify(clienteRepositoryMock).save(captor.capture());
		
		assertThat(captor.getValue().getNome()).isEqualTo(Util.criarClienteForm().getNome());
		assertThat(captor.getValue()).isExactlyInstanceOf(Cliente.class);
	}
	

}
