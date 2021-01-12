package com.tt2.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt2.entity.SolicitudProveedor;
import com.tt2.service.Imagenes;
import com.tt2.service.SolicitudProveedorBean;

@RestController
@RequestMapping("/solicitudProveedor")
@CrossOrigin("*")
public class SolicitudProveedorRest implements ErrorController{
	@Autowired
	@Qualifier("solicitanteProveedorBean")
	private SolicitudProveedorBean solicitudProveedorBean;

	@Autowired
	@Qualifier("imagenesBean")
	private Imagenes imagenes;
	
	@PostMapping("/registroProveedor")
	public ResponseEntity<String> registrarProveedor(@RequestBody SolicitudProveedor solicitudProveedor){
		ResponseEntity<String> res = ResponseEntity.noContent().build();
		solicitudProveedor.setIdentificacion(imagenes.toFile(solicitudProveedor.getIdentificacion(),solicitudProveedor.getNombreEmpresa()));		
		SolicitudProveedor usu = solicitudProveedorBean.nuevaSolicitud(solicitudProveedor);
		if(usu != null) {
			res = ResponseEntity.ok(usu.getCorreo());
		}
		return res;
	}
	
	@Override
	public String getErrorPath() {
		return "No Mapping Found";
	}
	
	
}
