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
import com.tt2.model.EliminarConsulta;
import com.tt2.model.MedidasModel;
import com.tt2.entity.Consulta;
import com.tt2.entity.Diccionario;
import com.tt2.service.ArquitectoBean;
import com.tt2.service.ConsulBean;

@RestController
@RequestMapping("/arquitecto")
@CrossOrigin("*")
public class ArquitectoRest implements ErrorController{
	
	@Autowired
	@Qualifier("arquitectoBean")
	private ArquitectoBean arquitectoBean;
	
	@Autowired
	@Qualifier("consulBean")
	private ConsulBean consulBean;
	
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
		if(consulBean.existeConsulta(model.getIdArquitecto(), model.getNombre())){
			ConsultaModel respuesta = arquitectoBean.consulta(model);
			if(respuesta != null)
				if(arquitectoBean.saveConsulta(respuesta)) {
					res = ResponseEntity.ok(respuesta);
				}
		}
		return res;
	}
	
	@PostMapping("/proveedores")
	public ResponseEntity<List<Usuario>> proveedores(){
		ResponseEntity<List<Usuario>> res= ResponseEntity.noContent().build();
		List<Usuario> proveedores = arquitectoBean.verProveedores();
		if(!proveedores.isEmpty()) {
			res = ResponseEntity.ok(proveedores);
		}
		return res;
	}
	
	@PostMapping("/elimarConsulta")
	public ResponseEntity<String> eliminaConsulta(@RequestBody EliminarConsulta consulta){
		ResponseEntity<String> res= ResponseEntity.noContent().build();
		if(arquitectoBean.eliminarConsulta(consulta)) {
			res = ResponseEntity.ok("true");
		} 
		return res;
	}
	
	@PostMapping("/consultasGuardas")
	public ResponseEntity<List<Consulta>> allConsultas(@RequestBody EliminarConsulta consulta){
		ResponseEntity<List<Consulta>> res= ResponseEntity.noContent().build();
		List<Consulta> consultas=arquitectoBean.getAllConsultas(consulta.getArquitecto());
		if(!consultas.isEmpty()) {
			res = ResponseEntity.ok(consultas);
		}
		return res;
	}
	
	@Override
	@GetMapping("/error")
	public String getErrorPath() {
		return "No Mapping Found";
	}
}
