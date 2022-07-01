package br.com.IngressoFacilAPI.controllers.exceptions_controller;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.IngressoFacilAPI.entities.erroValidacao.dto.ErroDeFormularioDto;
import br.com.IngressoFacilAPI.entities.semEstoque.dto.SemEstoqueDto;
import br.com.IngressoFacilAPI.exceptions.CarrinhoVazioException;
import br.com.IngressoFacilAPI.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.exceptions.SemEstoqueException;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

	private final MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroDeFormularioDto>> erroDePreenchimentoDeFormulario(
			MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(SemEstoqueException.class)
	public ResponseEntity<List<SemEstoqueDto>> semEstoqueSuficienteParaCompra(SemEstoqueException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getEstoque());
	}

	@ExceptionHandler({ CarrinhoVazioException.class, AuthenticationException.class, FileNotFoundException.class,
			IllegalArgumentException.class, HttpMessageNotReadableException.class, UsernameNotFoundException.class })
	public ResponseEntity<String> exceptionsBAD_REQUEST_QueSomenteRetornamSuaMensagemDeErro(Exception exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> idNaoExiste(IdNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<String> imagemNaoFoiSalvaPorErroNoServidor(FileUploadException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> naoPodeDeletarEntidadesQueTemDependenciaComOutras(
			SQLIntegrityConstraintViolationException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body("Não é possivel deletar este recurso, pois outros dependem dele");
	}

}
