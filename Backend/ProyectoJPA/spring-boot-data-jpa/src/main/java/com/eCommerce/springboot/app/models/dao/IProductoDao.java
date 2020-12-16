package com.eCommerce.springboot.app.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eCommerce.springboot.app.model.ProductoModelo;
import com.eCommerce.springboot.app.models.entity.Producto;

public interface IProductoDao extends JpaRepository<Producto, Long> {

	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> findByNombre(String term);

	public List<Producto> findByNombreLikeIgnoreCase(String term);

	@Query("select p from Producto p")
	public List<Producto> findAllProductos();
	
	@Query("select new com.eCommerce.springboot.app.model.ProductoModelo(p) from Producto p where p.id=:id")
	public ProductoModelo findProductoById(@Param("id") Long id);
	
	@Query("select p.cantidad from Producto p where p.id=:id")
	public Integer findProductoCantidadByProductoId(Long id);
	
	@Query("select p.nombre from Producto p where p.id=:id")
	public String findProductonameById(Long id);
	
	@Modifying
	@Transactional
	@Query("UPDATE Producto p SET p.cantidad=:cantidad where p.id=:id")
	public Integer updateProductQuantityById(Integer cantidad, Long id);

}