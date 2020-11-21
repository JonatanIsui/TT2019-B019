package com.tt2.service.interfaz;

import java.util.List;
import com.tt2.entity.Diccionario;

public interface DiccionarioBeanInterfaz {
	List <Diccionario> getTodos();
	boolean modificiar(Diccionario definicion);
	boolean agregar(Diccionario definicion);
	boolean eliminar(Diccionario definicion);
}
