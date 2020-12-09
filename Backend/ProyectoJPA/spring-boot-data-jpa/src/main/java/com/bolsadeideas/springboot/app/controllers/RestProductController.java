package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.app.model.ProductoModelo;
import com.bolsadeideas.springboot.app.models.entity.Producto;
import com.bolsadeideas.springboot.app.models.service.IProductoService;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/productos")

public class RestProductController {

	@Autowired
	private IProductoService productoService;

	@GetMapping
	public List<Producto> showProducto() {
		return productoService.findAllProduct();

	}

	@GetMapping("/{id}")
	public ProductoModelo find(@PathVariable Long id) {
		return productoService.findProductoById(id);

	}

}
