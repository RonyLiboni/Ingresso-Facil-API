package br.com.IngressoFacilAPI.entities.evento.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import br.com.IngressoFacilAPI.entities.evento.TipoDeEvento;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventoForm {
	
	@NotBlank
	@ApiModelProperty(example = "Improvavel - Os Barbixas", position =0)
	private String nome;
	@NotNull
	@Digits(fraction = 2, integer = 10)
	@ApiModelProperty(example = "120.00", position =1)
	private BigDecimal valor;
	@NotNull
	@ApiModelProperty(example = "TEATRO", position =2)
	private TipoDeEvento tipo;
	@NotNull
	@ApiModelProperty(example = "3", position =3)
	private Long localId;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future
	@ApiModelProperty(example = "2022-12-12", position =4)
	private LocalDate dataEvento;
	@NotNull
	@JsonFormat(pattern = "HH:mm:ss", shape = Shape.STRING)
	@ApiModelProperty(example = "20:00:00", position =5)
	private LocalTime horaEvento;
	@NotNull
	@Digits(fraction=0 ,integer =10)
	@ApiModelProperty(example = "1000", position =6)
	private Integer quantidadeIngressos;

}
