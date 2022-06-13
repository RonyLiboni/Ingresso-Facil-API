package br.com.IngressoFacilAPI.services;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.repositories.CarrinhoRepository;
import br.com.IngressoFacilAPI.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarrinhoService {
	private final CarrinhoRepository carrinhoRepository;
	private final ClienteRepository clienteRepository;
	

	public Carrinho salvar(Carrinho carrinho) {
		return carrinhoRepository.save(carrinho);
	}
	
	public Carrinho procurarPeloIdDoCliente(Long idDoCliente) {
		return carrinhoRepository.findByCliente(clienteRepository.findById(idDoCliente).get())
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}
	
	public Carrinho cadastrar(CarrinhoForm form) {
		Carrinho carrinho = new Carrinho();
		carrinho.setCliente(clienteRepository.findById(form.getClienteId()).get());
		carrinho.setIngressos(form.getIngressos());
		return salvar(carrinho);
	}
	
	
	
	
	@Override
	public String toString() {
		return "carrinho";
	}

}
