package com.bolsadeideas.springboot.app.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.model.CarritoModelo;
import com.bolsadeideas.springboot.app.model.CarritoProductoModelo;
import com.bolsadeideas.springboot.app.model.ResponseModel;
import com.bolsadeideas.springboot.app.models.dao.ICarritoDao;
import com.bolsadeideas.springboot.app.models.dao.ICarritoProductoDao;
import com.bolsadeideas.springboot.app.models.dao.IProductoDao;
import com.bolsadeideas.springboot.app.models.entity.Carrito;
import com.bolsadeideas.springboot.app.models.entity.CarritoProducto;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.ICarritoService;
import com.bolsadeideas.springboot.app.models.service.IProductoService;

@Service
public class CarritoServiceImpl implements ICarritoService {

	private final Logger LOGGER = Logger.getLogger(CarritoServiceImpl.class.getName());

	@Autowired
	private ICarritoDao carritoDao;
	
	@Autowired
	private ICarritoProductoDao carritoProductoDao;

	@Autowired
	private IProductoService productoService;

	@Override
	public CarritoModelo findCarritoById(Long id) {
		return carritoDao.fetchByIdWithCarritoProducto(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CarritoModelo> findAll() {
		return (List<CarritoModelo>) carritoDao.findAllCarritos();
	}

	@Override
	public List<CarritoProductoModelo> findAllCarritoProducto() {
		return carritoDao.findAllCarritosProducto();
	}

	@Override
	public List<CarritoProductoModelo> findCarritoDetalleById(Long id) {
		return carritoDao.findByCarritoProductoIdCarrito(id);
	}

	@Override
	public Carrito saveCarrito(CarritoProductoModelo carProdModelo) {
		Carrito carro = new Carrito();
		Cliente cliente = new Cliente();

		// pendiente
		try {
			if (carProdModelo.getIdCliente() != 1) {

				cliente.setId(carProdModelo.getIdCliente());
				carro.setCliente(cliente);

				return carritoDao.save(carro);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public ResponseModel llenarCarrito(CarritoProductoModelo carrito) {

		try {
			Carrito carro = carritoDao.obtenerCarritoByClienteId(carrito.getIdCliente());
			CarritoProducto carritoProducto = new CarritoProducto();
			carritoProducto.setCantidad(carrito.getCantidad());
			carritoProducto.setCarrito(carro);
			carritoProducto.setProducto(productoService.findById(carrito.getIdProducto()));
			
			carritoProductoDao.save(carritoProducto);
			
			return new ResponseModel("Agregado al carrito correctamente", HttpStatus.CREATED);
			
		} catch (Exception e) {

			LOGGER.log(Level.SEVERE, e.getMessage(), e);

			return new ResponseModel("Error al agregar producto", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
