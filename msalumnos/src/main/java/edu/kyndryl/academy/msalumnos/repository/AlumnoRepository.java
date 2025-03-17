package edu.kyndryl.academy.msalumnos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import edu.kyndryl.academy.mscomun.entity.Alumno;


/**
 * en esta clase, operamos con la base de datos
 */
@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
	
	//ADEMÁS DE CRUD VAMOS A AÑADIR UNA KEYWORD QUERY
	
		//consulta de los alumnos que estén de un rango de EDAD
	
			Iterable<Alumno> findByEdadBetween (int edadmin, int edadmax);

}