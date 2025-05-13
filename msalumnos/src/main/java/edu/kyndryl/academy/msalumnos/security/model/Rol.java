package edu.kyndryl.academy.msalumnos.security.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//autoincremento
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private TipoRol tipRol;
	
	public enum TipoRol {ROLE_USER, ROLE_ADMIN}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoRol getTipRol() {
		return tipRol;
	}

	public void setTipRol(TipoRol tipRol) {
		this.tipRol = tipRol;
	}
	
	
	

}
