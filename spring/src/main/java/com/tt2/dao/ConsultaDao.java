package com.tt2.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tt2.entity.Arquitecto;
import com.tt2.entity.Consulta;

@Repository("consultaDao")
public interface ConsultaDao extends JpaRepository<Consulta, Integer> {
	public abstract Consulta findByNombreAndArquitecto(String nombre,Arquitecto arquitecto);
	public abstract List<Consulta> findByArquitecto(Arquitecto arquitecto);
}
