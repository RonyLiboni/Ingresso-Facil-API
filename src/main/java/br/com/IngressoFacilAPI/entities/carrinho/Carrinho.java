package br.com.IngressoFacilAPI.entities.carrinho;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.Ingresso;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Carrinho {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Cliente cliente;
	@ElementCollection
	@Embedded
	private List<Ingresso> ingressos;
	 
}
