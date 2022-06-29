package br.com.IngressoFacilAPI.entities.login.form;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LoginForm {
	
	@NotBlank (message = "{obrigatorio.email}")
	@ApiModelProperty(example = "Admin@gmail.com", position =0)
	private String email;
	@NotBlank (message = "{obrigatorio.senha}")
	@ApiModelProperty(example = "S@suke2022", position =1)
	private String senha;

}
