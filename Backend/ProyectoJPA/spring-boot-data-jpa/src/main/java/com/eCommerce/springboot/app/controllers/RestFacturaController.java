package com.eCommerce.springboot.app.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.springboot.app.model.FacturaModelo;
import com.eCommerce.springboot.app.model.FacturaProductoModelo;
import com.eCommerce.springboot.app.model.ResponseModel;
import com.eCommerce.springboot.app.models.entity.Factura;
import com.eCommerce.springboot.app.models.service.IFacturaService;

@RestController
@RequestMapping("/facturas")

public class RestFacturaController {

	@Autowired
	private IFacturaService facturaService;

	
	@GetMapping
	public List<FacturaModelo> showFacturas() {
		return facturaService.findAllFactura();

	}

	@GetMapping("/{id}")
	public FacturaModelo findFactura(@PathVariable Long id) {
		return facturaService.findFacturaById(id);

	}
	
	@GetMapping("/detalle/{id}")
	public Factura findDetalleFactura(@PathVariable Long id) {
		return facturaService.findDetalleById(id);

	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseModel> crear(@RequestBody FacturaProductoModelo facModelo) {
		ResponseModel response = facturaService.saveFactura(facModelo);
		return new ResponseEntity<>(response, response.getHttpStatus());
	}

	
}
