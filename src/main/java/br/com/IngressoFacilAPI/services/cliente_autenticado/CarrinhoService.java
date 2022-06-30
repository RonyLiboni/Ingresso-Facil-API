package br.com.IngressoFacilAPI.services.cliente_autenticado;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.CarrinhoRepository;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
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
		
		if (form.getQuantidadeIngressos() < 1) {
			carrinhoRepository.delete(carrinho);
			return Carrinho.builder()
					.eventoId(form.getEventoId())
					.quantidadeIngressos(form.getQuantidadeIngressos())
					.clienteEmail(emailCliente)
					.build();
		}

		if (carrinho.getId() != null) {		
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
	
	private void verificaSeEventoExiste(Long eventoId) {
		eventoService.procurarPeloId(eventoId);
	}


	@Override
	public String toString() {
		return "carrinho";
	}

}
