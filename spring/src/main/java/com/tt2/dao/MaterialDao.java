package com.tt2.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;


@Repository("materialDao")
public interface MaterialDao extends JpaRepository <Material, Integer>{
	public abstract List<Material> findByProveedor(Proveedor proveedor);
	@Query("select AVG(costo) from Material where nombre='Grava'")
	public abstract float promedioCostoGrava();
	@Query("select AVG(costo) from Material where nombre='Arena'")
	public abstract float promedioCostoArena();
	@Query("select AVG(costo) from Material where nombre='Cemento'")
	public abstract float promedioCostoCemento();
	@Query("select AVG(costo) from Material where nombre='Mortero'")
	public abstract float promedioCostoMortero();
	@Query("select AVG(costo) from Material where nombre='Varilla'")
	public abstract float promedioCostoVarilla();
	@Query("select AVG(costo) from Material where nombre='Ladrillo Rojo'")
	public abstract float promedioCostoladrilloRojo();
	@Query("select AVG(costo) from Material where nombre='Ladrillo Block Ligero'")
	public abstract float promedioCostoladrilloLigero();
	@Query("select AVG(costo) from Material where nombre='Ladrillo Block Pesado'")
	public abstract float promedioCostoladrilloPesado();
	@Query("select AVG(costo) from Material where nombre='Alambre'")
	public abstract float promedioCostoAlambre();
	@Query("select AVG(costo) from Material where nombre='Varilla Armex'")
	public abstract float promedioCostoVarillaArmex();
	public abstract Material findByClaveAndProveedor(String clave,Proveedor proveedor);
	public abstract Material findByProveedorAndNombre(Proveedor proveedor,String nombre);
	
}