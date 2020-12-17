package com.tt2.dao;

import com.tt2.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("administradorDao")
public interface AdministradorDao extends JpaRepository<Usuario, Integer>{
	@Query("FROM Usuario WHERE arquitecto IS NOT NULL")
	public abstract List<Usuario> getAllArquitectos();
	
	@Query("FROM Usuario WHERE proveedor IS NOT NULL")
	public abstract List<Usuario> getAllProveedores();
	
}
