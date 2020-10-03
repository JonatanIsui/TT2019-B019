package com.init.construccion1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.init.construccion1.entitys.Proveedor;

public interface ProveedorDao extends JpaRepository<Proveedor, Integer>{
	@Query("FROM proveedor WHERE idusuario=?1")
	public abstract Proveedor getByidUs(int idusuario);
}
