package edu.kyndryl.academy.mscursos.service;

import java.util.List;
import java.util.Optional;

import edu.kyndryl.academy.mscomun.entity.Alumno;
import edu.kyndryl.academy.mscomun.entity.Curso;


public interface CursoService {
	

	public Iterable<Curso> findAll ();
	
	public Optional<Curso> findById (Long id);
	
	public Curso save (Curso curso);
	
	public void deleteById (Long id);
	
	public Optional<Curso> update (Curso curso, Long id);
	
	public Optional<Curso> asignarAlumos (List<Alumno> alumnos, Long idcurso);
	
	public Optional<Curso> eliminarAlumno (Alumno alumno, Long idcurso);
	
	public Optional<Curso> obtenerCursoAlumno (Long idalumno);

}
