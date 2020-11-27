package com.tt2.restcontroller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tt2.entity.Usuario;
import com.tt2.service.UsuarioBean;

@RestController
@RequestMapping("/index")
@CrossOrigin("*")
public class UsuarioRest implements ErrorController{
	@Autowired
	@Qualifier("usuarioBean")
	private UsuarioBean usuarioBean;
	
	@PostMapping("/login")
	public  ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
		//Error 204
		ResponseEntity<Usuario> res = ResponseEntity.noContent().build();
		Usuario usuarioResponse = usuarioBean.iniciarSesion(usuario.getCorreo(), usuario.getPassword());
		if(usuarioResponse != null)
			res = ResponseEntity.ok(usuarioResponse);
		return res;
	}
	
	@GetMapping(value = "/recuperarPassword/{email}/")
	public String recuperarPassword(@PathVariable("email") String email) {
		String res = "El correo ingresado no se encuentra segistrado";
		if(usuarioBean.recuperarPassword(email))
			res = "Se a enviado el link a su correo";
		return res;
	}
	
	@PostMapping("/cambioPassword")
	public ResponseEntity<Usuario> cambioPassword(@RequestBody Usuario usuario){
		ResponseEntity<Usuario> res = ResponseEntity.noContent().build();
		//Descifrar email
		String decoded = new String(Base64.getDecoder().decode(usuario.getCorreo()));
		usuario.setCorreo(decoded);
		Usuario aux = usuarioBean.cambioPassword(usuario);
		if(aux != null)
			res = ResponseEntity.ok(aux);
		return res;
	}
	
	@Override
	@GetMapping("/error")
	public String getErrorPath() {
		return "No Mapping Found";
	}
}
