package br.com.IngressoFacilAPI.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.repositories.CarrinhoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
	private final CarrinhoRepository carrinhoRepository;
	private final ClienteService clienteService;
	private final EventoService eventoService;

	public Carrinho salvar(Carrinho carrinho) {
		return carrinhoRepository.save(carrinho);
	}
	
	public List<Carrinho> retornaEventosDoCarrinho(Long idDoCliente) {
		verificaSeClienteExiste(idDoCliente);
		return carrinhoRepository.findByClienteId(idDoCliente);
	}
	@Transactional
	public void apagarCarrinhoInteiro(Long idCliente) {
		retornaEventosDoCarrinho(idCliente).forEach(eventoDoCarrinho -> {
			carrinhoRepository.delete(eventoDoCarrinho);
		});
		
	}
	
/**
 * Primeiro analisa se o item ja existe no carrinho e se os Ids sao válidos.
 * Se a quantidade de ingressos foi alterada para zero, o evento deve ser apagado do carrinho.
 * Se a quantidade for maior que zero e o item existe no carrinho do cliente, entao deve atualizar a quantidade do item e salvar no banco.
 * Se nenhuma das duas opções acima foram satisfeitas, entao é criado um novo carrinho e adicionado ao banco de dados.
 * 
 * @param form
 * @return Item atualizado no carrinho
 */
	@Transactional
	public Carrinho atualizarCarrinho(CarrinhoForm form) {
		Carrinho carrinho = verificaSeExisteEventoNoCarrinho(form);
		
		if (form.getQuantidadeIngressos() <= 0) {
			carrinhoRepository.delete(carrinho);
			return Carrinho.builder()
					.clienteId(form.getClienteId())
					.eventoId(form.getEventoId())
					.quantidadeIngressos(form.getQuantidadeIngressos())
					.build();
		}

		if (carrinhoExisteEQuantidadeDeIngressosEhMaiorQueZero(form, carrinho)) {		
			carrinho.setQuantidadeIngressos(form.getQuantidadeIngressos());
			return salvar(carrinho);
		}
	
		return salvar(Carrinho.builder()
				.clienteId(form.getClienteId())
				.eventoId(form.getEventoId())
				.quantidadeIngressos(form.getQuantidadeIngressos())
				.build());
	}

	private Carrinho verificaSeExisteEventoNoCarrinho(CarrinhoForm form) {
		verificaSeEventoExiste(form.getEventoId());
		verificaSeClienteExiste(form.getClienteId());
		return carrinhoRepository.findByEventoIdAndClienteId(form.getEventoId(),form.getClienteId()).orElse(new Carrinho());
	}

	private boolean carrinhoExisteEQuantidadeDeIngressosEhMaiorQueZero(CarrinhoForm form, Carrinho carrinho) {
		return carrinho.getId() != null	&& form.getQuantidadeIngressos() > 0;
	}
	
	private void verificaSeEventoExiste(Long eventoId) {
		eventoService.procurarPeloId(eventoId);
	}

	private void verificaSeClienteExiste(Long clienteId) {
		clienteService.procurarPeloId(clienteId);	
	}

	@Override
	public String toString() {
		return "carrinho";
	}

}
