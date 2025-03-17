package edu.kyndryl.academy.mscomun.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //autoinc
	private Long id;
	
	private String nombre;
	
	@OneToMany(fetch = FetchType.LAZY) //TODO add tipo de fetch
	private List<Alumno> alumnos;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Curso(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}
	
	public Curso() {
		// TODO Auto-generated constructor stub
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	
	public void addAlumno (Alumno alumno)
	{
		this.alumnos.add(alumno);
	}
	
	public void borrarAlumno (Alumno alumno)
	{
		this.alumnos.remove(alumno);
	}


}
