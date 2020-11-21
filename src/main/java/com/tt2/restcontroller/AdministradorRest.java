package com.tt2.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt2.entity.Diccionario;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.service.AdministradorBean;

@RestController
@RequestMapping("/administrador")
@CrossOrigin("*")
public class AdministradorRest implements ErrorController{
	@Autowired
	@Qualifier("administradorService")
	private AdministradorBean administradorBean;
	
	@PostMapping("/arquitectos")
	public ResponseEntity<List<Usuario>> getAllArquitectos(){
		ResponseEntity<List<Usuario>> res = ResponseEntity.noContent().build();
		List <Usuario> arquitectos = administradorBean.getAllArquitectos();
		if(!arquitectos.isEmpty())
			res = ResponseEntity.ok(arquitectos);
		return res;
	}
	
	@PostMapping("/proveedores")
	public ResponseEntity<List<Usuario>> getAllProveedores(){
		ResponseEntity<List<Usuario>> res = ResponseEntity.noContent().build();
		List <Usuario> proveedores = administradorBean.getProveedores();
		if(!proveedores.isEmpty())
			res = ResponseEntity.ok(proveedores);
		return res;
	}
	
	@PostMapping("/solicitudes")
	public ResponseEntity<List<SolicitudProveedor>> getAllSolicitud(){
		ResponseEntity<List<SolicitudProveedor>> res= ResponseEntity.noContent().build();
		List <SolicitudProveedor> solicitud = administradorBean.getSolicitudes();
		if(!solicitud.isEmpty())
			res = ResponseEntity.ok(solicitud);
		return res;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public String eliminarUsuario(@PathVariable("id") Integer id) {
		String res = "El usuario no se pudo eliminar";
		if(administradorBean.eliminarUsuario(id)) 
			res = "Usuario eliminado con exito";
		return res;
	}
	
	@DeleteMapping("/rechazarProveedor/{id}")
	public String rechazarSolicitud(@PathVariable("id") Integer id) {
		String res = "En este momento estamos presentando problema para rechazar la solicitud, intete mas tarde";
		if(administradorBean.rechazarSolicitudProveedor(id)) {
			res = "Solicitud rechazada";
		}
		return res;
	}
	
	@PostMapping("/diccionario")
	public ResponseEntity<List<Diccionario>> diccionario(){
		ResponseEntity<List<Diccionario>> res = ResponseEntity.noContent().build();
		List<Diccionario> diccionario = administradorBean.getDiccionario();
		if(!diccionario.isEmpty())
			res = ResponseEntity.ok(diccionario);
		return res;
	}
	
	@DeleteMapping("/eliminarDefinicion/{id}")
	public String eliminarDefinicion(@PathVariable("id") Integer id) {
		String res = "En este momento estamos presentando problema para resolver su peticion";
		if(administradorBean.eliminarDefinicion(id))
			res = "Definicion eliminada";
		return res;
	}
	
	@PostMapping("/modeficarDefinicion")
	public String modificarDefinicion(@RequestBody Diccionario definicion) {
		String res = "No se pudo modificar la definicion";
		if(administradorBean.modificarDefinicion(definicion))
			res = "Definicion modificada";
		return res;
	}
	
	@PostMapping("/agregarDefinicion")
	public String agregarDefinicion(@RequestBody Diccionario definicion) {
		String res = "En estos momentos no se pudo agregar la definicion";
		if(administradorBean.agregarDefinicion(definicion))
			res = "Definicion agregada con exito";
		return res;
	}
	
	@Override
	public String getErrorPath() {
		return "No Mapping Found";
	}

	

}
