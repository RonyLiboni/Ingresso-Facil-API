package br.com.IngressoFacilAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.SemEstoqueException;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.entities.semEstoque.dto.SemEstoqueDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
	
	private final CarrinhoService carrinhoService;
	private final EventoService eventoService;
	private final IngressoService ingressoService;
	
	public List<Ingresso> processoDeCompraDeIngressos(Long idCliente) {
		List<Carrinho> eventosDoCarrinho = carrinhoService.retornaEventosDoCarrinho(idCliente);
		verificaSeHaEstoqueSuficienteParaCompra(eventosDoCarrinho);
		eventoService.atualizarQuantidadeDeIngressosDisponiveisNosEventos(eventosDoCarrinho);
		carrinhoService.apagarCarrinhoInteiro(idCliente);
		return ingressoService.transformarCarrinhoEmIngresso(eventosDoCarrinho);
	}

	private void verificaSeHaEstoqueSuficienteParaCompra(List<Carrinho> carrinho) {
		List<SemEstoqueDto> estoque = new ArrayList<SemEstoqueDto>();
		carrinho.forEach(c -> {
			if (estoqueEhMenorQuePedidoDoCliente(c))
				estoque.add(SemEstoqueDto.builder()
						.eventoId(c.getEventoId())
						.quantidadeDisponivel(quantidadeDeIngressosDisponiveisNoEvento(c))
						.build());				
			});
		if(!estoque.isEmpty())
			throw new SemEstoqueException(estoque);		
	}
	
	private boolean estoqueEhMenorQuePedidoDoCliente(Carrinho carrinho) {
		return quantidadeDeIngressosDisponiveisNoEvento(carrinho) - carrinho.getQuantidadeIngressos() < 0;
	}

	private Integer quantidadeDeIngressosDisponiveisNoEvento(Carrinho carrinho) {
		return eventoService.procurarPeloId(carrinho.getEventoId()).getQuantidadeIngressosDisponiveis();
	}
	

}
