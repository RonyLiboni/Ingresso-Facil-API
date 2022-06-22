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
import org.springframework.web.bind.annotation.RestController;

import br.com.IngressoFacilAPI.entities.Local.dto.LocalDto;
import br.com.IngressoFacilAPI.entities.Local.form.LocalForm;
import br.com.IngressoFacilAPI.services.administracao_plataforma.LocalService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/v1/admin/local")
@RequiredArgsConstructor
public class LocalController {

	private final LocalService localService;

	@GetMapping
	@ApiOperation(value = "Retorna uma página com os locais cadastrados")
	public ResponseEntity<Page<LocalDto>> listar(@ApiIgnore() @PageableDefault() Pageable paginacao) {
		return ResponseEntity.status(HttpStatus.OK).body(localService.listar(paginacao).map(LocalDto::new));
	}

	@PostMapping
	@ApiOperation(value = "Cria um novo local")
	public ResponseEntity<LocalDto> cadastrar(@RequestBody @Valid LocalForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(new LocalDto(localService.cadastrar(form)));
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Retorna um local através do seu Id")
	public ResponseEntity<LocalDto> procurar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new LocalDto(localService.procurarPeloId(id)));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Atualiza um local através do seu Id")
	public ResponseEntity<LocalDto> atualizar(@PathVariable Long id, @RequestBody @Valid LocalForm form) {
		return ResponseEntity.status(HttpStatus.OK).body(new LocalDto(localService.atualizarCadastro(id, form)));
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Exclui um local através do seu Id")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		localService.deletarPeloId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
