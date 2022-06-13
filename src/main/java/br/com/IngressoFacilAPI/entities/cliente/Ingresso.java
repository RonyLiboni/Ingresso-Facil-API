package br.com.IngressoFacilAPI.entities.cliente;

import java.math.BigDecimal;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import br.com.IngressoFacilAPI.entities.evento.Evento;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Ingresso {
	
	@ManyToOne
	private Evento evento;
	private BigDecimal valorIngresso;
	private Integer quantidadeIngressosComprados;

}
