package edu.kyndryl.academy.mscursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.kyndryl.academy.mscomun.entity.Curso;


@Repository //no es obligatorio por heredar de CrudRepository
public interface CursoRepository extends CrudRepository<Curso, Long> {
	//TODO: PAGINACIÓN
	
	//QUERY NATIVA --tablas
	//JQPL / HQL --entitys / clases
	@Query(value = "select * from cursos where id = "
			+ "(select curso_id from cursos_alumnos where alumnos_id = ?1)", nativeQuery = true)
	public Optional<Curso> obtenerCursoAlumno (Long idalumno);

}
