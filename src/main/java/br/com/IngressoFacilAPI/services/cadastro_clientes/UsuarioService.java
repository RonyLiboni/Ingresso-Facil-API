package br.com.IngressoFacilAPI.services.cadastro_clientes;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.usuario.Perfil;
import br.com.IngressoFacilAPI.entities.usuario.Usuario;
import br.com.IngressoFacilAPI.repositories.autenticacao.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void salvar (Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void criarUsuario(ClienteCadastroForm form) {
		salvar(Usuario.builder()
				.email(form.getEmail())
				.senha(passwordEncoder.encode(form.getSenha()))
				.perfis(List.of(new Perfil("ROLE_CLIENTE")))
				.build());
	}
}
