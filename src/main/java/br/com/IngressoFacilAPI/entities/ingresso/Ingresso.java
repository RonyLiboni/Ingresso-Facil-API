package br.com.IngressoFacilAPI.entities.ingresso;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.com.IngressoFacilAPI.entities.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ingresso {
	
	@ManyToOne
	private Evento evento;
	private BigDecimal valorIngresso;
	private Integer quantidadeIngressos;
}
