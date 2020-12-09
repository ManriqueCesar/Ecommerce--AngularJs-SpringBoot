package com.bolsadeideas.springboot.app.model;


import java.io.Serializable;

import com.bolsadeideas.springboot.app.models.entity.ItemFactura;

public class ItemFacturaModelo implements Serializable{

	private Long id;
	private Integer cantidad;
	private Long productoId;

	public ItemFacturaModelo() {

	}

	public ItemFacturaModelo(ItemFactura itemFactura) {
		this.productoId = itemFactura.getProducto().getId();
		this.cantidad = itemFactura.getCantidad();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
