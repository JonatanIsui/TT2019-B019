package com.tt2.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.MaterialDao;
import com.tt2.dao.ProveedorDao;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;
import com.tt2.service.interfaz.ProveedorBeanInterfaz;

@Service("proveedorBean")
public class ProveedorBean extends UsuarioBean implements ProveedorBeanInterfaz{

	@Autowired
	@Qualifier("proveedorDao")
	private ProveedorDao proveedorDao;
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;
	
	@Override
	public boolean modificarMaterial(Material material) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean altaMaterial(Material material) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean bajaMaterial(Material material) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Material> catalogo(int id) {
		Optional<Proveedor> proveedor = proveedorDao.findById(id);
		return materialDao.findByProveedor(proveedor.get());
	}
}
