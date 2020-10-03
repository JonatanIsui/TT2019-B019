package com.init.construccion1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.init.construccion1.entitys.Administrador;

public interface AdministradorDao extends JpaRepository<Administrador,Integer>{
	@Query("FROM administrador WHERE idusuario=?1")
	public abstract Administrador getByidUs(int idusuario);
}
