package edu.kyndryl.academy.msalumnos.security.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//representamos los usuarios en base de datos

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremento
	private Long id;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Rol> roles;
	
	private String nombre;
	
	@Column(unique = true)
	private String email;
	
	private String empresa;
	
	private String password;
	
	@Column(columnDefinition = "boolean default true")
	private Boolean activada;
	
	@Column(columnDefinition = "boolean default false")
	private Boolean bloqueada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActivada() {
		return activada;
	}

	public void setActivada(Boolean activada) {
		this.activada = activada;
	}

	public Boolean getBloqueada() {
		return bloqueada;
	}

	public void setBloqueada(Boolean bloqueada) {
		this.bloqueada = bloqueada;
	}

	public Usuario(Long id, List<Rol> roles, String nombre, String email, String empresa, String password,
			Boolean activada, Boolean bloqueada) {
		super();
		this.id = id;
		this.roles = roles;
		this.nombre = nombre;
		this.email = email;
		this.empresa = empresa;
		this.password = password;
		this.activada = activada;
		this.bloqueada = bloqueada;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	

}