package com.bolsadeideas.springboot.app.model;

import java.io.Serializable;

import com.bolsadeideas.springboot.app.models.entity.Producto;

public class ProductoModelo implements Serializable {


	private Long id;
	private String nombre;
	private double precio;
	
	public ProductoModelo() {
		
	}
	
	public ProductoModelo(Producto prod) {
		this.id=prod.getId();
		this.precio=prod.getPrecio();
		this.nombre=prod.getNombre();

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
