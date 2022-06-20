package br.com.IngressoFacilAPI.validations.usuario.senha;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = SenhaForteValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SenhaForte {

	String message() default "Senha inválida!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}