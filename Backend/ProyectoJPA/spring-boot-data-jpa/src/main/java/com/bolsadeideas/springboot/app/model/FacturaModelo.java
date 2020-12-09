package com.bolsadeideas.springboot.app.model;

import java.io.Serializable;
import java.util.Date;

import com.bolsadeideas.springboot.app.models.entity.Factura;

public class FacturaModelo implements Serializable{
	

	private Long id;
	private String descripcion;
	private String observacion;
	private Date create_at;
	private double total;
	
	private ClienteModelo cliente;
	
	public FacturaModelo () {
		
	}
	
	public FacturaModelo(Factura factura) {
		this.id= factura.getId();
		this.descripcion= factura.getDescripcion();
		this.observacion= factura.getObservacion();
		this.create_at=factura.getCreateAt();
		this.total = factura.getTotal();
		
		this.cliente= new ClienteModelo (factura.getCliente());
	}

	
	public ClienteModelo getCliente() {
		return cliente;
	}

	public void setCliente(ClienteModelo cliente) {
		this.cliente = cliente;
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

	public Date getCreate_at() {
		return create_at;
	}

	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
