package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.cliente.dto.HistoricoDto;
import br.com.IngressoFacilAPI.services.ClienteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/historico")
public class HistoricoController {
	private final ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value="Retorna todos ingressos comprados através do Id do cliente")
	public ResponseEntity<HistoricoDto> retornaHistoricoDeComprasDoCliente(Long clienteId){
		return ResponseEntity.status(HttpStatus.OK).body(new HistoricoDto(clienteService.procurarPeloId(clienteId)));
	}
	
}
