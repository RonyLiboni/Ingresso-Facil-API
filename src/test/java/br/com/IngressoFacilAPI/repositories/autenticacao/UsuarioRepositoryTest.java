package br.com.IngressoFacilAPI.repositories.autenticacao;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;

class UsuarioRepositoryTest extends RepositoryTestConfig{

	@Autowired
	private UsuarioRepository usuarioRepository;
	private final String email = "admin@gmail.com";
	private final String emailQueNaoExiste= "nao existe";
	
	@Test
	void existePeloEmail_DeveRetornarTrue_QuandoNomeExisteNoBanco() {
		Assertions.assertThat(usuarioRepository.existsByEmail(email)).isEqualTo(true);
	}
	
	@Test
	void existePeloEmail_DeveRetornarFalse_QuandoNomeNaoExisteNoBanco() {
		Assertions.assertThat(usuarioRepository.existsByEmail(emailQueNaoExiste)).isEqualTo(false);
	}
	
	@Test
	void existePeloEmail_DeveRetornarFalse_QuandoNomeEhNulo() {
		Assertions.assertThat(usuarioRepository.existsByEmail(null)).isEqualTo(false);
	}
	
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalComUsuario_QuandoEmailExiste() {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Assertions.assertThat(usuarioOptional).isNotEmpty();
		Assertions.assertThat(usuarioOptional.get().getEmail()).isEqualTo(email);
	}
	
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalVazio_QuandoEmailNaoExiste() {
		Assertions.assertThat(usuarioRepository.findByEmail(emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void acharPeloEmail_DeveRetornarUmOptionalVazio_QuandoEmailEhNulo() {
		Assertions.assertThat(usuarioRepository.findByEmail(null)).isEmpty();
	}

}
