package com.tt2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tt2.entity.SolicitudProveedor;

@Repository("solicitudProveedorDao")
public interface SolicitudProveedorDao extends JpaRepository<SolicitudProveedor, Integer>{
	public abstract SolicitudProveedor findByNombreEmpresa(String nombreEmpresa);
	public abstract SolicitudProveedor findByCorreo(String correo);
	@Query("FROM SolicitudProveedor WHERE estadoSolicitud = 0 ")
	public abstract List<SolicitudProveedor> getPedientes();
}
