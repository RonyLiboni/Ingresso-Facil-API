package br.com.IngressoFacilAPI.controllers.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.util.Util;

class EventoControllerTest extends ControllerAndServiceTestConfig {
	
	@InjectMocks
	private EventoController eventoController;
	@Mock
	private EventoService eventoServiceMock;
	
	@Test
	void listar_DeveRetornarUmResponseEntityComStatusOkEUmaPageDeEventoHomeDto() {
		BDDMockito.when(eventoServiceMock.listarDto(ArgumentMatchers.any(Util.criarPageable().getClass())))
		.thenReturn(Util.criarPageDeEvento().map(EventoDto::new));
		
		ResponseEntity<Page<EventoDto>> listar = eventoController.listar(Util.criarPageable());
		
		assertThat(listar.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listar.getBody().getSize()).isEqualTo(Util.criarPageDeEvento().getSize());
		assertThat(listar.getBody()).isExactlyInstanceOf(PageImpl.class);
		assertThat(listar.getBody().getContent().get(0)).isExactlyInstanceOf(EventoDto.class).isNotNull();
		assertThat(listar.getBody().getContent().get(0).getNome()).isEqualTo(Util.criarEvento().getNome());
	}
	
	@Test
	void cadastrar_DeveRetornarUmResponseEntityComStatusCreatedEEventoDto_QuandoRecebeUmEventoFormValidado() {
		BDDMockito.when(eventoServiceMock.cadastrar(ArgumentMatchers.any(EventoForm.class)))
		.thenReturn(Util.criarEvento());
		
		ResponseEntity<EventoDto> eventoCadastrado = eventoController.cadastrar(Util.criarEventoForm());
		
		assertThat(eventoCadastrado.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(eventoCadastrado.getBody()).isExactlyInstanceOf(EventoDto.class);
		assertThat(eventoCadastrado.getBody().getNome()).isEqualTo(Util.criarEvento().getNome());		
	}
	
	@Test
	void procurar_DeveRetornarUmResponseEntityComStatusOkEEventoDto_QuandoRecebeUmIdDeEventoValido() {
		BDDMockito.when(eventoServiceMock.procurarPeloId(ArgumentMatchers.anyLong()))
		.thenReturn(Util.criarEvento());
		
		ResponseEntity<EventoDto> eventoAchado = eventoController.procurar(1l);
		
		assertThat(eventoAchado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(eventoAchado.getBody()).isExactlyInstanceOf(EventoDto.class);
		assertThat(eventoAchado.getBody().getNome()).isEqualTo(Util.criarEvento().getNome());		
	}
	
	@Test
	void atualizar_DeveRetornarUmResponseEntityComStatusOkEEventoDto_QuandoRecebeUmIdDeEventoValido() {
		BDDMockito.when(eventoServiceMock.atualizarCadastro(ArgumentMatchers.anyLong(),ArgumentMatchers.any(EventoForm.class)))
		.thenReturn(Util.criarEvento());
		
		ResponseEntity<EventoDto> eventoAchado = eventoController.atualizar(1l, Util.criarEventoForm());
		
		assertThat(eventoAchado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(eventoAchado.getBody()).isExactlyInstanceOf(EventoDto.class);
		assertThat(eventoAchado.getBody().getNome()).isEqualTo(Util.criarEvento().getNome());		
	}
	
	@Test
	void deletar_DeveRetornarUmResponseEntityComStatusNoContent_QuandoRecebeUmIdDeEventoValido() {		
		ResponseEntity<Object> statusCodeEventoDeletado = eventoController.deletar(1l);
		
		assertThat(statusCodeEventoDeletado.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	void atualizarImagemDoEvento_DeveRetornarUmResponseEntityComStatusOkECaminhoQueImagemFoiSalva_QuandoRecebeUmIdDeEventoValidoEUmaImagem() throws Exception {		
		ResponseEntity<String> caminhoDaImagem = eventoController.atualizarImagemDoEvento(1l, Util.criarUmMultiPart());
		
		assertThat(caminhoDaImagem.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	

}
