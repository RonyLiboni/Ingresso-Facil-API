package br.com.IngressoFacilAPI.controllers.cliente_nao_autenticado;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.IngressoFacilAPI.config.ServiceTestConfig;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoHomeDto;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.util.Util;

class HomeControllerTest extends ServiceTestConfig{
	
	@InjectMocks
	private HomeController homeController;
	@Mock
	private EventoService eventoServiceMock;
	
	@Test
	void listar_DeveRetornarUmResponseEntityComStatusOKEUmaPageDeEventoHomeDto() {
		BDDMockito.when(eventoServiceMock.listar(Util.criarPageable()))
		.thenReturn(Util.criarPageDeEvento());
		
		ResponseEntity<Page<EventoHomeDto>> listar = homeController.listar(Util.criarPageable());
		
		assertThat(listar.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(listar.getBody().getSize()).isEqualTo(Util.criarPageDeEvento().getSize());
		assertThat(listar.getBody()).isExactlyInstanceOf(PageImpl.class);
		assertThat(listar.getBody().getContent().get(0)).isExactlyInstanceOf(EventoHomeDto.class).isNotNull();
		assertThat(listar.getBody().getContent().get(0).getNome()).isEqualTo(Util.criarEvento().getNome());
	}

}
