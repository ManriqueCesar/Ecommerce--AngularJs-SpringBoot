package com.bolsadeideas.springboot.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.model.ProductoModelo;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDao productoDao;

	@Transactional(readOnly = true)
	public List<Producto> findByNombre(String term) {
		return productoDao.findByNombreLikeIgnoreCase("%" + term + "%");
	}

	public List<Producto> findAllProduct() {
		// TODO Auto-generated method stub
		return productoDao.findAllProductos();
	}

	@Override
	public Producto findById(Long id) {
		return productoDao.getOne(id);
	}

	@Override
	public ProductoModelo findProductoById(Long id) {
		return productoDao.findProductoById(id);
	}

}
