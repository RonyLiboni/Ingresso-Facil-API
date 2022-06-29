package br.com.IngressoFacilAPI.services.administracao_plataforma;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.LocalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalService {

	private final LocalRepository localRepository;

	public Page<Local> listar(Pageable paginacao) {
		return localRepository.findAll(paginacao);
	}
	
	@Transactional
	public Local salvar(Local local) {
		return localRepository.save(local);
	}
	
	public Local cadastrar(LocalForm form) {
		return salvar(form.converterParaLocal());
	}

	public Local procurarPeloId(Long id) {
		return localRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}

	public Local atualizarCadastro(Long id, LocalForm form) {
		procurarPeloId(id);
		return salvar(form.converterParaLocal(id));
	}

	@Transactional
	public void deletarPeloId(Long id) {
		localRepository.delete(procurarPeloId(id));
	}

	@Override
	public String toString() {
		return "local";
	}
}
