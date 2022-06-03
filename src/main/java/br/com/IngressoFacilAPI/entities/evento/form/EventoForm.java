package br.com.IngressoFacilAPI.entities.evento.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoForm {
	
	@NotBlank
	private String nome;
	@NotNull @Digits(fraction = 2, integer = 10)
	private BigDecimal valor;
	@NotNull
	private TipoDeEvento tipo;
	@NotNull
	private Local local;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	private LocalDate dataEvento;
	@NotNull
	private LocalTime horaEvento;
	@NotNull @Digits(fraction=0 ,integer =10)
	private Integer quantidadeIngressos;

}
