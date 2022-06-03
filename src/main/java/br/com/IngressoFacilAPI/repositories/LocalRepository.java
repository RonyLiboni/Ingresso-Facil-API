package br.com.IngressoFacilAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.Local.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

}
