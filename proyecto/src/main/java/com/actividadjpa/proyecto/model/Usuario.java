package com.actividadjpa.proyecto.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 30)
	private String nombre;
	
	@Column(nullable = false)
	private String contrasena;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Rol rol;
	
	public Usuario() {
		  
	}
	
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
	
	public String getContrasena() {
		return contrasena; 
	}
	  
	public void setContrasena(String contrasena) { 
		this.contrasena = contrasena; 
	}
	
	public Rol getRol() { 
		return rol; 
	}
	
	public void setRol(Rol rol) { 
		this.rol = rol; 
	}
}