package br.com.IngressoFacilAPI.entities.carrinho.form;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarrinhoForm {
	
	@NotNull
	@ApiModelProperty(example= "1")
	private Long clienteId;
	@NotNull
	@ApiModelProperty(example= "1")
	private Long eventoId;
	@NotNull
	@ApiModelProperty(example= "2")
	private Integer quantidadeIngressos;
}
