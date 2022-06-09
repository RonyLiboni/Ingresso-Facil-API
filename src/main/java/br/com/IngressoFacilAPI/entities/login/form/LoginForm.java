package br.com.IngressoFacilAPI.entities.login.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
	
	@NotBlank
	private String email;
	@NotBlank
	private String senha;
	
	public UsernamePasswordAuthenticationToken dadosLogin() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}
}
