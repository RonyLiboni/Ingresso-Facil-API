package br.com.IngressoFacilAPI.repositories.administracao_plataforma;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;

class LocalRepositoryTest extends RepositoryTestConfig{
	
	@Autowired
	private LocalRepository localRepository;
	private final String nome = "FIERGS";
	private final String nomeQueNaoExiste= "Nome que nao existe";
	
	@Test
	void existePeloNome_DeveRetornarTrue_QuandoNomeExisteNoBanco() {
		Assertions.assertThat(localRepository.existsByNome(nome)).isEqualTo(true);
	}
	
	@Test
	void existePeloNome_DeveRetornarFalse_QuandoNomeNaoExisteNoBanco() {
		Assertions.assertThat(localRepository.existsByNome(nomeQueNaoExiste)).isEqualTo(false);
	}
	
	@Test
	void existePeloNome_DeveRetornarFalse_QuandoNomeEhNulo() {
		Assertions.assertThat(localRepository.existsByNome(null)).isEqualTo(false);
	}

}
