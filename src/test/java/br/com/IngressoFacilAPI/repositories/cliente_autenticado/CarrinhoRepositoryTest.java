package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.IngressoFacilAPI.RepositoryTestConfig;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.util.Util;

class CarrinhoRepositoryTest extends RepositoryTestConfig {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	private final String email = "teste@teste.com";
	private final String emailQueNaoExiste= "nao existe";
		
	@Test
	void findByClienteEmail_DeveRetornarUmaListaComEventosNoCarrinhoDoCliente_QuandoEmailExisteEHaItensNoCarrinho() {
		List<Carrinho> carrinho = carrinhoRepository.findByClienteEmail(email);
		Assertions.assertThat(carrinho).isNotEmpty();
		Assertions.assertThat(carrinho.size()).isEqualTo(2);
		Assertions.assertThat(carrinho.get(0).getQuantidadeIngressos()).isEqualTo(10);
		Assertions.assertThat(carrinho.get(1).getQuantidadeIngressos()).isEqualTo(3);
	}
		
	@Test
	void findByClienteEmail_DeveRetornarUmaListaVazia_QuandoEmailNaoExiste() {
		Assertions.assertThat(carrinhoRepository.findByClienteEmail(emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void findByClienteEmail_DeveRetornarUmaListaVazia_QuandoEmailEhNulo() {
		Assertions.assertThat(carrinhoRepository.findByClienteEmail(null)).isEmpty();
	}
	
	@Test
	void findByEventoIdAndClienteEmail_DeveRetornarUmOptionalDeCarrinho_QuandoEmailEEventoIdEstaoCorretosEHaItensNoCarrinho() {
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findByEventoIdAndClienteEmail(1L, email);
		Assertions.assertThat(carrinhoOptional).isNotEmpty();
		Assertions.assertThat(carrinhoOptional.get().getQuantidadeIngressos()).isEqualTo(10);
	}

	@Test
	void findByEventoIdAndClienteEmail_DeveRetornarUmOptionalDeCarrinhoVazio_QuandoEmailEEventoIdEstaoCorretosENaoHaItensNoCarrinho() {
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(3L, email)).isEmpty();
	}
	
	@Test
	void findByEventoIdAndClienteEmail_DeveRetornarUmOptionalDeCarrinhoVazio_QuandoEmailOuEventoIdNaoExistem() {
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(-1L, email)).isEmpty();
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(1L, emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void save_DeveSalvarUmCarrinhoNoBancoDeDados_QuandoReceberUmCarrinhoComTodosAtributosPreenchidos() {
		Carrinho carrinhoSalvo = carrinhoRepository.save(Util.criarCarrinho());
		assertThat(carrinhoSalvo.getId()).isNotNull();
	}
	
	@Test
	void save_DeveLancarException_QuandoReceberUmLocalComNomeOuEnderecoNulos() {
		assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()-> carrinhoRepository.save(new Carrinho()));
	}
	
	@Test
	void delete_DeveDeletarUmLocalNoBancoDeDados_QuandoReceberUmIdQueExiste() {
		carrinhoRepository.deleteById(1L);
		assertThat(carrinhoRepository.existsById(1L)).isEqualTo(false);
	}
	
	@Test
	void delete_DeveJogarException_QuandoReceberUmIdQueNaoExiste() {
		assertThatExceptionOfType(EmptyResultDataAccessException.class).isThrownBy(() -> carrinhoRepository.deleteById(-1L));
	}	
	
}
