package com.tt2.service.interfaz;

import java.util.List;
import com.tt2.entity.Consulta;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.ConsultaAux;
import com.tt2.model.MedidasModel;


public interface ArquitectoBeanInterfaz {
	
	Usuario registroArquitecto(Usuario usuario);
	
	Usuario perfilUsuario(int id);
	
	List<Usuario> verProveedores();

	List<Diccionario> getDiccionario();
	
	List<Consulta> getAllConsultas(int id);
	
	ConsultaModel consulta(MedidasModel medidas);
	
	boolean saveConsulta(ConsultaModel consultaModel);
	
	boolean enviarCorreo(ConsultaAux consulta);
	
	boolean eliminarConsulta(ConsultaAux consulta);
}
