package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.carrinho.dto.CarrinhoDto;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.services.CarrinhoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {
		
	private final CarrinhoService carrinhoService;

	@PostMapping
	@ApiOperation(value="Adiciona ingressos ao carrinho")
	public ResponseEntity<CarrinhoDto> cadastrar(@RequestBody @Valid CarrinhoForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new CarrinhoDto(carrinhoService.cadastrar(form)));
	}

	@GetMapping("/{id}")
	@ApiOperation(value="Retorna um carrinho através do Id do cliente")
	public ResponseEntity<CarrinhoDto> procurar(@PathVariable Long idDoCliente) {
		return ResponseEntity.status(HttpStatus.OK).body(new CarrinhoDto(carrinhoService.procurarPeloIdDoCliente(idDoCliente)));
	}


}
