package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.ingresso.dto.IngressoDto;
import br.com.IngressoFacilAPI.services.CompraService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/comprar")
@RequiredArgsConstructor
@EnableAsync
public class CompraController {
	private final CompraService compraService;
	
	@PostMapping
	@ApiOperation(value="Faz o processo de compra para todos itens no carrinho")
	public ResponseEntity<List<IngressoDto>> comprarIngressos(@ApiIgnore Principal emailCliente) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(compraService.processoDeCompraDeIngressos(emailCliente.getName()).stream().map(IngressoDto::new).collect(Collectors.toList()));
	}
}
