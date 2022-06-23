package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;

class ClienteRepositoryTest extends RepositoryTestConfig {
	
	@Autowired
	private ClienteRepository clienteRepository;
	private final String email = "teste@teste.com";
	private final String emailQueNaoExiste= "nao existe";
		
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
	

}
