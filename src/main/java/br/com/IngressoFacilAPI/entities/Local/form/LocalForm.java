package br.com.IngressoFacilAPI.entities.Local.form;

import javax.validation.constraints.NotBlank;

import br.com.IngressoFacilAPI.entities.Local.Local;
import br.com.IngressoFacilAPI.validations.local.NomeDeLocalUnico;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LocalForm {
	
	@NotBlank (message = "{obrigatorio.nome}")
	@NomeDeLocalUnico
	@ApiModelProperty(example = "Theatro Municipal de São Paulo", position = 0)
	private String nome;
	@NotBlank (message = "{obrigatorio.endereco}")
	@ApiModelProperty(example = "Praça Ramos de Azevedo, s/n - República, São Paulo - SP, 01037-010", position = 1)
	private String endereco;
	
	public Local converterParaLocal() {
		return Local.builder()
				.nome(this.getNome())
				.endereco(this.getEndereco())
				.build();
	}
	
	public Local converterParaLocal(Long id) {
		return Local.builder()
				.id(id)
				.nome(this.getNome())
				.endereco(this.getEndereco())
				.build();
	}
}
