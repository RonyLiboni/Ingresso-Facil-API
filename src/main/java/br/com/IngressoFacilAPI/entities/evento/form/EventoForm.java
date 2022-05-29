package br.com.IngressoFacilAPI.entities.evento.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.IngressoFacilAPI.entities.casaDeShow.CasaDeShow;
import br.com.IngressoFacilAPI.entities.evento.GeneroMusical;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoForm {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nomeEvento;
	@NotNull @Digits(fraction = 2, integer = 10)
	private BigDecimal valorIngresso;
	@Enumerated(EnumType.STRING)
	private GeneroMusical generoMusical;
	@ManyToOne (fetch = FetchType.LAZY)
	private CasaDeShow casaDeShow;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate dataEvento;
	@NotNull
	private LocalTime horaEvento;
	@NotNull @Digits(fraction=0 ,integer =10)
	private Integer quantidadeIngressos;
	private Integer quantidadeIngressosVendidos=0;
	@Min(value=0)
	private Integer quantidadeIngressosDisponiveis;
	private String imagemDoEvento;
}
