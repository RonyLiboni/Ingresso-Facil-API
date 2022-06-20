package br.com.IngressoFacilAPI.services;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final UsuarioService usuarioService;
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente procurarPeloId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}
	
	public void adicionarIngresso(List<Ingresso> listaIngressos, Long clienteId) {
		Cliente cliente = procurarPeloId(clienteId);
		listaIngressos.forEach(ingresso -> cliente.getIngressos().add(ingresso));		
		salvar(cliente);
	}	
	
	@Override
	public String toString() {
		return "cliente";
	}

	public Cliente cadastrarCliente(ClienteCadastroForm form) {
		usuarioService.criarUsuario(form);
		return salvar(Cliente.builder()
				.email(form.getEmail())
				.nome(form.getNome())
				.build());
	}

}
