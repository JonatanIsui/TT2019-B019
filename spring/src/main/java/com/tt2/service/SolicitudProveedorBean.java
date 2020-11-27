package com.tt2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.SolicitudProveedorDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.service.interfaz.SolicitudProveedorBeanInterfaz;

@Service("solicitanteProveedorBean")
public class SolicitudProveedorBean implements SolicitudProveedorBeanInterfaz{

	@Autowired
	@Qualifier("solicitudProveedorDao")
	private SolicitudProveedorDao solicitudProveedorDao;
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Override
	public SolicitudProveedor nuevaSolicitud(SolicitudProveedor solicitud) {
		try {
			SolicitudProveedor aux = null;
			if(solicitudProveedorDao.findByCorreo(solicitud.getNombreEmpresa()) == null && usuarioDao.findByCorreo(solicitud.getCorreo()) == null)
				aux =solicitudProveedorDao.save(solicitud); 
			return aux;
		}catch(Exception e) {
			return null;
		}
	}

}
