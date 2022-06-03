package br.com.IngressoFacilAPI.entities.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Local {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false, length = 150)
	private String nome;
	@Column (nullable = false, length = 250)
	private String endereco;	
	
}
