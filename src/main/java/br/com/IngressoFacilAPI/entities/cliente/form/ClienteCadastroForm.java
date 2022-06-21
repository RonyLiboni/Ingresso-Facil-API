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
	@ApiModelProperty(example= "ronaldliboni@gmail.com", position = 1)
	private String email;
	@NotBlank
	@SenhaForte
	@ApiModelProperty(example= "N@ruto2022", position = 2)
	private String senha;
	
	
	
	

}
