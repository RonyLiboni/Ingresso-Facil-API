package br.com.IngressoFacilAPI.entities.casaDeShow.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasaDeShowForm {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	
}
