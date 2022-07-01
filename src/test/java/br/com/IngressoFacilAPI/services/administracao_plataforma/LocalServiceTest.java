package br.com.IngressoFacilAPI.services.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import br.com.IngressoFacilAPI.ControllerAndServiceTestConfig;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.LocalRepository;
import br.com.IngressoFacilAPI.util.Util;

class LocalServiceTest extends ControllerAndServiceTestConfig{
	
	@InjectMocks
	private LocalService localService;
	@Mock
	private LocalRepository localRepositoryMock;
	@Captor
	private ArgumentCaptor<Local> captor;
			
	@Test
	void procurarPeloId_DeveRetornarUmLocal_QuandoIdExiste() {
      BDDMockito.when(localRepositoryMock.findById(ArgumentMatchers.anyLong()))
      .thenReturn(Optional.of(Util.criarLocal()));
      Local local = localService.procurarPeloId(1L);
      
      assertThat(local).isNotNull();
	}
	
	@Test
	void procurarPeloId_DeveLancarException_QuandoIdNaoExiste() {
      BDDMockito.when(localRepositoryMock.findById(ArgumentMatchers.anyLong()))
      .thenReturn(Optional.empty());

      assertThatExceptionOfType(IdNotFoundException.class).isThrownBy(() -> localService.procurarPeloId(1L));
	}
	
	@Test
	void cadastrar_DeveTransformarLocalFormEmLocal_QuandoRecebeUmLocalFormComTodosDadosObrigatoriosPreenchidos() {
		localService.cadastrar(Util.criarLocalForm());
		BDDMockito.verify(localRepositoryMock).save(captor.capture());
		Local local = captor.getValue();
	
	    assertThat(local.getNome()).isEqualTo(Util.criarLocalForm().getNome());
	    assertThat(local.getEndereco()).isEqualTo(Util.criarLocalForm().getEndereco());
	}
	
	@Test
	void atualizarCadastro_DeveTransformarLocalFormEmLocalEAtualizarCampos_QuandoRecebeUmLocalFormComTodosDadosObrigatoriosPreenchidos() {
		BDDMockito.when(localRepositoryMock.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(Util.criarLocal()));
		localService.atualizarCadastro(1L, Util.criarLocalForm());
		Mockito.verify(localRepositoryMock).save(captor.capture());
		Local local = captor.getValue();
		
		assertThat(local.getNome()).isEqualTo(Util.criarLocalForm().getNome());
	    assertThat(local.getEndereco()).isEqualTo(Util.criarLocalForm().getEndereco());
	}

}
