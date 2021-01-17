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
import com.tt2.service.Imagenes;


@RestController
@RequestMapping("/administrador")
@CrossOrigin("*")
public class AdministradorRest implements ErrorController{
	@Autowired
	@Qualifier("administradorService")
	private AdministradorBean administradorBean;
	
	@Autowired
	@Qualifier("imagenesBean")
	private Imagenes imagenes;
	
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
		for (int i = 0; i < solicitud.size(); i++) {
			solicitud.get(i).setIdentificacion(imagenes.toString(solicitud.get(i).getIdentificacion()));
		}
		if(!solicitud.isEmpty())
			res = ResponseEntity.ok(solicitud);
		return res;
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable("id") Integer id) {
		String res = "El usuario no se pudo eliminar";
		if(administradorBean.eliminarUsuario(id)) 
			res = "Usuario eliminado con exito";
		return ResponseEntity.ok(res);
	}
	
	@DeleteMapping("/rechazarProveedor/{id}")
	public ResponseEntity<String> rechazarSolicitud(@PathVariable("id") Integer id) {
		String res = "En este momento estamos presentando problema para rechazar la solicitud, intete mas tarde";
		if(administradorBean.rechazarSolicitudProveedor(id)) {
			res = "Solicitud rechazada";
		}
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/aceptarProveedor/{id}")
	public ResponseEntity<String> aceptarProveedor(@PathVariable ("id") Integer id) {
		String res = "No se puede agregar a este proveedor.";
		if(administradorBean.altaProveedor(id)) 
			res = "Proveedor registrado";
		return ResponseEntity.ok(res);
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
	public ResponseEntity<String> eliminarDefinicion(@PathVariable("id") Integer id) {
		String res = "En este momento estamos presentando problema para resolver su peticion";
		if(administradorBean.eliminarDefinicion(id))
			res = "Definicion eliminada";
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/modeficarDefinicion")
	public ResponseEntity<String> modificarDefinicion(@RequestBody Diccionario definicion) {
		String res = "No se pudo modificar la definicion";
		if(administradorBean.modificarDefinicion(definicion))
			res = "Definicion modificada";
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/agregarDefinicion")
	public ResponseEntity<String> agregarDefinicion(@RequestBody Diccionario definicion) {
		String res = "En estos momentos no se pudo agregar la definicion o ya exite";
		if(administradorBean.agregarDefinicion(definicion))
			res = "Definicion agregada con exito";
		return ResponseEntity.ok(res);
	}
	
	@Override
	public String getErrorPath() {
		return "No Mapping Found";
	}

	

}
