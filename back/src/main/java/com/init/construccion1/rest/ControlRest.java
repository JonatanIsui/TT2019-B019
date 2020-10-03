package com.init.construccion1.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.init.construccion1.dao.AdministradorDao;
import com.init.construccion1.dao.ArquitectoDao;
import com.init.construccion1.dao.ProveedorDao;
import com.init.construccion1.dao.UsuarioDao;
import com.init.construccion1.entitys.Administrador;
import com.init.construccion1.entitys.Arquitecto;
import com.init.construccion1.entitys.Proveedor;
import com.init.construccion1.entitys.Usuario;

@RestController
@RequestMapping("/inicio")
@CrossOrigin("*")
public class ControlRest {
	@Autowired
	UsuarioDao usuarioDao;
	@Autowired
	ArquitectoDao arqDao;
	@Autowired
	AdministradorDao admDao;
	@Autowired
	ProveedorDao proveDao;

/////////////////////////////////////////////////////////////////////////////////////USUARIOS//////////////	
//Obtener todos los usuarios
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
		List<Usuario> usuario = usuarioDao.findAll();
		return ResponseEntity.ok(usuario);
	}

//Obtener usuario por id
	@GetMapping(value = "/usuario/{id}/")
	public ResponseEntity<Usuario> getById(@PathVariable("id") Integer id) {
		Optional<Usuario> usuario = usuarioDao.findById(id);
		ResponseEntity<Usuario> res = ResponseEntity.noContent().build();
		if (usuario.isPresent()) {
			res = ResponseEntity.ok(usuario.get());
		}
		return res;
	}

//Obtener usuario por contraseña y correo
	@GetMapping(value = "/usuario/{password}/{email}/")
	public ResponseEntity <Usuario> getByEmail(@PathVariable("password") String password, @PathVariable("email") String email) {
		ResponseEntity<Usuario> res = ResponseEntity.noContent().build();
		Usuario obj=usuarioDao.getByEmailPassword(email, password);
		if(obj!=null) {
			res = ResponseEntity.ok(obj);
		}
		return res;
	}

	//Registrar
	@PostMapping("/save")
	public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
		Usuario obj = usuarioDao.save(usuario);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

///Eliminar usuario
	@DeleteMapping(value = "/{id}/")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) {
		usuarioDao.deleteById(id);
		return ResponseEntity.ok(null);
	}
	//Retorna todos los arquitectos
	@GetMapping("/arquitectos")
	public ResponseEntity<List<Usuario>> getArquitecto(){
		List<Usuario> usuario =usuarioDao.getArquitectos();
		return ResponseEntity.ok(usuario);
	}
	//Retorna todos los proveedores
	@GetMapping("/proveedores")
	public ResponseEntity<List<Usuario>> getProveedor(){
		List<Usuario> usuario =usuarioDao.getProveedors();
		return ResponseEntity.ok(usuario);
	}
}
//	// Actualizar
//	@PutMapping("/update/usuario/")
//	public ResponseEntity<Usuario> modificarUsuario(@RequestBody Usuario usuari) {
//		System.out.print("Hola perro");
//		Optional<Usuario> usuario = usuarioDao.findById(usuari.getId());
//		ResponseEntity<Usuario> res = null;
//		if (usuario.isPresent()) {
//			Usuario updateUsu = usuario.get();
//			updateUsu.setEmail(usuari.getEmail());
//			updateUsu.setPassword(usuari.getPassword());
//			usuarioDao.save(updateUsu);
//			res = ResponseEntity.ok(usuario.get());
//		}/* else {
//			res = ResponseEntity.noContent().build();
//		}*/
//		return res;
//	}
//
///////////////////////////////////////////////////////////////////Arquitecto/////////////////////////////////
//	// Crear arquitecto
//	@PostMapping("/save/arquitecto")
//	public ResponseEntity<Arquitecto> saveArquitecto(@RequestBody Arquitecto arquitecto) {
//		Arquitecto obj = arqDao.save(arquitecto);
//		return new ResponseEntity<Arquitecto>(obj, HttpStatus.OK);
//	}
//
//	// Eliminar arquitecto
//	@DeleteMapping(value = "/arquitecto/{id}/")
//	public ResponseEntity<Void> deleteArqById(@PathVariable("id") Integer id) {
//		arqDao.deleteById(id);
//		return ResponseEntity.ok(null);
//	}
//
//	// Obtener arquitecto por id
//	@GetMapping(value = "/arquitecto/{id}/")
//	public ResponseEntity<Arquitecto> getArqById(@PathVariable("id") Integer id) {
//		Optional<Arquitecto> arquitecto = arqDao.findById(id);
//		ResponseEntity<Arquitecto> res = null;
//		if (arquitecto.isPresent()) {
//			res = ResponseEntity.ok(arquitecto.get());
//		} else {
//			res = ResponseEntity.noContent().build();
//		}
//		return res;
//	}
//
//	// Obtener todos los arquitectos
//	@GetMapping("/all/arquitectos")
//	public ResponseEntity<List<Arquitecto>> getArqAll() {
//		List<Arquitecto> arquitecto = arqDao.findAll();
//		return ResponseEntity.ok(arquitecto);
//	}
//
//	// Actualizar
//	@PutMapping("/update/arquitecto/")
//	public ResponseEntity<Arquitecto> modificarArq(@RequestBody Arquitecto arquitect) {
//		Optional<Arquitecto> arquitecto = arqDao.findById(arquitect.getId());
//		ResponseEntity<Arquitecto> res = null;
//		if (arquitecto.isPresent()) {
//			Arquitecto updateArq = arquitecto.get();
//			updateArq.setNombre(arquitect.getNombre());
//			updateArq.setApellido(arquitect.getApellido());
//			arqDao.save(updateArq);
//			res = ResponseEntity.ok(arquitecto.get());
//		} else {
//			res = ResponseEntity.noContent().build();
//		}
//		return res;
//	}
//
///////////////////////////////////////////////////////////////////Proveedor/////////////////////////////////
//	// Crear proveedor
//	@PostMapping("/save/proveedor")
//	public ResponseEntity<Proveedor> saveProveedor(@RequestBody Proveedor proveedor) {
//		Proveedor obj = proveDao.save(proveedor);
//		return new ResponseEntity<Proveedor>(obj, HttpStatus.OK);
//	}
//
//	// Eliminar proveedor
//	@DeleteMapping(value = "/proveedor/{id}/")
//	public ResponseEntity<Void> deleteProveedorById(@PathVariable("id") Integer id) {
//		arqDao.deleteById(id);
//		return ResponseEntity.ok(null);
//	}
//
//	// Obtener proveedor por id
//	@GetMapping(value = "/proveedor/{id}/")
//	public ResponseEntity<Proveedor> getProveedorById(@PathVariable("id") Integer id) {
//		Optional<Proveedor> proveedor = proveDao.findById(id);
//		ResponseEntity<Proveedor> res = null;
//		if (proveedor.isPresent()) {
//			res = ResponseEntity.ok(proveedor.get());
//		} else {
//			res = ResponseEntity.noContent().build();
//		}
//		return res;
//	}
//
//	// Obtener todos los proveedores
//	@GetMapping("/all/proveedores")
//	public ResponseEntity<List<Proveedor>> getProveedorAll() {
//		List<Proveedor> proveedor = proveDao.findAll();
//		return ResponseEntity.ok(proveedor);
//	}
//
///////////////////////////////////////////////////////////////////Administrador/////////////////////////////////
//	// Crear administrador
//	@PostMapping("/save/administrador")
//	public ResponseEntity<Administrador> saveAdministrador(@RequestBody Administrador administrador) {
//		Administrador obj = admDao.save(administrador);
//		return new ResponseEntity<Administrador>(obj, HttpStatus.OK);
//	}
//
//	// Eliminar administrador
//	@DeleteMapping(value = "/administrador/{id}/")
//	public ResponseEntity<Void> deleteAdmById(@PathVariable("id") Integer id) {
//		admDao.deleteById(id);
//		return ResponseEntity.ok(null);
//	}
//
//	// Obtener administrador por id
//	@GetMapping(value = "/administrador/{id}/")
//	public ResponseEntity<Administrador> getAdmById(@PathVariable("id") Integer id) {
//		Optional<Administrador> administrador = admDao.findById(id);
//		ResponseEntity<Administrador> res = null;
//		if (administrador.isPresent()) {
//			res = ResponseEntity.ok(administrador.get());
//		} else {
//			res = ResponseEntity.noContent().build();
//		}
//		return res;
//	}

