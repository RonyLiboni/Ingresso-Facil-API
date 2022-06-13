package br.com.IngressoFacilAPI.entities.carrinho.dto;

import java.util.List;
import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.Ingresso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarrinhoDto {
	private Long id;
	private Cliente cliente;
	private List<Ingresso> ingressos;
	
	public CarrinhoDto(Carrinho carrinho) {
		this.id = carrinho.getId();
		this.cliente = carrinho.getCliente();
		this.ingressos = carrinho.getIngressos();
	}
}
