package br.com.IngressoFacilAPI.services;

import org.springframework.stereotype.Service;

import br.com.IngressoFacilAPI.config.exceptionHandler.exceptions.IdNotFoundException;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;
import br.com.IngressoFacilAPI.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

	private final ClienteRepository clienteRepository;

	public Cliente procurarPeloId(Long id) {
		return clienteRepository.findById(id)
				.orElseThrow(() -> new IdNotFoundException("O Id do " + toString() + " informado não existe!"));
	}

	@Override
	public String toString() {
		return "cliente";
	}
}
