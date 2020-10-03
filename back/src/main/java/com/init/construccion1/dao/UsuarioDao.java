package com.init.construccion1.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.init.construccion1.entitys.Usuario;
//JpaRepository incluye buscar por id, all, delet, etc los mas usados
public interface UsuarioDao extends JpaRepository<Usuario, Integer>{
	@Query("FROM usuario WHERE email=?1 AND password=?2")
	public abstract Usuario getByEmailPassword(String email, String password);
	
	@Query("FROM usuario WHERE tipo='arquitecto'")
	public abstract List<Usuario> getArquitectos();
	
	@Query("FROM usuario WHERE tipo='proveedor'")
	public abstract List<Usuario> getProveedors();
}
