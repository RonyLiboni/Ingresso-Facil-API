package br.com.IngressoFacilAPI.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.transaction.Transactional;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.repositories.EventoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {

	private final EventoRepository eventoRepository;
	private final LocalService localService;

	public Page<EventoDto> listar(Pageable paginacao) {
		return converterParaDto(eventoRepository.findAll(paginacao));
	}

	public EventoDto cadastrar(EventoForm form) {
		return new EventoDto(salvar(converterParaEvento(form)));
	}

	@Transactional
	public Evento salvar(Evento evento) {
		return eventoRepository.save(evento);
	}

	public EventoDto procurarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		return new EventoDto(eventoRepository.findById(id).get());
	}

	public EventoDto atualizarCadastro(Long id, EventoForm form) {
		idNaoExistenteJogaException(id);
		Evento evento = converterParaEvento(form);
		evento.setId(id);
		// processo de atualização dos outros atributos

		return new EventoDto(salvar(evento));
	}

	@Transactional
	public void deletarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		eventoRepository.deleteById(id);
	}

	public String atualizarImagemDoEvento(Long id, MultipartFile imagemEvento) throws Exception {
		idNaoExistenteJogaException(id);
		if (imagemEvento.isEmpty())
			throw new FileNotFoundException("É obrigatório fazer o upload de uma imagem!");

		String caminhoImagem = criarCaminhoDaImagem(id, imagemEvento.getContentType());
		try {
			imagemEvento.transferTo(new File(caminhoImagem));
			atualizarCaminhodaImagem(id, caminhoImagem);
		} catch (Exception e) {
			throw new FileUploadException("Houve um erro no servidor e não foi possível atualizar a imagem!");
		}
		return caminhoImagem;
	}

	private Evento converterParaEvento(EventoForm form) {
		Evento evento = new Evento();
		evento.setNome(form.getNome());
		evento.setValor(form.getValor());
		evento.setTipo(form.getTipo());
		verificaSeLocalExiste(form.getLocal());
		evento.setLocal(form.getLocal());
		evento.setDataEvento(form.getDataEvento());
		evento.setHoraEvento(form.getHoraEvento());
		evento.setQuantidadeIngressos(form.getQuantidadeIngressos());
		return evento;
	}

	private void verificaSeLocalExiste(Local local) {
		localService.procurarPeloId(local.getId());
	}

	private Page<EventoDto> converterParaDto(Page<Evento> eventos) {
		return eventos.map(EventoDto::new);
	}

	private void idNaoExistenteJogaException(Long id) {
		if (eventoRepository.existsById(id))
			return;

		throw new IdNotFoundException("O Id do " + toString() + " informado não existe!");
	}

	@Transactional
	private void atualizarCaminhodaImagem(Long id, String caminhoImagem) {
		Evento evento = eventoRepository.findById(id).get();
		evento.setCaminhoImagemDoEvento(caminhoImagem);
		salvar(evento);
	}

	private String criarCaminhoDaImagem(Long id, String tipoDeArquivo) throws IOException {
		return new File(".").getCanonicalPath() 
				+ "/src/main/resources/static/ImagensDosEventos/" 
				+ String.valueOf(id)
				+ "." + tipoDeArquivo.split("/")[1];
	}

	@Override
	public String toString() {
		return "evento";
	}

}
