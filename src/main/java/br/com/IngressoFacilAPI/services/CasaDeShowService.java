package br.com.IngressoFacilAPI.services;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.IngressoFacilAPI.entities.casaDeShow.CasaDeShow;
import br.com.IngressoFacilAPI.entities.casaDeShow.dto.CasaDeShowDto;
import br.com.IngressoFacilAPI.entities.casaDeShow.form.CasaDeShowForm;
import br.com.IngressoFacilAPI.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.repositories.CasaDeShowRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CasaDeShowService {
	
	private final CasaDeShowRepository casaDeShowRepository;
	
	public Page<CasaDeShowDto> listar(Pageable paginacao){
		Page<CasaDeShow> casas = casaDeShowRepository.findAll(paginacao);
		return converterParaDto(casas);
	}
	
	public CasaDeShowDto cadastrar(CasaDeShowForm form) {
		CasaDeShow casa = converterParaCasaDeShow(form);	
		return new CasaDeShowDto(salvar(casa));
	}
	
	@Transactional
	public CasaDeShow salvar(CasaDeShow casa) {
		return casaDeShowRepository.save(casa);
	}
	
	public CasaDeShowDto procurarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		return new CasaDeShowDto(casaDeShowRepository.findById(id).get());
	}
	
	public CasaDeShowDto atualizarCadastro(Long id, CasaDeShowForm form){
		idNaoExistenteJogaException(id);
		CasaDeShow casa = converterParaCasaDeShow(form);
		casa.setId(id);
		return new CasaDeShowDto(salvar(casa));
	}
	
	@Transactional
	public void deletarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		casaDeShowRepository.deleteById(id);	
	}
	
	private CasaDeShow converterParaCasaDeShow(CasaDeShowForm form) {
		CasaDeShow casa = new CasaDeShow();
		casa.setNome(form.getNome());
		casa.setEndereco(form.getEndereco());
		return casa;
	}
	
	private Page<CasaDeShowDto> converterParaDto(Page<CasaDeShow> casas) {
		return casas.map(CasaDeShowDto::new);
	}
	
	private void idNaoExistenteJogaException(Long id) {
		if (casaDeShowRepository.existsById(id)) return;
		
		throw new IdNotFoundException("O Id informado não existe!");
	}
	
}
