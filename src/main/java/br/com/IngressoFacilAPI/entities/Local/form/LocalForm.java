package br.com.IngressoFacilAPI.entities.Local.form;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalForm {
	
	@NotBlank
	@ApiModelProperty(example = "Theatro Municipal de São Paulo", position = 0)
	private String nome;
	@NotBlank
	@ApiModelProperty(example = "Praça Ramos de Azevedo, s/n - República, São Paulo - SP, 01037-010", position = 1)
	private String endereco;
	
}
