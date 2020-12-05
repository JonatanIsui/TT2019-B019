package com.tt2.restcontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tt2.entity.Material;
import com.tt2.service.ProveedorBean;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin("*")
public class ProveedorRest {
	@Autowired
	@Qualifier("proveedorBean")
	private ProveedorBean proveedorBean;
	
	@PostMapping("/catalogo/{id}")
	public ResponseEntity<List<Material>> getCatalogo(@PathVariable("id")Integer id){
		ResponseEntity<List<Material>> res = ResponseEntity.noContent().build();
		List<Material> catalogo = proveedorBean.catalogo(id);
		if(!catalogo.isEmpty())
			res = ResponseEntity.ok(catalogo);
		return res;
	}
}
