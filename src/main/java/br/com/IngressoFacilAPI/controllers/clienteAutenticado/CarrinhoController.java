package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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

	@PutMapping
	@ApiOperation(value="Adiciona e retira eventos do carrinho")
	public ResponseEntity<CarrinhoDto> atualizarCarrinho(@RequestBody @Valid CarrinhoForm form) {
		return ResponseEntity.status(HttpStatus.OK).body(new CarrinhoDto(carrinhoService.atualizarCarrinho(form)));
	}

	@GetMapping("/{idDoCliente}")
	@ApiOperation(value="Retorna todos eventos no carrinho através do Id do cliente")
	public ResponseEntity<List<CarrinhoDto>> procurar(@PathVariable Long idDoCliente) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(carrinhoService.retornaEventosDoCarrinho(idDoCliente).stream().map(CarrinhoDto::new).collect(Collectors.toList()));
	}
}
