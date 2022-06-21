package br.com.IngressoFacilAPI.entities.cliente.dto;

import java.util.List;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.ingresso.dto.IngressoDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoricoDto {
	private List<IngressoDto> ingressos;
	
	public HistoricoDto(Cliente cliente) {
		this.ingressos = cliente.getIngressos().stream().map(IngressoDto::new).toList();
	}

}
