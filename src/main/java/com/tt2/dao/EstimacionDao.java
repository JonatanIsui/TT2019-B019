package com.tt2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Estimacion;

@Repository("estimacionDao")
public interface EstimacionDao extends JpaRepository<Estimacion , Integer>{
	
}
