package com.tt2.service.interfaz;

import java.util.List;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Proveedor;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;

public interface AdministradorBeanInterfaz {
	
	/*
	 * Este metodo se encargara de regresar los objetos
	 * proveedor(entity) solicitante
	 * */
	List<SolicitudProveedor> getSolicitudes();
	
	/*
	 * Este metodo se encargara de registrar un proveedor
	 * */
	boolean altaProveedor(SolicitudProveedor solicitudProveedor);
	
	/*
	 *Este metodo rechaza solicitud de proveedores 
	 * 
	 */
	boolean rechazarSolicitudProveedor(Integer id);
	
	/*
	 * Este metodo dara de baja un usuario por falta de actividad
	 * o violar las politicas y condiciones 
	 * */
	boolean eliminarUsuario(Integer id);

	/*
	 * Este metodo podra modificar los datos de algun proveedor
	 * 
	 * */
	boolean modificarProveedor(Proveedor proveedor);
	
	/*
	 *Este metodo regresara todos los proveedores registrados en el sistema 
	 * */
	List<Usuario> getProveedores();
	
	/*
	 *Este metodo obtiene todas las definiciones del diccionario
	 */
	List<Diccionario> getDiccionario();
	
	/*
	 *Este metodo modifica una palabra y su definicion 
	 */
	boolean modificarDefinicion(Diccionario definicion);
	
	/*
	 *Este metodo agrega una palabra y su definicion 
	 */
	boolean agregarDefinicion(Diccionario definicion);
	
	/*
	 *Este metodo elimina una palabra y su definicion 
	 */
	boolean eliminarDefinicion(Integer id);

	List<Usuario> getAllArquitectos();
}
