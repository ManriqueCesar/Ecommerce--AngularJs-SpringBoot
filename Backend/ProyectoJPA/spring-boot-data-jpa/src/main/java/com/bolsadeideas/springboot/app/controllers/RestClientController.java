package com.bolsadeideas.springboot.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.model.ClienteModelo;
import com.bolsadeideas.springboot.app.models.entity.Cliente;
import com.bolsadeideas.springboot.app.models.service.IClienteService;

@RestController
@CrossOrigin(origins = {"*"})
@RequestMapping("/clientes")
public class RestClientController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping
	public List<ClienteModelo> index() {
		return clienteService.findAll();
	}

	@GetMapping("/{id}")
	public ClienteModelo find(@PathVariable Long id) {
		return clienteService.findClienteById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody ClienteModelo cliente) {
		return clienteService.save(cliente);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente update(@PathVariable Long id,@RequestBody ClienteModelo cliente) {
		cliente.setId(id);
		return clienteService.save(cliente);
	}

}
