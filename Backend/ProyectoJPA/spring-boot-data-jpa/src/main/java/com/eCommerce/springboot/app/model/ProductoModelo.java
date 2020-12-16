package com.eCommerce.springboot.app.model;

import java.io.Serializable;

import com.eCommerce.springboot.app.models.entity.Producto;

public class ProductoModelo implements Serializable {


	private Long id;
	private String nombre;
	private double precio;
	private int cantidad;
	
	public ProductoModelo() {
		
	}
	
	public ProductoModelo(Producto prod) {
		this.id=prod.getId();
		this.precio=prod.getPrecio();
		this.nombre=prod.getNombre();
		this.cantidad=prod.getCantidad();

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
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
