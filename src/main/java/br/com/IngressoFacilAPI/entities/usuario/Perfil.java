package br.com.IngressoFacilAPI.entities.usuario;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Perfil implements GrantedAuthority {
	
	private static final long serialVersionUID = 1L;

	@Id 
	private String nome;

	@Override
	public String getAuthority() {
		return nome;
	}
	
}