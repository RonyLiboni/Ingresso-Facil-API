package br.com.IngressoFacilAPI.services.administracao_plataforma;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.LocalRepository;
import br.com.IngressoFacilAPI.util.Util;

class LocalServiceTest extends ServiceTestConfig{
	
	@InjectMocks
	private LocalService localService;
	@Mock
	private LocalRepository localRepositoryMock;
	

	@Test
	void listar_DeveRetornarUmaPaginaComLocais_QuandoSolicitado() {
		PageImpl<Local> localPage = new PageImpl<>(List.of(Util.criarLocal()));
	    BDDMockito.when(localRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
	                .thenReturn(localPage);
		
		Page<Local> pagina = localService.listar(PageRequest.of(1,1));
		List<Local> locais = pagina.getContent();
		assertThat(locais.get(0).getNome()).isEqualTo(Util.criarLocal().getNome());
		assertThat(pagina.getSize()).isEqualTo(1);
	}
	
	@Test
	void salvar_DeveSalvarNoBancoDeDadosERetornarOObjetoSalvo_QuandoObjetoLocalEstaValidado() {
		BDDMockito.when(localRepositoryMock.save(ArgumentMatchers.any(Local.class)))
		.thenReturn(Util.criarLocal());
		Local localSalvo = localService.salvar(Util.criarLocal());
		
		assertThat(localSalvo.getNome()).isEqualTo(Util.criarLocal().getNome());
	}
		
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

}
