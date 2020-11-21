package com.tt2.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Material;


@Repository("materialDao")
public interface MaterialDao extends JpaRepository <Material, Integer>{

}