package br.com.IngressoFacilAPI.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.carrinho.Carrinho;
import br.com.IngressoFacilAPI.entities.cliente.Cliente;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
	
	Optional<Carrinho> findByCliente(Cliente cliente);
}
