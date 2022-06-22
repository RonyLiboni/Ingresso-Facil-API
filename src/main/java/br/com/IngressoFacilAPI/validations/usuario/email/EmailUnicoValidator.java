package br.com.IngressoFacilAPI.validations.usuario.email;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.IngressoFacilAPI.repositories.autenticacao.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmailUnicoValidator implements ConstraintValidator<EmailDeveSerUnico, String> {

    private final UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !usuarioRepository.existsByEmail(email);
    }

}
