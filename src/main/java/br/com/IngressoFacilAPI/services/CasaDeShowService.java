package br.com.IngressoFacilAPI.services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.casaDeShow.CasaDeShow;
import br.com.IngressoFacilAPI.entities.casaDeShow.dto.CasaDeShowDto;
import br.com.IngressoFacilAPI.entities.casaDeShow.form.CasaDeShowForm;
import br.com.IngressoFacilAPI.repositories.CasaDeShowRepository;
import lombok.Data;

@Service
@Data
public class CasaDeShowService {
	
	private final CasaDeShowRepository casaDeShowRepository;
	
	public Page<CasaDeShowDto> listar(Pageable paginacao){
		Page<CasaDeShow> casas = casaDeShowRepository.findAll(paginacao);
		return CasaDeShowDto.converter(casas);
	}
	
	@Transactional
	public CasaDeShow salvar(CasaDeShow casa) {
		return casaDeShowRepository.save(casa);
		
	}
	
	public CasaDeShowDto cadastrar(CasaDeShowForm form) {
		CasaDeShow casa = converterParaCasaDeShow(form);	
		return new CasaDeShowDto(salvar(casa));
	}

	public CasaDeShowDto procurarPeloId(Long id) {
		return new CasaDeShowDto(casaDeShowRepository.findById(id).orElseThrow());
	}
	
	private CasaDeShow converterParaCasaDeShow(CasaDeShowForm form) {
		CasaDeShow casa = new CasaDeShow();
		casa.setNome(form.getNome());
		casa.setEndereco(form.getEndereco());
		return casa;
	}

	public CasaDeShowDto atualizarCadastro(Long id, CasaDeShowForm form) {
		procurarPeloId(id); // Se o id não existir, será lançada uma exceção!
		CasaDeShow casa = converterParaCasaDeShow(form);
		casa.setId(id);
		return new CasaDeShowDto(salvar(casa));
	}
	
	@Transactional
	public void deletarPeloId(Long id) {
		procurarPeloId(id); // Se o id não existir, será lançada uma exceção!
		casaDeShowRepository.deleteById(id);	
	}
	
}
