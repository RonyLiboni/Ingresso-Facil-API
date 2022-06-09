package br.com.IngressoFacilAPI.entities.usuario;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id 
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
	
}