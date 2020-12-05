package com.tt2.service.interfaz;

import java.util.List;

import com.tt2.entity.Material;


public interface ProveedorBeanInterfaz {
	//public boolean cargarCatalogo(Proveedor proveedor,Archivo archivo);
	public boolean modificarMaterial(Material material);
	public boolean altaMaterial(Material material);
	public boolean bajaMaterial(Material material);
	public List<Material> catalogo(int id);
}
