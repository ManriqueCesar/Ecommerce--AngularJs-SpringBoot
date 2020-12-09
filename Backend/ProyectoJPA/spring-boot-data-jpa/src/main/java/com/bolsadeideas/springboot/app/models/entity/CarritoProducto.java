package com.bolsadeideas.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bolsadeideas.springboot.app.model.CarritoProductoModelo;

@Entity
@Table(name = "carrito_producto")
public class CarritoProducto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer cantidad;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@OneToOne
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;

	
	public CarritoProducto() {
		
	}
	
	public CarritoProducto(CarritoProductoModelo carro) {
		this.id=carro.getId();
		this.cantidad=carro.getCantidad();
		this.producto.setId(carro.getIdProducto());
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

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public Double calcularImporte() {
		return cantidad.doubleValue() * producto.getPrecio();
	}


	public Carrito getCarrito() {
		return carrito;
	}


	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	
	
}
