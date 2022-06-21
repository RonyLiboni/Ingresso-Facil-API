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
	private final EventoService eventoService;

	public Carrinho salvar(Carrinho carrinho) {
		return carrinhoRepository.save(carrinho);
	}
	
	public List<Carrinho> retornaEventosDoCarrinho(String emailCliente) {
		return carrinhoRepository.findByClienteEmail(emailCliente);
	}
	@Transactional
	public void apagarCarrinhoInteiro(String emailCliente) {
		retornaEventosDoCarrinho(emailCliente).forEach(eventoDoCarrinho -> {
			carrinhoRepository.delete(eventoDoCarrinho);
		});
	}
	
	@Transactional
	public Carrinho atualizarCarrinho(CarrinhoForm form, String emailCliente) {
		Carrinho carrinho = verificaSeExisteEventoNoCarrinho(form, emailCliente);
		
		if (form.getQuantidadeIngressos() <= 0) {
			carrinhoRepository.delete(carrinho);
			return Carrinho.builder()
					.eventoId(form.getEventoId())
					.quantidadeIngressos(form.getQuantidadeIngressos())
					.clienteEmail(emailCliente)
					.build();
		}

		if (carrinhoExisteEQuantidadeDeIngressosEhMaiorQueZero(form, carrinho)) {		
			carrinho.setQuantidadeIngressos(form.getQuantidadeIngressos());
			return salvar(carrinho);
		}
	
		return salvar(Carrinho.builder()
				.eventoId(form.getEventoId())
				.quantidadeIngressos(form.getQuantidadeIngressos())
				.clienteEmail(emailCliente)
				.build());
	}

	private Carrinho verificaSeExisteEventoNoCarrinho(CarrinhoForm form, String emailCliente) {
		verificaSeEventoExiste(form.getEventoId());
		return carrinhoRepository.findByEventoIdAndClienteEmail(form.getEventoId(), emailCliente).orElse(new Carrinho());
	}

	private boolean carrinhoExisteEQuantidadeDeIngressosEhMaiorQueZero(CarrinhoForm form, Carrinho carrinho) {
		return carrinho.getId() != null	&& form.getQuantidadeIngressos() > 0;
	}
	
	private void verificaSeEventoExiste(Long eventoId) {
		eventoService.procurarPeloId(eventoId);
	}


	@Override
	public String toString() {
		return "carrinho";
	}

}
