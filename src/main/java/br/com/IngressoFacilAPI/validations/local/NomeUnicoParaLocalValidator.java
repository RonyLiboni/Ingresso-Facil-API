package br.com.IngressoFacilAPI.validations.local;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import br.com.IngressoFacilAPI.repositories.LocalRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NomeUnicoParaLocalValidator implements ConstraintValidator<NomeDeLocalUnico, String> {
	private final LocalRepository localRepository;
	
	@Override
	public boolean isValid(String nome, ConstraintValidatorContext context) {
		return !localRepository.existsByNome(nome);
	}

}
