package br.com.IngressoFacilAPI.controllers.clienteNaoAutenticado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.evento.dto.EventoHomeDto;
import br.com.IngressoFacilAPI.services.EventoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/home")
@RequiredArgsConstructor
public class HomeController {
	
	private final EventoService eventoService;
	
	@GetMapping
	@ApiOperation(value="Retorna uma página com os eventos disponíveis para clientes não autenticados")
	public ResponseEntity<Page<EventoHomeDto>> listar(@ApiIgnore() @PageableDefault() Pageable paginacao) {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listar(paginacao).map(EventoHomeDto::new));
	}
}
