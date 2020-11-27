package com.tt2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.DiccionarioDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Estimacion;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;
import com.tt2.service.interfaz.ArquitectoBeanInterfaz;

@Service("arquitectoBean")
public class ArquitectoBean extends UsuarioBean implements ArquitectoBeanInterfaz{

	@Autowired
	@Qualifier("arquitectoDao")
	private ArquitectoDao arquitectoDao;
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("diccionarioDao")
	private DiccionarioDao diccionarioDao;
	
	@Override
	public Usuario registroArquitecto(Usuario usuario) {
		Usuario aux = null;
		if(usuarioDao.findByCorreo(usuario.getCorreo()) == null) {
			aux = usuarioDao.save(usuario);
		}
		return aux;
	}

	@Override
	public Estimacion ingresarMedidas(Medidas medidas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estimacion> verConsultasAnteriores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Proveedor> verProveedores() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diccionario> getDiccionario() {
		return diccionarioDao.findAll();
	}


}
