package br.com.IngressoFacilAPI.entities.cliente.dto;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDto{
	private Long id;
	private String nome;
	private String email;

	public ClienteDto(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email= cliente.getEmail();
	}
}
