package com.tt2.service;

import java.util.ArrayList;
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
import com.tt2.model.MedidasModel;
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
	
	@Autowired
	@Qualifier("consultaBean")
	private ConsultaBean consultaBean;
	
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

	@Override
	public List<Proveedor> consulta(MedidasModel medidas) {
		List<Proveedor> respuesta = new ArrayList<Proveedor>();
		try {
			consultaBean.varillasLozas(medidas.getAnchoterreno(), medidas.getLargoterreno());
			consultaBean.coladoLozas(medidas.getAnchoterreno(), medidas.getLargoterreno());
			consultaBean.castillos(medidas.getPisos());
			consultaBean.cadenas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			consultaBean.paredesPerimetro(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getLadrillo());
			consultaBean.cuartos(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getLadrillo());
			consultaBean.escalera();
			consultaBean.imprimir();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return respuesta;
	}


}
