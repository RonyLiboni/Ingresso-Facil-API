package br.com.IngressoFacilAPI.controllers.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.entities.Local.dto.LocalDto;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.services.administracao_plataforma.LocalService;
import br.com.IngressoFacilAPI.util.Util;

class LocalControllerTest extends ControllerAndServiceTestConfig {
	@InjectMocks
	private LocalController localController;
	@Mock
	private LocalService localServiceMock;
		
	@Test
	void listar_DeveRetornarUmResponseEntityComStatusOkEUmaPageDeEventoHomeDto() {
		BDDMockito.when(localServiceMock.listar(ArgumentMatchers.any(Util.criarPageable().getClass())))
		.thenReturn(Util.criarPageDeLocal());
		
		ResponseEntity<Page<LocalDto>> pageLocaisDto = localController.listar(Util.criarPageable());
		
		assertThat(pageLocaisDto.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(pageLocaisDto.getBody().getSize()).isEqualTo(Util.criarPageDeEvento().getSize());
		assertThat(pageLocaisDto.getBody()).isExactlyInstanceOf(PageImpl.class);
		assertThat(pageLocaisDto.getBody().getContent().get(0)).isExactlyInstanceOf(LocalDto.class).isNotNull();
		assertThat(pageLocaisDto.getBody().getContent().get(0).getNome()).isEqualTo(Util.criarLocal().getNome());
	}
	
	@Test
	void cadastrar_DeveRetornarUmResponseEntityComStatusCreatedEEventoDto_QuandoRecebeUmEventoFormValidado() {
		BDDMockito.when(localServiceMock.cadastrar(ArgumentMatchers.any(LocalForm.class)))
		.thenReturn(Util.criarLocal());
		
		ResponseEntity<LocalDto> localCadastrado = localController.cadastrar(Util.criarLocalForm());
		
		assertThat(localCadastrado.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(localCadastrado.getBody()).isExactlyInstanceOf(LocalDto.class);
		assertThat(localCadastrado.getBody().getNome()).isEqualTo(Util.criarLocal().getNome());		
	}
	
	@Test
	void procurar_DeveRetornarUmResponseEntityComStatusOkEEventoDto_QuandoRecebeUmIdDeEventoValido() {
		BDDMockito.when(localServiceMock.procurarPeloId(ArgumentMatchers.anyLong()))
		.thenReturn(Util.criarLocal());
		
		ResponseEntity<LocalDto> localAchado = localController.procurar(1l);
		
		assertThat(localAchado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(localAchado.getBody()).isExactlyInstanceOf(LocalDto.class);
		assertThat(localAchado.getBody().getNome()).isEqualTo(Util.criarLocal().getNome());		
	}
	
	@Test
	void atualizar_DeveRetornarUmResponseEntityComStatusOkEEventoDto_QuandoRecebeUmIdDeEventoValido() {
		BDDMockito.when(localServiceMock.atualizarCadastro(ArgumentMatchers.anyLong(),ArgumentMatchers.any(LocalForm.class)))
		.thenReturn(Util.criarLocal());
		
		ResponseEntity<LocalDto> localAtualizado = localController.atualizar(1l, Util.criarLocalForm());
		
		assertThat(localAtualizado.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(localAtualizado.getBody()).isExactlyInstanceOf(LocalDto.class);
		assertThat(localAtualizado.getBody().getNome()).isEqualTo(Util.criarLocal().getNome());		
	}
	
	@Test
	void deletar_DeveRetornarUmResponseEntityComStatusNoContent_QuandoRecebeUmIdDeEventoValido() {		
		assertThat(localController.deletar(1l).getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
	}

}
