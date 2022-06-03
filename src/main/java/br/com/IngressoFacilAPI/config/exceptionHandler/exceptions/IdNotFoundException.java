package br.com.IngressoFacilAPI.config.exceptionHandler.exceptions;

public class IdNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundException (String msg) {
		super(msg);	
	}
}
