package br.com.IngressoFacilAPI.entities.cliente.form;

import javax.validation.constraints.NotBlank;

import br.com.IngressoFacilAPI.validations.usuario.email.EmailDeveSerUnico;
import br.com.IngressoFacilAPI.validations.usuario.senha.SenhaForte;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCadastroForm{
	@NotBlank
	@ApiModelProperty(example= "Ronald Liboni", position = 0)
	private String nome;
	@NotBlank
	@EmailDeveSerUnico
	@ApiModelProperty(example= "ronald.liboni@acad.pucrs.br", position = 1)
	private String email;
	@NotBlank
	@SenhaForte
	@ApiModelProperty(example= "N4rut0@d4t3Ba1o", position = 2)
	private String senha;
	
	
	
	

}
