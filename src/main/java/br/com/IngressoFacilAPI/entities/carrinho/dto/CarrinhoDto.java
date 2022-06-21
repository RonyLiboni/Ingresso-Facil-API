package br.com.IngressoFacilAPI.entities.carrinho.dto;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarrinhoDto {
	private Long id;
	private Long eventoId;
	private Integer quantidadeIngressos;
	
	public CarrinhoDto(Carrinho carrinho) {
		this.id = carrinho.getId();
		this.eventoId = carrinho.getEventoId();
		this.quantidadeIngressos = carrinho.getQuantidadeIngressos();
	}
}
