package br.com.IngressoFacilAPI.services.cadastro_clientes;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.entities.cliente.form.ClienteCadastroForm;
import br.com.IngressoFacilAPI.entities.ingresso.Ingresso;
import br.com.IngressoFacilAPI.repositories.cliente_autenticado.ClienteRepository;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailSenderService;
import br.com.IngressoFacilAPI.services.envio_de_emails.EmailsTemplates;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;
	private final UsuarioService usuarioService;
	private final EmailSenderService emailSenderService; 
	
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente procurarPeloId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}
	
	public void adicionarIngresso(List<Ingresso> listaIngressos, String emailCliente) {
		Cliente cliente = procurarPeloEmail(emailCliente);
		listaIngressos.forEach(ingresso -> cliente.getIngressos().add(ingresso));		
		salvar(cliente);
	}	
	
	public Cliente cadastrarCliente(ClienteCadastroForm form) {
		usuarioService.criarUsuario(form);
		emailSenderService.enviarEmail(form.getEmail(),EmailsTemplates.CONTA_CRIADA_COM_SUCESSO);
		return salvar(Cliente.builder()
				.email(form.getEmail())
				.nome(form.getNome())
				.build());
	}

	public Cliente procurarPeloEmail(String email) {
		return clienteRepository.findByEmail(email).get();
	}
	
	@Override
	public String toString() {
		return "cliente";
	}

}
