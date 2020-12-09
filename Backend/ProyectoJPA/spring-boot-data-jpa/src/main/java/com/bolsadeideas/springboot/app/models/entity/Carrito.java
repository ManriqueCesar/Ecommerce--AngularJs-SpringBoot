package com.bolsadeideas.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bolsadeideas.springboot.app.model.CarritoModelo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="carrito")
public class Carrito implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carritoId;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	

	
	public Carrito() {
		
	}
	

	public Carrito(CarritoModelo carrito) {
		this.carritoId=carrito.getId();
		this.createAt=new Date();
		
	}
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}
	
	
	public Long getCarritoId() {
		return carritoId;
	}


	public void setCarritoId(Long carritoId) {
		this.carritoId = carritoId;
	}


	public Date getCreateAt() {
		return createAt;
	}



	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
