package br.com.IngressoFacilAPI.entities.casaDeShow.dto;

import br.com.IngressoFacilAPI.entities.casaDeShow.CasaDeShow;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CasaDeShowDto {
	
	private Long id;
	private String nome;
	private String endereco;

	public CasaDeShowDto(CasaDeShow casaDeShow) {
		this.id = casaDeShow.getId();
		this.nome = casaDeShow.getNome();
		this.endereco = casaDeShow.getEndereco();
	}

}
