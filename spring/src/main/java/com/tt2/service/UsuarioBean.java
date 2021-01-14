package com.tt2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Usuario;
import com.tt2.service.interfaz.UsuarioBeanInterfaz;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service("usuarioBean")
public class UsuarioBean implements UsuarioBeanInterfaz{
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("emailDao")
	private Email email;
	
	@Override
	public Usuario iniciarSesion(String correo,String password) {
		try {
			Usuario aux = usuarioDao.findByCorreoAndPassword(correo, password);
			if(aux!=null) {
				SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
				aux.setFechaLogin(fecha.format(new Date()));
				usuarioDao.save(aux);
				return aux;
			}else {
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean borrarUsuario(String correo,String password) {
		try {
			Usuario usuario = usuarioDao.findByCorreoAndPassword(correo, password);
			usuarioDao.delete(usuario);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean recuperarPassword(String correo) {
		try {
			boolean aux = false;
			Usuario usuario = usuarioDao.findByCorreo(correo);
			if(usuario!=null) {
				if(email.sendEmail(usuario)) {
					aux = true;}
			}
			return aux;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Usuario cambioPassword(Usuario usuario) {
		try {
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Usuario aux = usuarioDao.findByCorreo(usuario.getCorreo());
			aux.setPassword(usuario.getPassword());
			aux.setFechaLogin(fecha.format(new Date()));
			return usuarioDao.save(aux);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
