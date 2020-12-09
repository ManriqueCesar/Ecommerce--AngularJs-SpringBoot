package com.bolsadeideas.springboot.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.app.models.entity.CarritoProducto;


public interface ICarritoProductoDao extends JpaRepository<CarritoProducto, Long> {
	
}
