package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;

class CarrinhoRepositoryTest extends RepositoryTestConfig {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	private final String email = "teste@teste.com";
	private final String emailQueNaoExiste= "nao existe";
		
	@Test
	void acharPeloEmailDoCliente_DeveRetornarUmaListaComEventosNoCarrinhoDoCliente_QuandoEmailExisteEHaItensNoCarrinho() {
		List<Carrinho> carrinho = carrinhoRepository.findByClienteEmail(email);
		Assertions.assertThat(carrinho).isNotEmpty();
		Assertions.assertThat(carrinho.size()).isEqualTo(2);
		Assertions.assertThat(carrinho.get(0).getQuantidadeIngressos()).isEqualTo(10);
		Assertions.assertThat(carrinho.get(1).getQuantidadeIngressos()).isEqualTo(3);
	}
		
	@Test
	void acharPeloEmailDoCliente_DeveRetornarUmaListaVazia_QuandoEmailNaoExiste() {
		Assertions.assertThat(carrinhoRepository.findByClienteEmail(emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void acharPeloEmailDoCliente_DeveRetornarUmaListaVazia_QuandoEmailEhNulo() {
		Assertions.assertThat(carrinhoRepository.findByClienteEmail(null)).isEmpty();
	}
	
	@Test
	void acharPeloEmailDoClienteEEventoId_DeveRetornarUmOptionalDeCarrinho_QuandoEmailEEventoIdEstaoCorretosEHaItensNoCarrinho() {
		Optional<Carrinho> carrinhoOptional = carrinhoRepository.findByEventoIdAndClienteEmail(1L, email);
		Assertions.assertThat(carrinhoOptional).isNotEmpty();
		Assertions.assertThat(carrinhoOptional.get().getQuantidadeIngressos()).isEqualTo(10);
	}

	@Test
	void acharPeloEmailDoClienteEEventoId_DeveRetornarUmOptionalDeCarrinhoVazio_QuandoEmailEEventoIdEstaoCorretosENaoHaItensNoCarrinho() {
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(3L, email)).isEmpty();
	}
	
	@Test
	void acharPeloEmailDoClienteEEventoId_DeveRetornarUmOptionalDeCarrinhoVazio_QuandoEmailOuEventoIdNaoExistem() {
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(-1L, email)).isEmpty();
		Assertions.assertThat(carrinhoRepository.findByEventoIdAndClienteEmail(1L, emailQueNaoExiste)).isEmpty();
	}
	
	
	
}
