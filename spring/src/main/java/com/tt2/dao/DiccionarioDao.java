package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Diccionario;

@Repository ("diccionarioDao")
public interface DiccionarioDao extends JpaRepository<Diccionario, Integer>{

}