package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import com.bolsadeideas.springboot.app.model.FacturaModelo;
import com.bolsadeideas.springboot.app.model.FacturaProductoModelo;
import com.bolsadeideas.springboot.app.models.entity.Factura;


public interface IFacturaService {
	//Factura
	
	public Factura saveFactura(FacturaProductoModelo factura);
	
	public Factura findFacById(Long id);
	
	public FacturaModelo findFacturaById(Long id);
	
	public void deleteFactura(Long id);
	
	public Factura fetchFacturaByIdWithClienteWhithItemFacturaWithProducto(Long id);

	public List<FacturaModelo> findAllFactura();
	
	public Factura findDetalleById(Long id);
	
}
