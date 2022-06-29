package br.com.IngressoFacilAPI.entities.erroValidacao.dto;

import lombok.Getter;

@Getter
public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;
	
	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}	

}
