package edu.kyndryl.academy.msalumnos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.kyndryl.academy.msalumnos.security.model.Usuario;


@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	Optional<Usuario> findByEmail (String email);
	
	Optional<Usuario> findByNombre (String email);
	
}