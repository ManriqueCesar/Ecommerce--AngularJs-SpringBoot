package com.eCommerce.springboot.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eCommerce.springboot.app.model.ProductoModelo;
import com.eCommerce.springboot.app.models.dao.IProductoDao;
import com.eCommerce.springboot.app.models.entity.Producto;
import com.eCommerce.springboot.app.models.service.IProductoService;

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

	@Override
	public Integer findProductoCantidadByProductoId(Long id) {
		return productoDao.findProductoCantidadByProductoId(id);
	}

	@Override
	public Integer updateProductQuantityById(Integer cantidad, Long id) {
		return productoDao.updateProductQuantityById(cantidad, id);
	}

	@Override
	public String findProductonameById(Long id) {
		return productoDao.findProductonameById(id);
	}









}
