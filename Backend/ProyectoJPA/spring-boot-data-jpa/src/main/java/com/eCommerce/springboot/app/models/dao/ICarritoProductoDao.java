package com.eCommerce.springboot.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eCommerce.springboot.app.models.entity.Carrito;
import com.eCommerce.springboot.app.models.entity.CarritoProducto;


public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long> {
	

}
