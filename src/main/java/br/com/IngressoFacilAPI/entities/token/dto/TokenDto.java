package br.com.IngressoFacilAPI.entities.token.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
	@ApiModelProperty(example = "51as5d1as6d1as65d1sad1", position =1)
	private String token;
	@ApiModelProperty(example = "Bearer", position =0)
	private String tipo;

}
