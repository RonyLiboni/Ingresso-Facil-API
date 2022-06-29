package br.com.IngressoFacilAPI.entities.ingresso.dto;

import java.math.BigDecimal;

import br.com.IngressoFacilAPI.entities.evento.dto.EventoIngressoDto;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IngressoDto {

	private EventoIngressoDto evento;
	private BigDecimal valorIngresso;
	private Integer quantidadeIngressos;
	
	public IngressoDto(Ingresso ingresso) {
		this.evento = new EventoIngressoDto(ingresso.getEvento());
		this.valorIngresso = ingresso.getValorIngresso();
		this.quantidadeIngressos = ingresso.getQuantidadeIngressos();
	}
}
