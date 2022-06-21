package br.com.IngressoFacilAPI.controllers.clienteAutenticado;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.carrinho.dto.CarrinhoDto;
import br.com.IngressoFacilAPI.entities.carrinho.form.CarrinhoForm;
import br.com.IngressoFacilAPI.services.CarrinhoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/carrinho")
@RequiredArgsConstructor
public class CarrinhoController {
		
	private final CarrinhoService carrinhoService;

	@PutMapping
	@ApiOperation(value="Adiciona e retira eventos do carrinho")
	public ResponseEntity<CarrinhoDto> atualizarCarrinho(@RequestBody @Valid CarrinhoForm form, @ApiIgnore Principal emailDoCliente) {
		return ResponseEntity.status(HttpStatus.OK).body(new CarrinhoDto(carrinhoService.atualizarCarrinho(form, emailDoCliente.getName())));
	}

	@GetMapping
	@ApiOperation(value="Retorna todos eventos no carrinho do cliente autenticado")
	public ResponseEntity<List<CarrinhoDto>> procurar(@ApiIgnore Principal emailDoCliente) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(carrinhoService.retornaEventosDoCarrinho(emailDoCliente.getName()).stream().map(CarrinhoDto::new).collect(Collectors.toList()));
	}
}
