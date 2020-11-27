package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tt2.entity.ListaMateriales;

@Repository("listaMaterialesDao")
public interface ListaMaterialesDao extends JpaRepository <ListaMateriales , Integer>{

}