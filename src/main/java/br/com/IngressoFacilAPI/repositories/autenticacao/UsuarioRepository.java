package br.com.IngressoFacilAPI.repositories.autenticacao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.IngressoFacilAPI.entities.usuario.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Optional<Usuario> findByEmail(String email);

	boolean existsByEmail(String email);
}
