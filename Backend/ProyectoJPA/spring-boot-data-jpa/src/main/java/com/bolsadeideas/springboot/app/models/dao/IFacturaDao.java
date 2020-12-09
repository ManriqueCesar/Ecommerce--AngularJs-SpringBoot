package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.model.FacturaModelo;
import com.bolsadeideas.springboot.app.model.FacturaProductoModelo;
import com.bolsadeideas.springboot.app.models.entity.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{
	
	@Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
	public Factura fetchByIdWithClienteWhithItemFacturaWithProducto(Long id);
	
	@Query("select new com.bolsadeideas.springboot.app.model.FacturaModelo(c) from Factura c")
	public List<FacturaModelo> findAllFacturas();
	
	@Query("select new com.bolsadeideas.springboot.app.model.FacturaModelo(f) from Factura f where f.id=:id")
	public FacturaModelo findFacturaById();

	public Factura save(FacturaProductoModelo factura);
	
	@Query("select i from ItemFactura i join fetch i.producto where i.id=:id")
	public Factura findDetalleById(Long id);
}

