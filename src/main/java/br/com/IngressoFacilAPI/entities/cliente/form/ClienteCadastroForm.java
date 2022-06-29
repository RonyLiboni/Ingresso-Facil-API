package br.com.IngressoFacilAPI.entities.cliente.form;

import javax.validation.constraints.NotBlank;

import br.com.IngressoFacilAPI.validations.usuario.email.EmailDeveSerUnico;
import br.com.IngressoFacilAPI.validations.usuario.senha.SenhaForte;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteCadastroForm{
	@NotBlank (message = "{obrigatorio.nome}")
	@ApiModelProperty(example= "Ronald Liboni", position = 0)
	private String nome;
	@NotBlank (message = "{obrigatorio.email}")
	@EmailDeveSerUnico
	@ApiModelProperty(example= "ronaldliboni@gmail.com", position = 1)
	private String email;
	@NotBlank (message = "{obrigatorio.senha}")
	@SenhaForte
	@ApiModelProperty(example= "N@ruto2022", position = 2)
	private String senha;
	
	
	
	

}
