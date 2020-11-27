package com.tt2.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;


@Repository("materialDao")
public interface MaterialDao extends JpaRepository <Material, Integer>{
	public abstract List<Material> findByProveedor(Proveedor proveedor);
}