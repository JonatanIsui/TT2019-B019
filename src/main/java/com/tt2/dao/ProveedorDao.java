package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Proveedor;

@Repository("proveedorDao")
public interface ProveedorDao extends JpaRepository<Proveedor , Integer>{
	public abstract String findByNombreEmpresa(String nombreEmpresa);
}
