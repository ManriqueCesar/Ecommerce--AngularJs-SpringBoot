package com.eCommerce.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eCommerce.springboot.app.model.CarritoModelo;
import com.eCommerce.springboot.app.model.CarritoProductoModelo;
import com.eCommerce.springboot.app.models.entity.Carrito;
import com.eCommerce.springboot.app.models.entity.CarritoProducto;


public interface ICarritoDao extends JpaRepository<Carrito, Long> {
	
	@Query("select new com.eCommerce.springboot.app.model.CarritoModelo(c) from Carrito c where c.id=:id")
	public CarritoModelo fetchByIdWithCarritoProducto(Long id);
	
	@Query("select new com.eCommerce.springboot.app.model.CarritoModelo(c) from Carrito c")
	public List<CarritoModelo> findAllCarritos();
	
	@Query("select new com.eCommerce.springboot.app.model.CarritoProductoModelo(c) from CarritoProducto c")
	public List<CarritoProductoModelo> findAllCarritosProducto();
	
	@Query("select new com.eCommerce.springboot.app.model.CarritoProductoModelo(c) from CarritoProducto c where c.carrito.carritoId=:id")
	public List<CarritoProductoModelo> findByCarritoProductoIdCarrito(Long id);

	@Query("select c from Carrito c where c.cliente.id=:id")
	public Carrito obtenerCarritoByClienteId(Long id);
	
}
