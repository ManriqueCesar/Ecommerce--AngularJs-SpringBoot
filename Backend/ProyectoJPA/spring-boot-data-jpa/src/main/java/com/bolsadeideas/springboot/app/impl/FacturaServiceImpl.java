package com.bolsadeideas.springboot.app.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.model.FacturaModelo;
import com.bolsadeideas.springboot.app.model.FacturaProductoModelo;
import com.bolsadeideas.springboot.app.model.ItemFacturaModelo;
import com.bolsadeideas.springboot.app.models.dao.IFacturaDao;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.entity.Factura;
import com.bolsadeideas.springboot.app.models.entity.ItemFactura;
import com.bolsadeideas.springboot.app.models.service.IFacturaService;
import com.bolsadeideas.springboot.app.models.service.IProductoService;

@Service
public class FacturaServiceImpl implements IFacturaService {

	@Autowired
	private IFacturaDao facturaDao;

	@Autowired
	private IProductoService productoService;

	@Override
	@Transactional(readOnly = true)
	public List<FacturaModelo> findAllFactura() {
		// TODO Auto-generated method stub
		return (List<FacturaModelo>) facturaDao.findAllFacturas();
	}

	@Override
	@Transactional
	public Factura saveFactura(FacturaProductoModelo facModelo) {
		Factura factura = new Factura();
		Cliente cliente = new Cliente();

		factura.setDescripcion(facModelo.getDescripcion());

		factura.setObservacion(facModelo.getObservacion());

		cliente.setId(facModelo.getIdCliente());

		factura.setCliente(cliente);

		List<ItemFactura> items = new ArrayList<>();

		Double precioTotal = 0.0;

		for (ItemFacturaModelo itemFacturaModelo : facModelo.getItems()) {

			ItemFactura itemFactura = new ItemFactura();

			itemFactura.setProducto(productoService.findById(itemFacturaModelo.getProductoId()));

			itemFactura.setCantidad(itemFacturaModelo.getCantidad());

			items.add(itemFactura);

		}

		factura.setItems(items);

		return facturaDao.save(factura);
	}

	@Override
	@Transactional
	public void deleteFactura(Long id) {
		facturaDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id) {
		return facturaDao.fetchByIdWithClienteWhithItemFacturaWithProducto(id);
	}

	@Override
	public FacturaModelo findFacturaById(Long id) {
		// TODO Auto-generated method stub
		return facturaDao.findFacturaById();
	}

	@Override
	public Factura findFacById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Factura findDetalleById(Long id) {
		return facturaDao.findDetalleById(id);
	}

}
