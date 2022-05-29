package br.com.IngressoFacilAPI.controllers;

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
import br.com.IngressoFacilAPI.entities.casaDeShow.dto.CasaDeShowDto;
import br.com.IngressoFacilAPI.entities.casaDeShow.form.CasaDeShowForm;
import br.com.IngressoFacilAPI.services.CasaDeShowService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin/casaDeShow")
@RequiredArgsConstructor
public class CasaDeShowController {

	private final CasaDeShowService casaDeShowService;

	@GetMapping
	public ResponseEntity<Page<CasaDeShowDto>> listar(@PageableDefault() Pageable paginacao) {
		return ResponseEntity.status(HttpStatus.OK).body(casaDeShowService.listar(paginacao));
	}

	@PostMapping
	public ResponseEntity<CasaDeShowDto> cadastrar(@RequestBody @Valid CasaDeShowForm form) {
		return ResponseEntity.status(HttpStatus.CREATED).body(casaDeShowService.cadastrar(form));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CasaDeShowDto> procurar(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(casaDeShowService.procurarPeloId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CasaDeShowDto> atualizar(@PathVariable Long id, @RequestBody @Valid CasaDeShowForm form) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(casaDeShowService.atualizarCadastro(id, form));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletar(@PathVariable Long id) {
		casaDeShowService.deletarPeloId(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Excluído com sucesso!");
	}
}
