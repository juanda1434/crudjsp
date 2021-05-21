package com.edu.ufps.CrudJsp.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Usuario implements Serializable {

	private String nombre;
	private Integer id;
	private String email;
	private String pais;
	
	public Usuario() {
		
	}
	public Usuario(Integer id) {
		this.id=id;
	}
	public Usuario(String nombre,String email,String pais) {
		this.nombre=nombre;
		this.email=email;
		this.pais=pais;
	}
	public Usuario(Integer id,String nombre,String email,String pais) {
		this.nombre=nombre;
		this.email=email;
		this.pais=pais;
		this.id=id;
	}
	

}
