package com.tt2.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tt2.entity.Arquitecto;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;

@Repository("usuarioDao")
public interface UsuarioDao extends JpaRepository<Usuario,Integer>{
	/*Correo y Password son atributos de la entidad 
	 * JpaRepository permite que en el nombre del metodo pongas los atributos para que 
	 * genere la consulta con esos para metros 
	 */
	/*Retorna el usuario mediante la contraseña y
	 *  password
	 */
	public abstract Usuario findByCorreoAndPassword(String correo,String password);	

	/*
	 * Retorna el usuario mediante correo para la 
	 * recuperacion de contraseña
	 */
	public abstract Usuario findByCorreo(String correo);
	public abstract Usuario findByProveedor(Proveedor proveedor);
	@Query("FROM Usuario WHERE proveedor IS NOT NULL")
	public abstract List<Usuario> findAllProveedor();
	public abstract Usuario findByArquitecto(Arquitecto arquitecto);
}