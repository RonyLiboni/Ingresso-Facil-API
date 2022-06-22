package br.com.IngressoFacilAPI.controllers.cadastro_de_clientes;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.cliente.dto.ClienteDto;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.services.cadastro_clientes.ClienteService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping ("/v1/cadastrarCliente")
@RequiredArgsConstructor
@EnableAsync
public class CadastroClientesController {
	private final ClienteService clienteService;
	
	@PostMapping
	@ApiOperation(value = "cadastra o cliente e cria um usuario para ele")
	public ResponseEntity<ClienteDto> cadastrarCliente(@RequestBody @Valid ClienteCadastroForm form){
		return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteDto(clienteService.cadastrarCliente(form)));
	}
}
