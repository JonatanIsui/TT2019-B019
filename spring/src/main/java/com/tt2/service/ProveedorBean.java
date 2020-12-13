package com.tt2.service;
import java.io.FileInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.tt2.dao.MaterialDao;
import com.tt2.dao.ProveedorDao;
import com.tt2.entity.Material;
import com.tt2.entity.Proveedor;
import com.tt2.model.ArchivoModel;
import com.tt2.service.interfaz.ProveedorBeanInterfaz;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
@Service("proveedorBean")
public class ProveedorBean extends UsuarioBean implements ProveedorBeanInterfaz{

	@Autowired
	@Qualifier("proveedorDao")
	private ProveedorDao proveedorDao;
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;
	
	@Autowired
	@Qualifier("archivoBean")
	private Archivo archivo;

	private Workbook workbook;
	
	@Override
	public List<Material> modificarMaterial(Material material) {
		try {
			Optional<Material> materialOpt = materialDao.findById(material.getId());
			Material aux = materialOpt.get();
			aux.setNombre(material.getNombre());
			aux.setCategoria(material.getCategoria());
			aux.setDescripcion(material.getDescripcion());
			aux.setCosto(material.getCosto());
			materialDao.save(aux);
			return materialDao.findByProveedor(aux.getProveedor());
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Material> altaMaterial(Material material) {
		try {
			Material aux = new Material();
			Optional<Proveedor> proveedor = proveedorDao.findById(material.getProveedor().getId());
			aux.setNombre(material.getNombre());
			aux.setCategoria(material.getCategoria());
			aux.setDescripcion(material.getDescripcion());
			aux.setCosto(material.getCosto());
			aux.setProveedor(proveedor.get());
			materialDao.save(aux);
			return materialDao.findByProveedor(proveedor.get());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Material> bajaMaterial(int id) {
		try {
			Optional<Material> materialOpt = materialDao.findById(id);
			Material material = materialOpt.get();
			Proveedor proveedor = material.getProveedor();
			materialDao.delete(material);
			return materialDao.findByProveedor(proveedor);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Material> catalogo(int id) {
		try {
			Optional<Proveedor> proveedor = proveedorDao.findById(id);
			return materialDao.findByProveedor(proveedor.get());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean agregarArchiv(ArchivoModel archivoModel) {
		boolean res = false;
		Optional<Proveedor> proveedorOpt = proveedorDao.findById(archivoModel.getId());
		Proveedor proveedor = proveedorOpt.get();
		try {
			FileInputStream inputStream = new FileInputStream(archivo.toFile(archivoModel.getCatalogo(),proveedor.getNombreEmpresa()));
			workbook = new XSSFWorkbook(inputStream);
			//Hoja de excel
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			for(int i=1;i<=sheet.getLastRowNum();i++) {
				row = (Row) sheet.getRow(i);
				Material auxMaterial = new Material();
				auxMaterial.setNombre(row.getCell(0).getStringCellValue());
				auxMaterial.setCategoria(row.getCell(1).getStringCellValue());
				auxMaterial.setDescripcion(row.getCell(2).getStringCellValue());
				auxMaterial.setCosto((float) row.getCell(3).getNumericCellValue());
				auxMaterial.setProveedor(proveedor);
				materialDao.save(auxMaterial);
			}
			inputStream.close();
			res = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getFormato() {
		String res = null;
		try {
			res = archivo.toString(); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	
	
}
