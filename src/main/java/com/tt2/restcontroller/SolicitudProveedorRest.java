package com.tt2.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt2.entity.SolicitudProveedor;
import com.tt2.service.SolicitudProveedorBean;

@RestController
@RequestMapping("/solicitudProveedor")
@CrossOrigin("*")
public class SolicitudProveedorRest implements ErrorController{
	@Autowired
	@Qualifier("solicitanteProveedorBean")
	private SolicitudProveedorBean solicitudProveedorBean;

	@PostMapping("/registroProveedor")
	public String registrarProveedor(@RequestBody SolicitudProveedor solicitudProveedor){
		SolicitudProveedor usu = solicitudProveedorBean.nuevaSolicitud(solicitudProveedor);
		String res = "El proveedor ya tiene una solicitud en curso";
		if(usu != null)
			res = "Solicitus enviada con exito";
		return res;
	}
	
	@Override
	public String getErrorPath() {
		return "No Mapping Found";
	}
	
	
}
