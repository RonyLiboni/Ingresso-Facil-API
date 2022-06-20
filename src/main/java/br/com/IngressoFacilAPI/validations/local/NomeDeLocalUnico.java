package br.com.IngressoFacilAPI.validations.local;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NomeUnicoParaLocalValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NomeDeLocalUnico {
	String message() default "Este nome de local ja existe!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
