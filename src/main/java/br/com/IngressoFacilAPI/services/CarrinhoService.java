package br.com.IngressoFacilAPI.services;

import java.util.List;

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
	
	public Carrinho atualizarCarrinho(CarrinhoForm form) {
		Carrinho carrinho = verificaSeExisteEventoNoCarrinho(form);
		// Cliente zerou a quantidade porque não quer mais o ingresso, entao apagar evento do banco de dados OU não salvar(caso nao esteja no banco de dados) 
		if (form.getQuantidadeIngressos() <= 0) {
			carrinhoRepository.delete(carrinho);
			return Carrinho.builder()
					.clienteId(form.getClienteId())
					.eventoId(form.getEventoId())
					.quantidadeIngressos(form.getQuantidadeIngressos())
					.build();
		}

		//Existe esse Evento no carrinho do cliente, entao atualizar no banco de dados
		if (carrinhoExisteEQuantidadeDeIngressosEhMaiorQueZero(form, carrinho)) {		
			carrinho.setQuantidadeIngressos(form.getQuantidadeIngressos());
			return salvar(carrinho);
		}
		
		//Não existe esse Evento no carrinho do cliente, entao adicionar ao banco de dados
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
