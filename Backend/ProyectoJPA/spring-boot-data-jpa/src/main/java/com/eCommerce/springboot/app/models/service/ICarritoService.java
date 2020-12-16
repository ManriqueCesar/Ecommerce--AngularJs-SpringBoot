package com.eCommerce.springboot.app.models.service;

import java.util.List;

import com.eCommerce.springboot.app.model.CarritoModelo;
import com.eCommerce.springboot.app.model.CarritoProductoModelo;
import com.eCommerce.springboot.app.model.ResponseModel;
import com.eCommerce.springboot.app.models.entity.Carrito;
import com.eCommerce.springboot.app.models.entity.CarritoProducto;

public interface ICarritoService {

	public Carrito saveCarrito(CarritoProductoModelo carrito);
	
	public ResponseModel deleteCarrito(Long id);
	
	public ResponseModel llenarCarrito(CarritoProductoModelo carrito) ;
	
	public CarritoModelo findCarritoById(Long id);

	public List<CarritoModelo> findAll();
	
	public List<CarritoProductoModelo> findAllCarritoProducto();
	
	public List<CarritoProductoModelo> findCarritoDetalleById (Long id);

	

}
	