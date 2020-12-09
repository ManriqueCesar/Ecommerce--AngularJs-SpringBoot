package com.bolsadeideas.springboot.app.model;

import java.io.Serializable;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

public class ClienteModelo implements Serializable{

	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	
	public ClienteModelo() {
		
	}
	
	public ClienteModelo(Cliente cliente) {
		this.id=cliente.getId();
		this.nombre=cliente.getNombre();
		this.apellido=cliente.getApellido();
		this.email=cliente.getEmail();
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
