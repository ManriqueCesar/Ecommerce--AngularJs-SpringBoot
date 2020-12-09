package com.bolsadeideas.springboot.app.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DetalleFacturaModelo implements Serializable{
	

	private Long id;
	private String descripcion;
	private String observacion;
	private ArrayList<String> cliente;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public ArrayList<String> getCliente() {
		return cliente;
	}
	public void setCliente(ArrayList<String> cliente) {
		this.cliente = cliente;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
