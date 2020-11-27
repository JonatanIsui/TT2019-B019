package com.tt2.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Usuario;
import com.tt2.service.interfaz.UsuarioBeanInterfaz;

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
		return usuarioDao.findByCorreoAndPassword(correo, password);
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
		boolean res = false;
		Usuario usuario = usuarioDao.findByCorreo(correo);
		if(email.sendEmail(usuario))
			res = true;
		return res;
	}
	
	@Override
	public Usuario cambioPassword(Usuario usuario) {
		Usuario aux = usuarioDao.findByCorreo(usuario.getCorreo());
		aux.setPassword(usuario.getPassword());
		return usuarioDao.save(aux);
	}

}
