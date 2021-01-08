package com.tt2.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.ConsultaDao;
import com.tt2.entity.Arquitecto;
import com.tt2.service.interfaz.ConsulBeanInterfaz;

@Service("consulBean")
public class ConsulBean implements ConsulBeanInterfaz {
	@Autowired
	@Qualifier("consultaDao")
	private ConsultaDao consultaDao;

	@Autowired
	@Qualifier("arquitectoDao")
	private ArquitectoDao arquitectoDao;
	
	@Override
	public boolean existeConsulta(int id, String nombre) {
		boolean aux = true;
		Optional<Arquitecto> arquitecto = arquitectoDao.findById(id);
		if(consultaDao.findByNombreAndArquitecto(nombre,arquitecto.get())!=null) {
			aux=false;
		}
		return aux;
	}

}
