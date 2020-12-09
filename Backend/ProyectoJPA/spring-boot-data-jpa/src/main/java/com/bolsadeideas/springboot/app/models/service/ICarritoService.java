package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.model.CarritoModelo;
import com.bolsadeideas.springboot.app.model.CarritoProductoModelo;
import com.bolsadeideas.springboot.app.model.ResponseModel;
import com.bolsadeideas.springboot.app.models.entity.Carrito;
import com.bolsadeideas.springboot.app.models.entity.CarritoProducto;

public interface ICarritoService {

	public Carrito saveCarrito(CarritoProductoModelo carrito);
	
	public ResponseModel llenarCarrito(CarritoProductoModelo carrito) ;
	
	public CarritoModelo findCarritoById(Long id);

	public List<CarritoModelo> findAll();
	
	public List<CarritoProductoModelo> findAllCarritoProducto();
	
	public List<CarritoProductoModelo> findCarritoDetalleById (Long id);

	

}
	