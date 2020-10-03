package com.init.construccion1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.init.construccion1.entitys.Arquitecto;


public interface ArquitectoDao extends JpaRepository<Arquitecto,Integer>{
	@Query("FROM arquitecto WHERE idusuario=?1")
	public abstract Arquitecto getByidUs(int idusuario);
}
