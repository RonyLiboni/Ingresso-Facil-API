package br.com.IngressoFacilAPI.services.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.EventoRepository;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.util.Util;

class EventoServiceTest extends ControllerAndServiceTestConfig {

	@InjectMocks
	private EventoService eventoService;
	@Mock
	private LocalService localServiceMock;
	@Mock
	private ClienteRepository clienteRepositoryMock;
	@Mock
	private EventoRepository eventoRepositoryMock;
	@Captor
	private ArgumentCaptor<Evento> captor;

	@Test
	void procurarPeloId_DeveRetornarUmLocal_QuandoIdExiste() {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(Util.criarEvento()));
		Evento evento = eventoService.procurarPeloId(1L);

		assertThat(evento).isNotNull();
	}

	@Test
	void procurarPeloId_DeveLancarException_QuandoIdNaoExiste() {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

		assertThatExceptionOfType(IdNotFoundException.class).isThrownBy(() -> eventoService.procurarPeloId(1L));
	}

	@Test
	void cadastrar_DeveTranformarEventoFormEmEvento_QuandoRecebeUmEventoFormComOsAtributosValidados() {
		eventoService.cadastrar(Util.criarEventoForm());

		BDDMockito.verify(eventoRepositoryMock).save(captor.capture());

		assertThat(captor.getValue()).isExactlyInstanceOf(Evento.class).isNotNull();
		assertThat(captor.getValue().getNome()).isEqualTo(Util.criarEventoForm().getNome());
	}

	@Test
	void atualizarCadastro_DeveTranformarEventoFormEmEvento_QuandoRecebeUmEventoFormComOsAtributosValidados() {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(Util.criarEvento()));

		eventoService.atualizarCadastro(1L, Util.criarEventoForm());

		BDDMockito.verify(eventoRepositoryMock).save(captor.capture());

		assertThat(captor.getValue()).isExactlyInstanceOf(Evento.class).isNotNull();
		assertThat(captor.getValue().getNome()).isEqualTo(Util.criarEventoForm().getNome());
	}

	@Test
	void atualizarQuantidadeDeIngressosDisponiveisNosEventos_QuandoReceberUmaListaDeIngressos() {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(Util.criarEvento()));

		eventoService.atualizarQuantidadeDeIngressosDisponiveisNosEventos(List.of(Util.criarCarrinho()));

		BDDMockito.verify(eventoRepositoryMock).save(captor.capture());

		assertThat(captor.getValue().getQuantidadeIngressos()).isEqualTo(100);
		assertThat(captor.getValue().getQuantidadeIngressosDisponiveis()).isEqualTo(90);
		assertThat(captor.getValue().getQuantidadeIngressosVendidos()).isEqualTo(10);
	}

	@Test
	void atualizarImagemDoEvento_DeveLancarFileNotFoundException_QuandoArquivoEstiverVazio() throws Exception {
		assertThatExceptionOfType(FileNotFoundException.class)
				.isThrownBy(() -> eventoService.atualizarImagemDoEvento(1L, Util.criarUmMultiPartVazio()));
	}

	@Test
	void atualizarImagemDoEvento_DeveLancarFileUploadException_QuandoDerErroNaTransferenciaDoArquivo() throws Exception {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
				.thenReturn(Optional.of(Util.criarEvento()));
		BDDMockito.when(eventoRepositoryMock.save(ArgumentMatchers.any(Evento.class)))
				.thenThrow(new RuntimeException());

		assertThatExceptionOfType(FileUploadException.class)
				.isThrownBy(() -> eventoService.atualizarImagemDoEvento(1L, Util.criarUmMultiPart()));
	}
	
	@Test
	void atualizarImagemDoEvento_DeveSalvarOArquivoLocalmente_QuandoEnviadoUmArquivo() throws Exception {
		BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
		.thenReturn(Optional.of(Util.criarEvento()));
		
		String caminhoDoArquivo = eventoService.atualizarImagemDoEvento(1L, Util.criarUmMultiPart());
		
		assertThat(Util.apagarArquivoTeste(caminhoDoArquivo)).isEqualTo(true);
	}
	
	@Test
	void listarDto_DeveTransformarEmPageEventoDto_QuandoReceberUmaPageEvento() {
		PageImpl<Evento> eventoPage = new PageImpl<Evento>(List.of(Util.criarEvento()));
	    BDDMockito.when(eventoRepositoryMock.findAll(ArgumentMatchers.any(Util.criarPageable().getClass())))
	                .thenReturn(eventoPage);
	    
	    Page<EventoDto> eventoDtoPage = eventoService.listarDto(Util.criarPageable());
	    EventoDto eventoDto = eventoDtoPage.getContent().get(0);
	    assertThat(eventoDto).isExactlyInstanceOf(EventoDto.class);
	    assertThat(eventoDto.getNome()).isEqualTo(Util.criarEvento().getNome());
	    assertThat(eventoDto.getLocal()).isEqualTo(Util.criarEvento().getLocal());
	    assertThat(eventoDto.getQuantidadeIngressos()).isEqualTo(Util.criarEvento().getQuantidadeIngressos());  
	}
	

}
