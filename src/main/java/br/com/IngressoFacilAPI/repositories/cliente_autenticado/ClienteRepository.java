package br.com.IngressoFacilAPI.repositories.cliente_autenticado;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.IngressoFacilAPI.entities.cliente.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	@Query(value = "SELECT SUM(quantidade_ingressos) FROM `ingresso-facil-api`.cliente_ingressos "
			+ "WHERE `ingresso-facil-api`.cliente_ingressos.evento_id = :eventoId" ,nativeQuery = true)
	Integer quantidadeDeIngressosVendidosPorEvento(Long eventoId);

	Optional<Cliente> findByEmail(String email);
	
}
