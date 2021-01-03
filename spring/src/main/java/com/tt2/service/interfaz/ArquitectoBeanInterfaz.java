package com.tt2.service.interfaz;

import java.util.List;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.MedidasModel;


public interface ArquitectoBeanInterfaz {
	
	Usuario registroArquitecto(Usuario usuario);
	
	List<Proveedor> verProveedores();

	List<Diccionario> getDiccionario();
	
	ConsultaModel consulta(MedidasModel medidas);
}
