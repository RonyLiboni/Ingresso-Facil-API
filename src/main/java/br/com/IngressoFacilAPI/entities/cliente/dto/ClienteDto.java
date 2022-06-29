package br.com.IngressoFacilAPI.entities.cliente.dto;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
