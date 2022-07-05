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

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.util.Util;

class EventoControllerTest extends ServiceTestConfig {
	
	@InjectMocks
	private EventoController eventoController;
	@Mock
	private EventoService eventoServiceMock;
	
	@Test
	void listar_DeveRetornarUmResponseEntityComStatusOkEUmaPageDeEventoHomeDto() {
		BDDMockito.when(eventoServiceMock.listarDto(ArgumentMatchers.any(Util.criarPageable().getClass())))
		.thenReturn(Util.criarPageDeEvento().map(EventoDto::new));
		
		ResponseEntity<Page<EventoDto>> pageEventosDto = eventoController.listar(Util.criarPageable());
		
		assertThat(pageEventosDto.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(pageEventosDto.getBody().getSize()).isEqualTo(Util.criarPageDeEvento().getSize());
		assertThat(pageEventosDto.getBody()).isExactlyInstanceOf(PageImpl.class);
		assertThat(pageEventosDto.getBody().getContent().get(0)).isExactlyInstanceOf(EventoDto.class).isNotNull();
		assertThat(pageEventosDto.getBody().getContent().get(0).getNome()).isEqualTo(Util.criarEvento().getNome());
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
		
		ResponseEntity<EventoDto> eventoAtualizado = eventoController.atualizar(1l, Util.criarEventoForm());
		
		assertThat(eventoAtualizado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(eventoAtualizado.getBody()).isExactlyInstanceOf(EventoDto.class);
		assertThat(eventoAtualizado.getBody().getNome()).isEqualTo(Util.criarEvento().getNome());		
	}
	
	@Test
	void deletar_DeveRetornarUmResponseEntityComStatusNoContent_QuandoRecebeUmIdDeEventoValido() {		
		assertThat(eventoController.deletar(1l).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}
	
	@Test
	void atualizarImagemDoEvento_DeveRetornarUmResponseEntityComStatusOkECaminhoQueImagemFoiSalva_QuandoRecebeUmIdDeEventoValidoEUmaImagem() throws Exception {		
			assertThat(eventoController.atualizarImagemDoEvento(1l, Util.criarUmMultiPart()).getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	

}
