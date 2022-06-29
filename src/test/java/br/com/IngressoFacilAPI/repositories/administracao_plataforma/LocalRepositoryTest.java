package br.com.IngressoFacilAPI.repositories.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.util.Util;

class LocalRepositoryTest extends RepositoryTestConfig{
	
	@Autowired
	private LocalRepository localRepository;
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	private final String nome = "FIERGS";
	private final String nomeQueNaoExiste ="NAO EXISTE";
		
	@Test
	void findAll_DeveRetornarUmaPaginaVazia_QuandoNaoHaLocaisNoBancoDeDados() {
		clienteRepository.deleteAll();
		eventoRepository.deleteAll();
		localRepository.deleteAll();
		Page<Local> locaisPage = localRepository.findAll(Util.criarPageable());
		assertThat(locaisPage.hasContent()).isEqualTo(false);
		assertThat(locaisPage.getSize()).isEqualTo(0);
	}
	
	@Test
	void findAll_DeveRetornarUmaPaginaDeLocais_QuandoHaLocaisNoBancoDeDados() {
		Page<Local> locaisPage = localRepository.findAll(Util.criarPageable());
		assertThat(locaisPage.hasContent()).isEqualTo(true);
		assertThat(locaisPage.getSize()).isEqualTo(localRepository.count());
	}
	
	@Test
	void findById_DeveRetornarUmLocal_QuandoRecebeUmIdQueExiste() {
		assertThat(localRepository.findById(1L).get().getNome()).isEqualTo("Gigantinho");
	}
	
	@Test
	void findById_DeveRetornarUmOptionalVazio_QuandoRecebeUmIdQueNaoExiste() {
		assertThat(localRepository.findById(-1L).isEmpty()).isEqualTo(true);
	}
	
	@Test
	void save_DeveSalvarUmLocalNoBancoDeDados_QuandoReceberUmLocalComTodosAtributosPreenchidos() {
		Local localSalvo = localRepository.save(Util.criarLocal());
		assertThat(localSalvo.getId()).isNotNull();
	}
	
	@Test
	void save_DeveLancarException_QuandoReceberUmLocalComNomeOuEnderecoNulos() {
		assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()-> localRepository.save(new Local()));
	}
	
	@Test
	void delete_DeveDeletarUmLocalNoBancoDeDados_QuandoReceberUmIdQueExiste() {
		localRepository.deleteById(1L);
		assertThat(localRepository.existsById(1L)).isEqualTo(false);
	}
	
	@Test
	void delete_DeveJogarException_QuandoReceberUmIdQueNaoExiste() {
		assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(() -> localRepository.deleteById(-1L));
	}
	
	@Test
	void delete_DeveLancarExeption_QuandoTentarApagarUmLocalQueEstaVinculadoAUmEvento() {
		localRepository.deleteAll();
		assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()-> localRepository.deleteAll());
	}
		
	@Test
	void existsByNome_DeveRetornarTrue_QuandoNomeExisteNoBanco() {
		assertThat(localRepository.existsByNome(nome)).isEqualTo(true);
	}
	
	@Test
	void existsByNome_DeveRetornarFalse_QuandoNomeNaoExisteNoBanco() {
		assertThat(localRepository.existsByNome(nomeQueNaoExiste)).isEqualTo(false);
	}
	
	@Test
	void existsByNome_DeveRetornarFalse_QuandoNomeEhNulo() {
		assertThat(localRepository.existsByNome(null)).isEqualTo(false);
	}
	

}
