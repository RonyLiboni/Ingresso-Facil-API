package br.com.IngressoFacilAPI.entities.Local.dto;

import br.com.IngressoFacilAPI.entities.Local.Local;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocalDto {
	
	private Long id;
	private String nome;
	private String endereco;

	public LocalDto(Local casaDeShow) {
		this.id = casaDeShow.getId();
		this.nome = casaDeShow.getNome();
		this.endereco = casaDeShow.getEndereco();
	}

}
