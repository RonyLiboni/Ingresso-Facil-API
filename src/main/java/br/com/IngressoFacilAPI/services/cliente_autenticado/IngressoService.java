package br.com.IngressoFacilAPI.services.cliente_autenticado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.CarrinhoVazioException;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import br.com.IngressoFacilAPI.services.cadastro_clientes.ClienteService;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailSenderService;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailsTemplates;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngressoService {
	private final ClienteService clienteService;
	private final EventoService eventoService;
	private final EmailSenderService emailSenderService; 

	public List<Ingresso> transformarCarrinhoEmIngresso(List<Carrinho> eventosDoCarrinho, String emailCliente) {
		if(eventosDoCarrinho.isEmpty())
			throw new CarrinhoVazioException("Não é possivel transformar Carrinho em Ingressos, pois o carrinho está vazio");
		
		List<Ingresso> listaIngressos = new ArrayList<>();		
		eventosDoCarrinho.forEach(eventoDoCarrinho -> {
			Evento evento = eventoService.procurarPeloId(eventoDoCarrinho.getEventoId());
			listaIngressos.add(Ingresso.builder()
					.evento(evento)
					.quantidadeIngressos(eventoDoCarrinho.getQuantidadeIngressos())
					.valorIngresso(evento.getValor())
					.build());
		});		
		clienteService.adicionarIngresso(listaIngressos, emailCliente);
		emailSenderService.enviarEmail(emailCliente, EmailsTemplates.COMPRA_REALIZADA_COM_SUCESSO);
		return listaIngressos;
	}	
}
