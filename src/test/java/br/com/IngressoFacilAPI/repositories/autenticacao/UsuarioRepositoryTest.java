package br.com.IngressoFacilAPI.repositories.autenticacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import br.com.IngressoFacilAPI.repositories.RepositoryTestConfig;
import br.com.IngressoFacilAPI.util.Util;

class UsuarioRepositoryTest extends RepositoryTestConfig{

	@Autowired
	private UsuarioRepository usuarioRepository;
	private final String email = "teste@teste.com";
	private final String emailQueNaoExiste= "nao existe";
	
	@Test
	void existsByEmail_DeveRetornarTrue_QuandoNomeExisteNoBanco() {
		Assertions.assertThat(usuarioRepository.existsByEmail(email)).isEqualTo(true);
	}
	
	@Test
	void existsByEmail_DeveRetornarFalse_QuandoNomeNaoExisteNoBanco() {
		Assertions.assertThat(usuarioRepository.existsByEmail(emailQueNaoExiste)).isEqualTo(false);
	}
	
	@Test
	void existsByEmail_DeveRetornarFalse_QuandoNomeEhNulo() {
		Assertions.assertThat(usuarioRepository.existsByEmail(null)).isEqualTo(false);
	}
	
	@Test
	void findByEmail_DeveRetornarUmOptionalComUsuario_QuandoEmailExiste() {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Assertions.assertThat(usuarioOptional).isNotEmpty();
		Assertions.assertThat(usuarioOptional.get().getEmail()).isEqualTo(email);
	}
	
	@Test
	void findByEmail_DeveRetornarUmOptionalVazio_QuandoEmailNaoExiste() {
		Assertions.assertThat(usuarioRepository.findByEmail(emailQueNaoExiste)).isEmpty();
	}
	
	@Test
	void findByEmail_DeveRetornarUmOptionalVazio_QuandoEmailEhNulo() {
		Assertions.assertThat(usuarioRepository.findByEmail(null)).isEmpty();
	}
	@Test
	void findById_DeveRetornarUmUsuario_QuandoRecebeUmIdQueExiste() {
		assertThat(usuarioRepository.findById(2L).get().getEmail()).isEqualTo(email);
	}
	
	@Test
	void findById_DeveRetornarUmOptionalVazio_QuandoRecebeUmIdQueNaoExiste() {
		assertThat(usuarioRepository.findById(-1L).isEmpty()).isEqualTo(true);
	}
	
	@Test
	void save_DeveSalvarUmUsuarioNoBancoDeDados_QuandoReceberUmUsuarioComTodosAtributosPreenchidos() {
		Usuario localSalvo = usuarioRepository.save(Util.criarUsuario());
		assertThat(localSalvo.getId()).isNotNull();
	}
	
	@Test
	void save_DeveLancarException_QuandoReceberUmUsuarioComAlgumAtributoNulo() {
		assertThatExceptionOfType(DataIntegrityViolationException.class).isThrownBy(()-> usuarioRepository.save(new Usuario()));
	}
	
}
