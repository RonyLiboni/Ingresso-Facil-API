package br.com.IngressoFacilAPI.repositories.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import br.com.IngressoFacilAPI.config.RepositoryTestConfig;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.util.Util;

class EventoRepositoryTest extends RepositoryTestConfig{
	
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Test
	void findAll_DeveRetornarUmaPaginaVazia_QuandoNaoHaLocaisNoBancoDeDados() {
		clienteRepository.deleteAll();
		eventoRepository.deleteAll();
		Page<Evento> eventosPage = eventoRepository.findAll(Util.criarPageable());
		assertThat(eventosPage.hasContent()).isEqualTo(false);
		assertThat(eventosPage.getSize()).isEqualTo(0);
	}
	
	@Test
	void findAll_DeveRetornarUmaPaginaDeLocais_QuandoHaLocaisNoBancoDeDados() {
		Page<Evento> eventosPage = eventoRepository.findAll(Util.criarPageable());
		assertThat(eventosPage.hasContent()).isEqualTo(true);
		assertThat(eventosPage.getSize())
										.isEqualTo(eventoRepository.count())
										.isEqualTo(4);
	}
	
	@Test
	void findById_DeveRetornarUmEvento_QuandoRecebeUmIdQueExiste() {
		assertThat(eventoRepository.findById(3L).get().getNome()).isEqualTo("Guns N´ Roses");
	}
	
	@Test
	void findById_DeveRetornarUmOptionalVazio_QuandoRecebeUmIdQueNaoExiste() {
		assertThat(eventoRepository.findById(-1L).isEmpty()).isEqualTo(true);
	}
	
	@Test
	void save_DeveSalvarUmEventoNoBancoDeDados_QuandoReceberUmEvento() {	
		Evento eventoSalvo = eventoRepository.save(Util.criarEvento());
		assertThat(eventoSalvo.getId()).isNotNull();
		assertThat(eventoSalvo.getNome()).isEqualTo(Util.criarEvento().getNome());
	}
	
	@Test
	void delete_DeveDeletarUmEventoNoBancoDeDados_QuandoReceberUmIdQueExisteENenhumClienteComprouIngressosDoEvento() {
		eventoRepository.deleteById(4L);
		assertThat(eventoRepository.existsById(4L)).isEqualTo(false);
	}
	
	@Test
	void delete_DeveJogarException_QuandoReceberUmIdQueNaoExiste() {
		assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(() -> eventoRepository.deleteById(-1L));
	}
	
		
	

}
