package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bolsadeideas.springboot.app.model.ProductoModelo;
import com.bolsadeideas.springboot.app.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);

	public List<Producto> findByNombreLikeIgnoreCase(String term);

	@Query("select p from Producto p")
	public List<Producto> findAllProductos();
	
	@Query("select new com.bolsadeideas.springboot.app.model.ProductoModelo(p) from Producto p where p.id=:id")
	public ProductoModelo findProductoById(@Param("id") Long id);

}