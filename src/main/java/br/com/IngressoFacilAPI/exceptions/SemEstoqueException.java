package br.com.IngressoFacilAPI.exceptions;

import java.util.List;
import br.com.IngressoFacilAPI.entities.semEstoque.dto.SemEstoqueDto;
import lombok.Getter;

@Getter
public class SemEstoqueException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private List<SemEstoqueDto> estoque;
	
	public SemEstoqueException (List<SemEstoqueDto> estoque) {
		this.estoque = estoque;
	}
}
