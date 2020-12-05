package com.tt2.service.interfaz;

import java.util.List;

import com.tt2.entity.ListaMateriales;
import com.tt2.service.Medidas;

public interface EstimacionBeanInterfaz {

	/*
	 * Este metodo sera el encargado de calcular el material requerido para 
	 * Realizar la construccion 
	 */
	ListaMateriales materialRequerido(Medidas medidas); 
	
	/*
	 *Este metodo mostrara los materiales que usaran 
	 */
	List<ListaMateriales> getListaMateriales();

	/*
	 * Este metodo calculara el costo aproximado de construccion
	 * */
	float calcularCosto(List<ListaMateriales> listaMatariales);
}
