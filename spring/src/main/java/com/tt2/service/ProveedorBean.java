package com.tt2.service;
import java.io.FileInputStream;
import java.util.Arrays;
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
	List<String> nombreMaterial = Arrays.asList(
			"ladrillo rojo",
			"ladrillo block ligero",
			"ladrillo block pesado",
			"cemento",
			"mortero",
			"arena",
			"grava",
			"varilla",
			"alambre");
	
	@Override
	public List<Material> modificarMaterial(Material material) {
		try {
			Optional<Material> materialOpt = materialDao.findById(material.getId());
			Material aux = materialOpt.get();
			if(nombreMaterial.contains(material.getNombre().toLowerCase())) {
				if(materialDao.findByProveedorAndNombre(material.getProveedor(),material.getNombre().toLowerCase())==null){
					aux.setNombre(material.getNombre());
				}
			}
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
			System.out.println(materialDao.findByProveedorAndNombre(proveedor.get(),material.getNombre().toLowerCase()));
			if(materialDao.findByClave(material.getClave()) == null) {
				if(materialDao.findByProveedorAndNombre(proveedor.get(),material.getNombre().toLowerCase())==null){
					aux.setClave(material.getClave());
					aux.setNombre(material.getNombre());
					aux.setCategoria(material.getCategoria());
					aux.setDescripcion(material.getDescripcion());
					aux.setCosto(material.getCosto());
					aux.setProveedor(proveedor.get());
					materialDao.save(aux);
				}
			}
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
		boolean res= false;
		boolean aux = true;
		Optional<Proveedor> proveedorOpt = proveedorDao.findById(archivoModel.getId());
		Proveedor proveedor = proveedorOpt.get();
		try {
			FileInputStream inputStream = new FileInputStream(archivo.toFile(archivoModel.getCatalogo(),proveedor.getNombreEmpresa()));
			workbook = new XSSFWorkbook(inputStream);
			//Hoja de excel
			Sheet sheet = workbook.getSheetAt(0);
			Row row;
			for(int j=1;j<=sheet.getLastRowNum();j++ ){
				row = (Row) sheet.getRow(j);
				if(row.getCell(1) != null && nombreMaterial.contains(row.getCell(1).getStringCellValue().toLowerCase())) {
					if(materialDao.findByProveedorAndNombre(proveedor,row.getCell(1).getStringCellValue().toLowerCase())!=null){
						aux = false;
						break;
					}
				}
				if(row.getCell(0) == null) {
					aux = false;
					break;
				}
				if(row.getCell(1) ==null){
					aux = false;
					break;
				}else if(row.getCell(2) ==null){
					aux = false;
					break;
				}else if(row.getCell(3) ==null){
					aux = false;
					break;
				}else if(row.getCell(4) ==null){
					aux = false;
					break;
				}
			}
			
			if(aux) {
				for(int i=1;i<=sheet.getLastRowNum();i++) {
					row = (Row) sheet.getRow(i);
					Material auxMaterial = new Material();
					if(materialDao.findByClave(row.getCell(0).getStringCellValue()) != null) {
						auxMaterial = materialDao.findByClave(row.getCell(0).getStringCellValue());
					}else {
						auxMaterial.setClave(row.getCell(0).getStringCellValue());
					}
					auxMaterial.setNombre(row.getCell(1).getStringCellValue().toLowerCase());
					auxMaterial.setCategoria(row.getCell(2).getStringCellValue());
					auxMaterial.setDescripcion(row.getCell(3).getStringCellValue());
					auxMaterial.setCosto((float) row.getCell(4).getNumericCellValue());
					auxMaterial.setProveedor(proveedor);
					materialDao.save(auxMaterial);
				}
				res = true;
			}
			inputStream.close();
		}catch(Exception e) {
			e.printStackTrace();
			res = false;
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
