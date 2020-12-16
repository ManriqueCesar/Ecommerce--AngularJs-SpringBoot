package com.eCommerce.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eCommerce.springboot.app.model.CarritoModelo;
import com.eCommerce.springboot.app.model.CarritoProductoModelo;
import com.eCommerce.springboot.app.model.FacturaProductoModelo;
import com.eCommerce.springboot.app.model.ResponseModel;
import com.eCommerce.springboot.app.models.entity.Carrito;
import com.eCommerce.springboot.app.models.entity.CarritoProducto;
import com.eCommerce.springboot.app.models.entity.Factura;
import com.eCommerce.springboot.app.models.service.ICarritoService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/carrito")

public class RestCarritoController {
	
	@Autowired
	private ICarritoService carritoService;
	
	
	@GetMapping("/{id}")
	public CarritoModelo find(@PathVariable Long id) {
		return carritoService.findCarritoById(id);
	}
	
	
	@GetMapping
	public List<CarritoModelo> findAll() {
		return carritoService.findAll();
	}
	
	@GetMapping("/detalle")
	public List<CarritoProductoModelo> findAllCarritoProducto() {
		return carritoService.findAllCarritoProducto();
	}
	
	@GetMapping("/detalle/{id}")
	public List<CarritoProductoModelo> findCarritoDetalleById(@PathVariable Long id) {
		return carritoService.findCarritoDetalleById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Carrito crear(@RequestBody CarritoProductoModelo carrito) {
		return carritoService.saveCarrito(carrito);
	}
	
	@DeleteMapping("/detalle/{id}")
	public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
		ResponseModel response = carritoService.deleteCarrito(id);
	  return new ResponseEntity<>(response, response.getHttpStatus());
	}
	
	@PostMapping("/detalle")
	public ResponseEntity<ResponseModel> llenar(@RequestBody CarritoProductoModelo carrito) {
		ResponseModel response = carritoService.llenarCarrito(carrito);
		return new ResponseEntity<>(response, response.getHttpStatus());
		 
	}
	
}
