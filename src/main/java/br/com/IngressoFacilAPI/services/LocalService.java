package br.com.IngressoFacilAPI.services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.Local.dto.LocalDto;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalService {
	
	private final LocalRepository localRepository;
	
	public Page<LocalDto> listar(Pageable paginacao){
		return converterParaDto(localRepository.findAll(paginacao));
	}
	
	public LocalDto cadastrar(LocalForm form) {	
		return new LocalDto(salvar(converterParaCasaDeShow(form)));
	}
	
	@Transactional
	public Local salvar(Local casa) {
		return localRepository.save(casa);
	}
	
	public LocalDto procurarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		return new LocalDto(localRepository.findById(id).get());
	}
	
	public Local retornaOLocalPeloId(Long id) {
		idNaoExistenteJogaException(id);
		return localRepository.findById(id).get();
	}
	
	public LocalDto atualizarCadastro(Long id, LocalForm form){
		idNaoExistenteJogaException(id);
		Local casa = converterParaCasaDeShow(form);
		casa.setId(id);
		return new LocalDto(salvar(casa));
	}
	
	@Transactional
	public void deletarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		localRepository.deleteById(id);	
	}
	
	private Local converterParaCasaDeShow(LocalForm form) {
		Local casa = new Local();
		casa.setNome(form.getNome());
		casa.setEndereco(form.getEndereco());
		return casa;
	}
	
	private Page<LocalDto> converterParaDto(Page<Local> casas) {
		return casas.map(LocalDto::new);
	}
	
	public void idNaoExistenteJogaException(Long id) {
		if (localRepository.existsById(id)) return;
		
		throw new IdNotFoundException("O Id do "+toString()+" informado não existe!");
	}
	
	@Override
	public String toString() {
		return "local";
	}
}
