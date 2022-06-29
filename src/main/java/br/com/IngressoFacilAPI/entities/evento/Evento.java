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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
	private Integer quantidadeIngressosVendidos;
	private Integer quantidadeIngressosDisponiveis;
	private String caminhoImagemDoEvento;

}
