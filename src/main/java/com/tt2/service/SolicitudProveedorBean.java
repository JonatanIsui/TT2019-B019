package com.tt2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.SolicitudProveedorDao;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.service.interfaz.SolicitudProveedorBeanInterfaz;

@Service("solicitanteProveedorBean")
public class SolicitudProveedorBean implements SolicitudProveedorBeanInterfaz{

	@Autowired
	@Qualifier("solicitudProveedorDao")
	private SolicitudProveedorDao solicitudProveedorDao;
	
	@Override
	public List<SolicitudProveedor> getSolicitudes() {
		return solicitudProveedorDao.findAll();
	}

	@Override
	public boolean eliminarSolicitud(SolicitudProveedor solicitud) {
		boolean aux = false;
		if(solicitudProveedorDao.findById(solicitud.getId())!=null) {
			solicitudProveedorDao.delete(solicitud);
		}
		return aux;
	}

	@Override
	public SolicitudProveedor nuevaSolicitud(SolicitudProveedor solicitud) {
		SolicitudProveedor aux = null;
		if(solicitudProveedorDao.findByCorreo(solicitud.getNombreEmpresa()) == null && solicitudProveedorDao.findByCorreo(solicitud.getCorreo()) == null) {
			aux =solicitudProveedorDao.save(solicitud); 
		}
		return aux;
	}

}
