package edu.kyndryl.academy.mscursos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kyndryl.academy.mscomun.entity.Alumno;
import edu.kyndryl.academy.mscomun.entity.Curso;
import edu.kyndryl.academy.mscursos.repository.CursoRepository;


@Service
public class CursoServiceImp implements CursoService {
	
	@Autowired
	CursoRepository cursoRepository;//acceso a la bd

	@Override
	@Transactional(readOnly = true)
	public Iterable<Curso> findAll() {
		return this.cursoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> findById(Long id) {
		return this.cursoRepository.findById(id);
	}

	@Override
	@Transactional
	public Curso save(Curso curso) {
		return this.cursoRepository.save(curso);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		this.cursoRepository.deleteById(id);

	}


	@Override
	@Transactional
	public Optional<Curso> update(Curso curso, Long id) {
		Optional<Curso> opcurso = Optional.empty();
		
			//1 leo el curso a modificar por el id
			opcurso = this.cursoRepository.findById(id);
			//2 si existe
			if (opcurso.isPresent())
			{
				Curso cursoleido = opcurso.get();
				//2.1 modifco los datos del curso leido
				cursoleido.setNombre(curso.getNombre());//actualizo
				//forma alternativa de actualizar todos los atributos de una clase
				//en vez de hacer el set uno a uno 
				//BeanUtils.copyProperties(curso, cursoleido, "nombre");
				//2.2 guardo
				//this.cursoRepository.save(cursoleido); //ESTA INSTRUCCIÓN NO ES NECESARIA. SE MODIFICA AUTOMÁTICAMENTE EN BD, POR ESTAR LA ENTIDAD CURSO EN ESTADO "PERSISTENCE"
				opcurso = Optional.of(cursoleido);//devuelvo el curso modificado
			} 
			//3 no existe - no hago nada
			//else {
				//no hago nada
			//}				
			
			
		 
		return opcurso;
	}

	@Override
	@Transactional
	public Optional<Curso> asignarAlumos(List<Alumno> alumnos, Long idcurso) {
		
		Optional<Curso> opcurso = Optional.empty();
		
		//leer el curso
			opcurso = this.cursoRepository.findById(idcurso);
		//si existe
			if (opcurso.isPresent())
			{
				Curso cursoleido = opcurso.get();
				alumnos.forEach(alumno -> cursoleido.addAlumno(alumno));
				opcurso = Optional.of(cursoleido);
				
			}
			
		//si no, no hago nada
		
		
		return opcurso;
	}

	@Override
	@Transactional
	public Optional<Curso> eliminarAlumno(Alumno alumno, Long idcurso) {
		Optional<Curso> opcurso = Optional.empty();
		
		//leer el curso
			opcurso = this.cursoRepository.findById(idcurso);
		//si existe
			if (opcurso.isPresent())
			{
				Curso cursoleido = opcurso.get();
				cursoleido.borrarAlumno(alumno);
				opcurso = Optional.of(cursoleido);
				
			}
			
		//si no, no hago nada
		
		
		return opcurso;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Curso> obtenerCursoAlumno(Long idalumno) {
		
		return this.cursoRepository.obtenerCursoAlumno(idalumno);
	}

}
