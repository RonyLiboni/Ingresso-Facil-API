package br.com.IngressoFacilAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.evento.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
