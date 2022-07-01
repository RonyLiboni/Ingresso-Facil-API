package br.com.IngressoFacilAPI.services.administracao_plataforma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.repositories.administracao_plataforma.EventoRepository;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {

	private final EventoRepository eventoRepository;
	private final LocalService localService;
	private final ClienteRepository clienteRepository;

	public Page<Evento> listar(Pageable paginacao) {
		return eventoRepository.findAll(paginacao);
	}

	public Evento cadastrar(EventoForm form) {
		return salvar(converterParaEvento(form));
	}

	@Transactional
	public Evento salvar(Evento evento) {
		return eventoRepository.save(evento);
	}

	public Evento procurarPeloId(Long id) {
		return eventoRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}

	public Evento atualizarCadastro(Long id, EventoForm form) {
		Evento evento = procurarPeloId(id);
		evento.setNome(form.getNome());
		evento.setValor(form.getValor());
		evento.setTipo(form.getTipo());
		evento.setLocal(localService.procurarPeloId(form.getLocalId()));
		evento.setDataEvento(form.getDataEvento());
		evento.setHoraEvento(form.getHoraEvento());
		evento.setQuantidadeIngressos(form.getQuantidadeIngressos());
		evento.setQuantidadeIngressosVendidos(atualizaQuantidadeIngressosVendidos(id));
		evento.setQuantidadeIngressosDisponiveis(evento.getQuantidadeIngressos() - evento.getQuantidadeIngressosVendidos());
		return salvar(evento);
	}

	public void atualizarQuantidadeDeIngressosDisponiveisNosEventos(List<Carrinho> eventosDoCarrinho) {
		eventosDoCarrinho.forEach(eventoDoCarrinho -> {
			Evento evento = procurarPeloId(eventoDoCarrinho.getEventoId());
			evento.setQuantidadeIngressosDisponiveis(evento.getQuantidadeIngressosDisponiveis() - eventoDoCarrinho.getQuantidadeIngressos());
			evento.setQuantidadeIngressosVendidos(evento.getQuantidadeIngressos() - evento.getQuantidadeIngressosDisponiveis());
			salvar(evento);
		});
		
	}

	private Integer atualizaQuantidadeIngressosVendidos(Long id) {
		Integer quantidadeDeIngressosVendidosPorEvento = clienteRepository.quantidadeDeIngressosVendidosPorEvento(id);
		if(quantidadeDeIngressosVendidosPorEvento == null) {
			return 0;
		}
		return quantidadeDeIngressosVendidosPorEvento;
	}

	@Transactional
	public void deletarPeloId(Long id) {
		eventoRepository.delete(procurarPeloId(id));
	}

	public String atualizarImagemDoEvento(Long id, MultipartFile imagemEvento) throws Exception {
		if (imagemEvento.isEmpty())
			throw new FileNotFoundException("É obrigatório fazer o upload de uma imagem!");
		
		Evento evento = procurarPeloId(id);
		String caminhoImagem = criarCaminhoDaImagem(evento.getNome(),id, imagemEvento.getContentType());
		try {
			imagemEvento.transferTo(new File(caminhoImagem));
			evento.setCaminhoImagemDoEvento(caminhoImagem);
			salvar(evento);
		} catch (Exception e) {
			throw new FileUploadException("Houve um erro no servidor e não foi possível atualizar a imagem!");
		}
		return caminhoImagem;
	}

	private Evento converterParaEvento(EventoForm form) {
		return Evento.builder()
				.nome(form.getNome())
				.valor(form.getValor())
				.tipo(form.getTipo())
				.local(localService.procurarPeloId(form.getLocalId()))
				.dataEvento(form.getDataEvento())
				.horaEvento(form.getHoraEvento())
				.quantidadeIngressos(form.getQuantidadeIngressos())
				.quantidadeIngressosDisponiveis(form.getQuantidadeIngressos())
				.quantidadeIngressosVendidos(0)
				.caminhoImagemDoEvento("/imagensDosEventos/imagemPadrao.jpg")
				.build();
	}

	public Page<EventoDto> listarDto(Pageable paginacao) {
		return listar(paginacao).map(EventoDto::new);
	}

	private String criarCaminhoDaImagem(String nomeEvento, Long id, String tipoDeArquivo) throws IOException {
		return new File(".").getCanonicalPath() 
				+ "/src/main/resources/static/ImagensDosEventos/" 
				+ String.valueOf(id)+" - "+ nomeEvento 
				+ "." + tipoDeArquivo.split("/")[1];
	}

	@Override
	public String toString() {
		return "evento";
	}

}
