package br.com.IngressoFacilAPI.config.exceptionHandler;

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

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.CarrinhoVazioException;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.SemEstoqueException;
import br.com.IngressoFacilAPI.entities.erroValidacao.dto.ErroDeFormularioDto;
import br.com.IngressoFacilAPI.entities.semEstoque.dto.SemEstoqueDto;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler {

	private final MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErroDeFormularioDto>> erroDePreenchimentoDeFormulario(MethodArgumentNotValidException exception) {
		List<ErroDeFormularioDto> dto = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
			dto.add(erro);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> idNaoExiste(IdNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> jsonComSintaxeErrada(HttpMessageNotReadableException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> idIncorretoNaCriacaoOuAtualizacaoDeEvento(IllegalArgumentException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	

	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<String> naoEnviouImagemNaTentativaDeUpload(FileNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(FileUploadException.class)
	public ResponseEntity<String> imagemNaoFoiSalvaPorErroNoServidor(FileUploadException exception) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<String> dadosDeUsernameInvalidos(UsernameNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> dadosDeLoginInvalidos(AuthenticationException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<String> naoPodeDeletarEntidadesQueTemDependenciaComOutras(SQLIntegrityConstraintViolationException exception) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
	}
	
	@ExceptionHandler(SemEstoqueException.class)
	public ResponseEntity<List<SemEstoqueDto>> semEstoqueSuficienteParaCompra(SemEstoqueException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getEstoque());
	}
	
	@ExceptionHandler(CarrinhoVazioException.class)
	public ResponseEntity<String> carrinhoVazioJogaException(CarrinhoVazioException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
	
	
	
}
