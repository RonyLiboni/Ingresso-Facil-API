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

	public List<Ingresso> transformarCarrinhoEmIngresso(List<Carrinho> eventosDoCarrinho) {
		List<Ingresso> listaIngressos = new ArrayList<>();
		eventosDoCarrinho.forEach(eventoDoCarrinho -> {
			Evento evento = eventoService.procurarPeloId(eventoDoCarrinho.getEventoId());
			listaIngressos.add(Ingresso.builder()
					.evento(evento)
					.quantidadeIngressosComprados(eventoDoCarrinho.getQuantidadeIngressos())
					.valorIngresso(evento.getValor())
					.build());
		});
		clienteService.adicionarIngresso(listaIngressos, eventosDoCarrinho.get(0).getClienteId());
		return listaIngressos;
	}
	
	
	
}
