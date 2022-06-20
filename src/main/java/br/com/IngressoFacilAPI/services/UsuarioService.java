package br.com.IngressoFacilAPI.services;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.usuario.Perfil;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import br.com.IngressoFacilAPI.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	
	public void salvar (Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void criarUsuario(ClienteCadastroForm form) {
		salvar(Usuario.builder()
				.email(form.getEmail())
				.senha(new BCryptPasswordEncoder().encode(form.getSenha()))
				.perfis(List.of(new Perfil("ROLE_CLIENTE")))
				.build());
	}
}
