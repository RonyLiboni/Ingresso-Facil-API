package br.com.IngressoFacilAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngressoService {
	private final ClienteService clienteService;
	private final EventoService eventoService;
	private final EmailSenderService emailSenderService; 

	public List<Ingresso> transformarCarrinhoEmIngresso(List<Carrinho> eventosDoCarrinho, String emailCliente) {
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
		emailSenderService.enviarEmail(emailCliente,
				"A compra dos seus ingressos foi realizada com sucesso!",
				"Olá, email para sinalizar que sua compra dos seus ingressos foi realizada com sucesso!" +
				"\n https://github.com/RonyLiboni/Ingresso-Facil-API.git");
		return listaIngressos;
	}	
}
