package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.springboot.app.model.ClienteModelo;
import com.bolsadeideas.springboot.app.models.entity.Cliente;

public interface IClienteDao extends JpaRepository<Cliente, Long>{

	@Query("select new com.bolsadeideas.springboot.app.model.ClienteModelo(c) from Cliente c where c.id=:id")
	public ClienteModelo findClienteById(@Param("id") Long id);
	
	@Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
	public Cliente fetchByIdWithFacturas(Long id);

	
	@Query("select new com.bolsadeideas.springboot.app.model.ClienteModelo(c) from Cliente c")
	public List<ClienteModelo> findAllClientes();

}
