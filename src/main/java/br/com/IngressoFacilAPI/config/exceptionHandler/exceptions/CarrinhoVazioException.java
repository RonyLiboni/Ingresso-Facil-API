package br.com.IngressoFacilAPI.config.exceptionHandler.exceptions;

public class CarrinhoVazioException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CarrinhoVazioException (String msg) {
		super(msg);	
	}
}
