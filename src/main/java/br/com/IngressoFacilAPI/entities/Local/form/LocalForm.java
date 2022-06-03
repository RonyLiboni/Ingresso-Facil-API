package br.com.IngressoFacilAPI.entities.Local.form;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocalForm {
	
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	
}
