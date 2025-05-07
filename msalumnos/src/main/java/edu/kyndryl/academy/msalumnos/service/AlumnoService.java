package edu.kyndryl.academy.msalumnos.service;

import java.util.Optional;

import org.springframework.data.domain.Pageable;

//import org.springframework.data.domain.Pageable;

//import org.springframework.data.domain.Pageable;

import edu.kyndryl.academy.msalumnos.model.FraseChiquito;
import edu.kyndryl.academy.msalumnos.model.FraseChuckNorris;
import edu.kyndryl.academy.mscomun.entity.Alumno;

/**
 * Aquí quedan definidos de manera abstracta (sólo la cabecera de los métodos)
 * la funcionalidad o la lógica de negocio
 */
public interface AlumnoService {
	
	//ALTA
	Alumno alta (Alumno alumno);
	//BAJA POR ID
	void borrarPorId (Long id);
	//MODIFICACIÓN
	Optional<Alumno> modificarAlumnoPorId (Alumno alumno, Long id);
	//CONSULTA POR ID
	Optional<Alumno> consultarPorId (Long id);
	//CONSULTA DE TODOS
	Iterable<Alumno> consultarTodos ();
	//CONSULTA POR RANGO DE EDAD
	Iterable<Alumno> buscarAlumnosPorIntervaloDeEdad (int edadmin, int edadmax);
	//OBTENER FRASE CHIQUITO DE OTRO SERVER
	Optional<FraseChiquito> obtenerFraseAleatoria ();
	//OBTENER FRASE CHUCK NORRIS DE OTRO SERVER
	FraseChuckNorris obtenerFraseAleatoriaChuckNorris ();
	//OBTENER LOS ALUMNOS POR PÁGINAS / BLOQUES
	Iterable<Alumno> consultarAlumnosPorPagina (Pageable pageable);
	//OBTENER LOS ALUMNOS POR PÁGINAS / BLOQUES y por EDAD
	Iterable<Alumno> consultarAlumnosPorPaginaYPorEdad (int edadmin, int edadmax, Pageable pageable);

}
