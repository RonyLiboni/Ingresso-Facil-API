package br.com.IngressoFacilAPI.controllers.administracao_plataforma;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import br.com.IngressoFacilAPI.entities.evento.dto.EventoDto;
import br.com.IngressoFacilAPI.entities.evento.form.EventoForm;
import br.com.IngressoFacilAPI.services.administracao_plataforma.EventoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/admin/evento")
@RequiredArgsConstructor
public class EventoController {

	private final EventoService eventoService;

	@GetMapping
	@ApiOperation(value = "Retorna uma página com os eventos cadastrados")
	public ResponseEntity<Page<EventoDto>> listar(@ApiIgnore() @PageableDefault() Pageable paginacao) {
		return ResponseEntity.status(HttpStatus.OK).body(eventoService.listar(paginacao).map(EventoDto::new));
	}

	@PostMapping
	@ApiOperation(value = "Cria um novo evento")
	public ResponseEntity<EventoDto> cadastrar(@RequestBody @Valid EventoForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new EventoDto(eventoService.cadastrar(form)));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um evento através do seu Id")
	public ResponseEntity<EventoDto> procurar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new EventoDto(eventoService.procurarPeloId(id)));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um evento através do seu Id")
	public ResponseEntity<EventoDto> atualizar(@PathVariable Long id, @RequestBody @Valid EventoForm form) {
		return ResponseEntity.status(HttpStatus.OK).body(new EventoDto(eventoService.atualizarCadastro(id, form)));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Deleta um evento através do seu Id")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		eventoService.deletarPeloId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PutMapping("/{id}/imagemEvento")
	@ApiOperation(value = "Atualiza a imagem de um evento através do seu Id")
	public ResponseEntity<String> atualizarImagemDoEvento(@PathVariable Long id,
			@RequestParam("imagemEvento") MultipartFile imagemEvento) throws Exception {
		return ResponseEntity.status(HttpStatus.OK)
				.body("Imagem salva em " + eventoService.atualizarImagemDoEvento(id, imagemEvento));
	}

}
