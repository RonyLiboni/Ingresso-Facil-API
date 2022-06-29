package br.com.IngressoFacilAPI.entities.semEstoque.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SemEstoqueDto {
	private Long eventoId;
	private Integer quantidadeDisponivel;
	private Integer quantidadeSolicitada;
	@Builder.Default
	private String mensagem= "Não tem ingressos suficientes para concluir a compra solicitada!";
}
