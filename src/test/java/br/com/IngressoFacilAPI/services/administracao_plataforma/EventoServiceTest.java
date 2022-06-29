package br.com.IngressoFacilAPI.services.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.EventoRepository;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.util.Util;

class EventoServiceTest extends ServiceTestConfig{
	
	@InjectMocks
	private EventoService eventoService;
	@Mock
	private LocalService localServiceMock;
	@Mock
	private ClienteRepository clienteRepositoryMock;
	@Mock
	private EventoRepository eventoRepositoryMock;
	
	
	

	@Test
	void listar_DeveRetornarUmaPaginaComEvento_QuandoSolicitado() {
		PageImpl<Evento> eventoPage = new PageImpl<>(List.of(Util.criarEvento()));
	    BDDMockito.when(eventoRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
	                .thenReturn(eventoPage);
		
		Page<Evento> pagina = eventoService.listar(PageRequest.of(1,1));
		List<Evento> eventos = pagina.getContent();
		assertThat(eventos.get(0).getNome()).isEqualTo(Util.criarEvento().getNome());
		assertThat(pagina.getSize()).isEqualTo(1);
	}
	
	@Test
	void salvar_DeveSalvarNoBancoDeDadosERetornarOObjetoSalvo_QuandoObjetoLocalEstaValidado() {
		BDDMockito.when(eventoRepositoryMock.save(ArgumentMatchers.any(Evento.class)))
		.thenReturn(Util.criarEvento());
		Evento eventoSalvo = eventoService.salvar(Util.criarEvento());
		
		assertThat(eventoSalvo.getNome()).isEqualTo(Util.criarEvento().getNome());
	}
		
	@Test
	void procurarPeloId_DeveRetornarUmLocal_QuandoIdExiste() {
      BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
      .thenReturn(Optional.of(Util.criarEvento()));
      Evento evento = eventoService.procurarPeloId(1L);
      
      assertThat(evento).isNotNull();
	}
	
	@Test
	void procurarPeloId_DeveLancarException_QuandoIdNaoExiste() {
      BDDMockito.when(eventoRepositoryMock.findById(ArgumentMatchers.anyLong()))
      .thenReturn(Optional.empty());

      assertThatExceptionOfType(IdNotFoundException.class).isThrownBy(() -> eventoService.procurarPeloId(1L));
	}


}
