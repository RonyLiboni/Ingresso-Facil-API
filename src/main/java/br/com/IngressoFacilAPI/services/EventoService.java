package br.com.IngressoFacilAPI.services;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

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
	
	public Page<EventoDto> listar(Pageable paginacao){
		Page<Evento> eventos = eventoRepository.findAll(paginacao);
		return converterParaDto(eventos);
	}
	
	public EventoDto cadastrar(EventoForm form) {
		Evento evento = converterParaEvento(form);	
		return new EventoDto(salvar(evento));
	}
	
	@Transactional
	public Evento salvar(Evento evento) {
		return eventoRepository.save(evento);
	}
	
	public EventoDto procurarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		return new EventoDto(eventoRepository.findById(id).get());
	}
	
	public EventoDto atualizarCadastro(Long id, EventoForm form){
		idNaoExistenteJogaException(id);
		Evento evento = converterParaEvento(form);
		evento.setId(id);
		//processo de atualização dos outros atributos
		
		
		return new EventoDto(salvar(evento));
	}
	
	@Transactional
	public void deletarPeloId(Long id) {
		idNaoExistenteJogaException(id);
		eventoRepository.deleteById(id);	
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
	
	public Evento converterParaEvento(EventoDto eventoDto) {
		Evento evento = new Evento();
		evento.setId(eventoDto.getId());
		evento.setNome(eventoDto.getNome());
		evento.setValor(eventoDto.getValor());
		evento.setTipo(eventoDto.getTipo());		
		verificaSeLocalExiste(eventoDto.getLocal());
		evento.setLocal(eventoDto.getLocal());
		evento.setDataEvento(eventoDto.getDataEvento());
		evento.setHoraEvento(eventoDto.getHoraEvento());
		evento.setQuantidadeIngressos(eventoDto.getQuantidadeIngressos());
		return evento;
	}
	
	private void verificaSeLocalExiste(Local local) {
		localService.procurarPeloId(local.getId());
	}

	private Page<EventoDto> converterParaDto(Page<Evento> eventos) {
		return eventos.map(EventoDto::new);
	}
	
	private void idNaoExistenteJogaException(Long id) {
		if (eventoRepository.existsById(id)) return;
		
		throw new IdNotFoundException("O Id do "+toString()+" informado não existe!");
	}
	
	@Override
	public String toString() {
		return "evento";
	}

	public String atualizarImagemDoEvento(Long id, MultipartFile imagemEvento) throws Exception {
		EventoDto eventoDto = procurarPeloId(id);

		String nomeImagem = imagemEvento.getOriginalFilename();
		String caminhoImagem = new File(".").getCanonicalPath() + "/src/main/resources/static/"+String.valueOf(id)+nomeImagem;
				
		try {
			imagemEvento.transferTo(new File(caminhoImagem));
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} 
		Evento evento = converterParaEvento(eventoDto);
				
		evento.setCaminhoImagemDoEvento(caminhoImagem);
		salvar(evento);
		
		return caminhoImagem;
		
	}
}
