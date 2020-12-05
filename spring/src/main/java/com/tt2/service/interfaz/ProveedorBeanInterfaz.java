package com.tt2.service.interfaz;

import java.util.List;

import com.tt2.entity.Material;
import com.tt2.model.ArchivoModel;


public interface ProveedorBeanInterfaz {
	public List<Material> modificarMaterial(Material material);
	public List<Material> altaMaterial(Material material);
	public List<Material> bajaMaterial(int id);
	public List<Material> catalogo(int id);
	public boolean agregarArchiv(ArchivoModel archivo);
}
