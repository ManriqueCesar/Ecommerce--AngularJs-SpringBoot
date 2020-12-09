package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.model.ProductoModelo;
import com.bolsadeideas.springboot.app.models.entity.Producto;
public interface IProductoService {

	/* public List<Producto> findByNombre(String term); */

	public Producto findById(Long id);

	public List<Producto> findAllProduct();
	
	public ProductoModelo findProductoById(Long id);

}
