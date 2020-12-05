package com.tt2.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt2.entity.Material;
import com.tt2.model.ArchivoModel;
import com.tt2.service.ProveedorBean;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin("*")
public class ProveedorRest {
	@Autowired
	@Qualifier("proveedorBean")
	private ProveedorBean proveedorBean;
	
	@PostMapping("/catalogo")
	public ResponseEntity<List<Material>> getCatalogo(@RequestBody Material material){
		ResponseEntity<List<Material>> res = ResponseEntity.noContent().build();
		List<Material> catalogo = proveedorBean.catalogo(material.getId());
		if(!catalogo.isEmpty())
			res = ResponseEntity.ok(catalogo);
		return res;
	}
	
	@PostMapping("/eliminar")
	public ResponseEntity<List<Material>> eliminarMaterial(@RequestBody Material material){
		ResponseEntity<List<Material>> res = ResponseEntity.noContent().build();
		List<Material> catalogo = proveedorBean.bajaMaterial(material.getId());
		if(!catalogo.isEmpty())
			res = ResponseEntity.ok(catalogo);
		return res;
	}
	
	@PostMapping("/agregar")
	public ResponseEntity<List<Material>> agregarMaterial(@RequestBody Material material){
		ResponseEntity<List<Material>> res = ResponseEntity.noContent().build();
		List<Material> catalogo = proveedorBean.altaMaterial(material);
		if(!catalogo.isEmpty())
			res = ResponseEntity.ok(catalogo);
		return res;
	}
	
	@PostMapping("/modificar")
	public ResponseEntity<List<Material>> modificarMaterial(@RequestBody Material material){
		ResponseEntity<List<Material>> res = ResponseEntity.noContent().build();
		List<Material> catalogo = proveedorBean.modificarMaterial(material);
		if(!catalogo.isEmpty())
			res = ResponseEntity.ok(catalogo);
		return res;
	}
	
	@PostMapping("/archivo")
	public String agregarArchivo(@RequestBody ArchivoModel archivo) {
		String res = null;
		if(proveedorBean.agregarArchiv(archivo));
			res = "Catalogo agregado con exito";
		return res;
	}
	
}
