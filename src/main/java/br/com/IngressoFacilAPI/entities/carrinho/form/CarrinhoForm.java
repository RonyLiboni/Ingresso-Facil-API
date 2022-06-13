package br.com.IngressoFacilAPI.entities.carrinho.form;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.IngressoFacilAPI.entities.cliente.Ingresso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CarrinhoForm {
	
	@NotNull
	private Long clienteId;
	@NotNull
	private List<Ingresso> ingressos;
}
