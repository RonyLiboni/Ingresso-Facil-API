package br.com.IngressoFacilAPI.entities.semEstoque.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SemEstoqueDto {
	private Long eventoId;
	private Integer quantidadeDisponivel;
	private Integer quantidadeSolicitada;
	@Builder.Default
	private String mensagem= "Não tem ingressos suficientes para concluir a compra solicitada!";
}
