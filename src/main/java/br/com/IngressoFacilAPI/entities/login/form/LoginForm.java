package br.com.IngressoFacilAPI.entities.login.form;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	
	@NotBlank
	@ApiModelProperty(example = "Admin@gmail.com", position =0)
	private String email;
	@NotBlank
	@ApiModelProperty(example = "S@suke2022", position =1)
	private String senha;

}
