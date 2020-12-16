 package com.eCommerce.springboot.app.model;

import com.eCommerce.springboot.app.models.entity.CarritoProducto;
import com.eCommerce.springboot.app.models.entity.Producto;

public class CarritoProductoModelo {

	private Long id;
	private Integer cantidad;
	private String nombre;
	private double precio;
	private Long idProducto;
	private Long idCliente;
	
	public CarritoProductoModelo() {
		
	}
	
	public CarritoProductoModelo(CarritoProducto carroProducto) {
		this.id= carroProducto.getId();
		this.cantidad=carroProducto.getCantidad();
		this.nombre=carroProducto.getProducto().getNombre();
		this.precio= carroProducto.getProducto().getPrecio();
		this.idProducto= carroProducto.getProducto().getId();
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}


	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	
}
