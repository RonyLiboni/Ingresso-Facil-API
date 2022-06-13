package br.com.IngressoFacilAPI.entities.evento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.IngressoFacilAPI.entities.Local.Local;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Evento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoDeEvento tipo;
	@ManyToOne (fetch = FetchType.EAGER)
	private Local local;
	private LocalDate dataEvento;
	private LocalTime horaEvento;
	private Integer quantidadeIngressos;
	private Integer quantidadeIngressosVendidos=0;
	private Integer quantidadeIngressosDisponiveis=this.quantidadeIngressos;
	private String caminhoImagemDoEvento;

}
