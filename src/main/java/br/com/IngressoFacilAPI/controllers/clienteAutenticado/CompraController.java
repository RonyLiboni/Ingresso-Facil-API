package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.ingresso.dto.IngressoDto;
import br.com.IngressoFacilAPI.services.CompraService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/comprar")
@RequiredArgsConstructor
public class CompraController {
	private final CompraService compraService;
	
	@PostMapping
	@ApiOperation(value="Faz o processo de compra para todos itens no carrinho")
	public ResponseEntity<List<IngressoDto>> comprarIngressos(Long IdCliente) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(compraService.processoDeCompraDeIngressos(IdCliente).stream().map(IngressoDto::new).collect(Collectors.toList()));
	}
}
