package br.com.IngressoFacilAPI.entities.cliente.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.ingresso.dto.IngressoDto;
import lombok.Data;

@Data
public class HistoricoDto {
	private List<IngressoDto> ingressos;
	
	public HistoricoDto(Cliente cliente) {
		this.ingressos = cliente.getIngressos().stream().map(IngressoDto::new).collect(Collectors.toList());
	}

}
