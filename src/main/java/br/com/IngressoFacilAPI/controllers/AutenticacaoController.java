package br.com.IngressoFacilAPI.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.IngressoFacilAPI.entities.login.form.LoginForm;
import br.com.IngressoFacilAPI.entities.token.dto.TokenDto;
import br.com.IngressoFacilAPI.services.TokenService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {

	private final TokenService tokenService;
	private final AuthenticationManager authManager;

	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form) {
		return ResponseEntity.status(HttpStatus.OK).body(new TokenDto(tokenService.criarToken(form, authManager), "Bearer"));
	}
}
