package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.MaterialConsulta;
@Repository("materialConsultaDao")
public interface MaterialConsultaDao extends JpaRepository<MaterialConsulta, Integer>{

}
