package com.eCommerce.springboot.app.model;
import java.io.Serializable;
import java.util.List;


public class FacturaProductoModelo implements Serializable{

	private Long id;
	private String descripcion;
	private String observacion;
	private Long idCliente;
	
	private List<ItemFacturaModelo> items;
	
	public List<ItemFacturaModelo> getItems() {
		return items;
	}

	public void setItems(List<ItemFacturaModelo> items) {
		this.items = items;
	}

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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
