package br.com.IngressoFacilAPI.entities.carrinho.dto;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
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
