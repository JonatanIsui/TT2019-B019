package com.tt2.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.MedidasModel;
import com.tt2.entity.Diccionario;
import com.tt2.service.ArquitectoBean;

@RestController
@RequestMapping("/arquitecto")
@CrossOrigin("*")
public class ArquitectoRest implements ErrorController{
	
	@Autowired
	@Qualifier("arquitectoBean")
	private ArquitectoBean arquitectoBean;
	
	@PostMapping("/registroArquitecto")
	public ResponseEntity <Usuario> registrarArquitecto(@RequestBody Usuario usuario){
		ResponseEntity<Usuario> res = ResponseEntity.noContent().build();
		Usuario aux = arquitectoBean.registroArquitecto(usuario);
		if(aux != null)
			res = ResponseEntity.ok(aux);
		return res;
	}
	
	@PostMapping("/diccionario")
	public ResponseEntity<List<Diccionario>> diccionario(){
		ResponseEntity<List<Diccionario>> res= ResponseEntity.noContent().build();
		List<Diccionario> diccionario = arquitectoBean.getDiccionario();
		if(!diccionario.isEmpty())
			res = ResponseEntity.ok(diccionario);
		return res;
	}
	
	@PostMapping("/consulta")
	public ResponseEntity<ConsultaModel> consulta(@RequestBody MedidasModel model){
		ResponseEntity<ConsultaModel> res= ResponseEntity.noContent().build();
		ConsultaModel respuesta = arquitectoBean.consulta(model);
		if(respuesta != null)
			res = ResponseEntity.ok(respuesta);
		return res;
	}
	@Override
	@GetMapping("/error")
	public String getErrorPath() {
		return "No Mapping Found";
	}
	
}
