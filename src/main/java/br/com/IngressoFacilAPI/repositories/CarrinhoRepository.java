package br.com.IngressoFacilAPI.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
	Optional<Carrinho> findByEventoIdAndClienteEmail(Long eventoId, String emailCliente);
	List<Carrinho> findByClienteEmail(String emailCliente);
	
}
