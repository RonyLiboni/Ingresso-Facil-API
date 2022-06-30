package br.com.IngressoFacilAPI.services.cliente_autenticado;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.SemEstoqueException;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.entities.semEstoque.dto.SemEstoqueDto;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompraService {
	
	private final CarrinhoService carrinhoService;
	private final EventoService eventoService;
	private final IngressoService ingressoService;
	
	public List<Ingresso> processoDeCompraDeIngressos(String emailCliente) {
		List<Carrinho> eventosDoCarrinho = carrinhoService.retornaEventosDoCarrinho(emailCliente);
		verificaSeHaEstoqueSuficienteParaCompra(eventosDoCarrinho);
		eventoService.atualizarQuantidadeDeIngressosDisponiveisNosEventos(eventosDoCarrinho);
		carrinhoService.apagarCarrinhoInteiro(emailCliente);
		return ingressoService.transformarCarrinhoEmIngresso(eventosDoCarrinho, emailCliente);
	}

	private void verificaSeHaEstoqueSuficienteParaCompra(List<Carrinho> carrinho) {
		List<SemEstoqueDto> estoque = new ArrayList<SemEstoqueDto>();
		carrinho.forEach(c -> {
			if (estoqueEhMenorQuePedidoDoCliente(c))
				estoque.add(SemEstoqueDto.builder()
						.eventoId(c.getEventoId())
						.quantidadeDisponivel(quantidadeDeIngressosDisponiveisNoEvento(c))
						.quantidadeSolicitada(c.getQuantidadeIngressos())
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
