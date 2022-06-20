package br.com.IngressoFacilAPI.services;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocalService {

	private final LocalRepository localRepository;

	public Page<Local> listar(Pageable paginacao) {
		return localRepository.findAll(paginacao);
	}

	public Local cadastrar(LocalForm form) {
		return salvar(converterParaLocal(form));
	}

	@Transactional
	public Local salvar(Local local) {
		return localRepository.save(local);
	}

	public Local procurarPeloId(Long id) {
		return localRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}

	public Local atualizarCadastro(Long id, LocalForm form) {
		Local local = procurarPeloId(id);
		local.setNome(form.getNome());
		local.setEndereco(form.getEndereco());
		return salvar(local);
	}

	@Transactional
	public void deletarPeloId(Long id) {
		localRepository.delete(procurarPeloId(id));
	}

	private Local converterParaLocal(LocalForm form) {
		Local local = new Local();
		local.setNome(form.getNome());
		local.setEndereco(form.getEndereco());
		return local;
	}

	public void idNaoExistenteJogaException(Long id) {
		if (localRepository.existsById(id))
			return;
		throw new IdNotFoundException("O Id do " + toString() + " informado não existe!");
	}

	@Override
	public String toString() {
		return "local";
	}
}
