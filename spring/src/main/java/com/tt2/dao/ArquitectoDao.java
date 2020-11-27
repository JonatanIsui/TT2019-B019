package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Arquitecto;

@Repository("arquitectoDao")
public interface ArquitectoDao extends JpaRepository <Arquitecto, Integer>{
		
}
