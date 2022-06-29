package br.com.IngressoFacilAPI.entities.carrinho.form;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoForm {
	
	@NotNull(message = "{obrigatorio.id}")
	@ApiModelProperty(example= "1")
	private Long eventoId;
	@NotNull(message = "{obrigatorio.quantidade.ingressos}")
	@ApiModelProperty(example= "2")
	private Integer quantidadeIngressos;
}
