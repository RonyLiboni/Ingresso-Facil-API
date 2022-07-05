package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query("SELECT SUM(ing.quantidadeIngressos) FROM Cliente c JOIN c.ingressos ing WHERE ing.evento.id = :eventoId")
	Integer quantidadeDeIngressosVendidosPorEvento(Long eventoId);

	Optional<Cliente> findByEmail(String email);
	
}
