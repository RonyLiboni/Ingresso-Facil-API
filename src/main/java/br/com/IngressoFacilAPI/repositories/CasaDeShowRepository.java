package br.com.IngressoFacilAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.casaDeShow.CasaDeShow;

@Repository
public interface CasaDeShowRepository extends JpaRepository<CasaDeShow, Long> {

}
