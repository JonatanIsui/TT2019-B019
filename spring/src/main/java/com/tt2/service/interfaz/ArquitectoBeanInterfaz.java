package com.tt2.service.interfaz;

import java.util.List;

import com.tt2.entity.Diccionario;
import com.tt2.entity.Estimacion;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;
import com.tt2.service.Medidas;


public interface ArquitectoBeanInterfaz {
	
	Usuario registroArquitecto(Usuario usuario);
	
	Estimacion ingresarMedidas(Medidas medidas);

	List<Estimacion> verConsultasAnteriores();

	List<Proveedor> verProveedores();

	List<Diccionario> getDiccionario();
}
