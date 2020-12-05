package com.tt2.service.interfaz;

import java.util.List;

import com.tt2.entity.SolicitudProveedor;

public interface SolicitudProveedorBeanInterfaz{
	List<SolicitudProveedor> getSolicitudes();
	boolean eliminarSolicitud(SolicitudProveedor solicitud);
	SolicitudProveedor nuevaSolicitud(SolicitudProveedor solicitud);
}
