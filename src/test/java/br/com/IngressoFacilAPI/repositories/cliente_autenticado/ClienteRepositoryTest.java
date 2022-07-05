package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.IngressoFacilAPI.config.RepositoryTestConfig;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.util.Util;

class ClienteRepositoryTest extends RepositoryTestConfig {
	
	@Autowired
	private ClienteRepository clienteRepository;
	private final String email = "teste@teste.com";
	private final String emailQueNaoExiste= "nao existe";
	private final String nome = "Teste";
		
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalComCliente_QuandoEmailExiste() {
		Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);
		Assertions.assertThat(clienteOptional).isNotEmpty();
		Assertions.assertThat(clienteOptional.get().getEmail()).isEqualTo(email);
	}
	
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalVazio_QuandoEmailNaoExiste() {
		Assertions.assertThat(clienteRepository.findByEmail(emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalVazio_QuandoEmailEhNulo() {
		Assertions.assertThat(clienteRepository.findByEmail(null)).isEmpty();
	}
	
	@Test
	void quantidadeDeIngressosVendidosPorEvento_DeveRetornarUmInteiro_QuandoIdDoEventoEhValidoEHaIngressosVendidos() {
		Assertions.assertThat(clienteRepository.quantidadeDeIngressosVendidosPorEvento(1l)).isEqualTo(25);
		Assertions.assertThat(clienteRepository.quantidadeDeIngressosVendidosPorEvento(2l)).isEqualTo(3);		
	}
	
	@Test
	void quantidadeDeIngressosVendidosPorEvento_DeveRetornarNulo_QuandoIdDoEventoEhValidoENaoHaIngressosVendidos() {
		Assertions.assertThat(clienteRepository.quantidadeDeIngressosVendidosPorEvento(3l)).isNull();
	}
	
	@Test
	void quantidadeDeIngressosVendidosPorEvento_DeveRetornarNulo_QuandoIdDoEventoEhNulo() {
		Assertions.assertThat(clienteRepository.quantidadeDeIngressosVendidosPorEvento(null)).isNull();
	}
	
	@Test
	void quantidadeDeIngressosVendidosPorEvento_DeveRetornarNulo_QuandoIdDoEventoNaoExiste() {
		Assertions.assertThat(clienteRepository.quantidadeDeIngressosVendidosPorEvento(1000l)).isNull();
	}
	@Test
	void findById_DeveRetornarUmCliente_QuandoRecebeUmIdQueExiste() {
		assertThat(clienteRepository.findById(150L).get().getNome()).isEqualTo(nome);
	}
	
	@Test
	void findById_DeveRetornarUmOptionalVazio_QuandoRecebeUmIdQueNaoExiste() {
		assertThat(clienteRepository.findById(-1L).isEmpty()).isEqualTo(true);
	}
	
	@Test
	void save_DeveSalvarUmClienteNoBancoDeDados_QuandoReceberUmLocalComTodosAtributosPreenchidos() {
		Cliente clienteSalvo = clienteRepository.save(Util.criarCliente());
		assertThat(clienteSalvo.getId()).isNotNull();
	}
	
	@Test
	void save_DeveLancarException_QuandoReceberUmClienteComAtributosNulos() {
		assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()-> clienteRepository.save(new Cliente()));
	}

}
