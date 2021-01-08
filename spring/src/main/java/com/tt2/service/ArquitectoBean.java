package com.tt2.service;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.ConsultaDao;
import com.tt2.dao.DiccionarioDao;
import com.tt2.dao.MaterialDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Consulta;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.MedidasModel;
import com.tt2.service.interfaz.ArquitectoBeanInterfaz;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

@Service("arquitectoBean")
public class ArquitectoBean extends UsuarioBean implements ArquitectoBeanInterfaz{

	@Autowired
	@Qualifier("arquitectoDao")
	private ArquitectoDao arquitectoDao;
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("diccionarioDao")
	private DiccionarioDao diccionarioDao;
	
	@Autowired
	@Qualifier("consultaBean")
	private ConsultaBean consultaBean;
	
	@Autowired
	@Qualifier("consultaDao")
	private ConsultaDao consultaDao;
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;
	
	@Autowired
	@Qualifier("proveedorConsulta")
	private ProveedorConsulta proveedorConsulta;
	
	@Override
	public Usuario registroArquitecto(Usuario usuario) {
		Usuario aux = usuarioDao.findByCorreo(usuario.getCorreo());
		if(aux == null) {
			aux = usuarioDao.save(usuario);
		}else {
			aux=null;
		}
		return aux;
	}

	@Override
	public List<Proveedor> verProveedores() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Diccionario> getDiccionario() {
		return diccionarioDao.findAll();
	}

	@Override
	public ConsultaModel consulta(MedidasModel medidas) {
		Optional<Arquitecto> arquitectoOption = arquitectoDao.findById(medidas.getIdArquitecto());
		try {
			consultaBean.varillasLozas(medidas.getAnchoterreno(), medidas.getLargoterreno());
			consultaBean.coladoLozas(medidas.getAnchoterreno(), medidas.getLargoterreno());
			consultaBean.castillos(medidas.getPisos());
			consultaBean.cadenas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			consultaBean.paredesPerimetro(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getTipoladrillo());
			consultaBean.cuartos(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getTipoladrillo());
			consultaBean.escalera();
			consultaBean.imprimir();
			ConsultaModel consulta = consultaBean.getConsulta();
			consulta.setArquitecto(arquitectoOption.get());
			consulta.setAnchoHabitacion1(medidas.getAnchoHabitacion1());
			consulta.setAnchoHabitacion2(medidas.getAnchoHabitacion2());
			consulta.setAnchobano(medidas.getAnchobano());
			consulta.setAnchococina(medidas.getAnchococina());
			consulta.setAnchoterreno(medidas.getAnchoterreno());
			consulta.setLargoHabitacion1(medidas.getLargoHabitacion1());
			consulta.setLargoHabitacion2(medidas.getLargoHabitacion2());
			consulta.setLargobano(medidas.getLargobano());
			consulta.setLargococina(medidas.getLargococina());
			consulta.setLargoterreno(medidas.getLargoterreno());
			consulta.setPisos(medidas.getPisos());
			consulta.setTipoLadrillo(medidas.getTipoladrillo());
			consulta.setNombre(medidas.getNombre());
			consulta.setExcel("false");
			consulta=proveedorConsulta.selectProveedor(consulta);
			saveConsulta(consulta);
			return consulta;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean saveConsulta(ConsultaModel consultaModel) {
		Consulta consulta = new Consulta();
		try {
			consulta.setNombre(consultaModel.getNombre());
			consulta.setAgua(consultaModel.getAgua());
			consulta.setArena(consultaModel.getArena());
			consulta.setGrava(consultaModel.getGrava());
			consulta.setSaco(consultaModel.getSaco());
			consulta.setSacoMortero(consultaModel.getSacoMortero());
			consulta.setVarilla(consultaModel.getVarilla());
			consulta.setLadrilloRojo(consultaModel.getLadrilloRojo());
			consulta.setLadrilloBlockLigero(consultaModel.getLadrilloBlockLigero());
			consulta.setLadrilloBloackPesado(consultaModel.getLadrilloBloackPesado());
			consulta.setArquitecto(consultaModel.getArquitecto());
			consulta.setAnchoHabitacion1(consultaModel.getAnchoHabitacion1());
			consulta.setAnchoHabitacion2(consultaModel.getAnchoHabitacion2());
			consulta.setAnchobano(consultaModel.getAnchobano());
			consulta.setAnchococina(consultaModel.getAnchococina());
			consulta.setAnchoterreno(consultaModel.getAnchoterreno());
			consulta.setLargoHabitacion1(consultaModel.getLargoHabitacion1());
			consulta.setLargoHabitacion2(consultaModel.getLargoHabitacion2());
			consulta.setLargobano(consultaModel.getLargobano());
			consulta.setLargococina(consultaModel.getLargococina());
			consulta.setLargoterreno(consultaModel.getLargoterreno());
			consulta.setPisos(consultaModel.getPisos());
			if(consultaModel.getTipoladrillo()==1) {
				consulta.setTipoladrillo("Ladrillo rojo");
			}else {
				consulta.setTipoladrillo("Ladrillo de block");
			}
			consulta.setArenaCosto(consultaModel.getArenaCosto());
			consulta.setGravaCosto(consultaModel.getGravaCosto());
			consulta.setSacoCosto(consultaModel.getSacoCosto());
			consulta.setSacoMorteroCosto(consultaModel.getSacoMorteroCosto());
			consulta.setVarillaCosto(consultaModel.getVarillaCosto());
			consulta.setLadrilloRojoCosto(consultaModel.getLadrilloRojo());
			consulta.setLadrilloBlockLigeroCosto(consultaModel.getLadrilloBlockLigeroCosto());
			consulta.setLadrilloBloackPesadoCosto(consultaModel.getLadrilloBloackPesadoCosto());
			consulta.setNombreProveedor(consultaModel.getNombreProveedor());
			consulta.setTelefonoProveedor(consultaModel.getTelefonoProveedor());
			consulta.setCorreoProveedor(consultaModel.getCorreoProveedor());
			consulta.setDireccionProveedor(consultaModel.getDireccionProveedor());
			consulta.setTotalConsulta(consultaModel.getTotal());
			consulta.setExcel(generarExcel(consulta));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String generarExcel(Consulta consulta){
		String[] columnas= {"Nombre","Cantidad","Precio promedio por pieza","Sub total"};
		String[] nombreMaterial = {
				"ladrillo rojo",
				"ladrillo block ligero",
				"ladrillo block pesado",
				"Saco de cemento",
				"Saco de mortero",
				"arena",
				"grava",
				"varilla",
				"alambre",
				"Botes de agua de 19 L"};
		try {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet =workbook.createSheet(consulta.getNombre());
			Row row = sheet.createRow(0);
			for(int i=0;i<columnas.length;i++){
				Cell cell=row.createCell(i);
				cell.setCellValue(columnas[i]);
			}
			row = sheet.createRow(1);
			row.createCell(0).setCellValue(nombreMaterial[0]);
			row.createCell(1).setCellValue(consulta.getLadrilloRojo());
			row.createCell(2).setCellValue(consulta.getLadrilloRojoCosto());
			row.createCell(3).setCellValue(consulta.getLadrilloRojo()*consulta.getLadrilloRojoCosto());
			
			row = sheet.createRow(2);
			row.createCell(0).setCellValue(nombreMaterial[1]);
			row.createCell(1).setCellValue(consulta.getLadrilloBlockLigero());
			row.createCell(2).setCellValue(consulta.getLadrilloBlockLigeroCosto());
			row.createCell(3).setCellValue(consulta.getLadrilloBlockLigero()*consulta.getLadrilloBlockLigeroCosto());
			
			row = sheet.createRow(3);
			row.createCell(0).setCellValue(nombreMaterial[2]);
			row.createCell(1).setCellValue(consulta.getLadrilloBloackPesado());
			row.createCell(2).setCellValue(consulta.getLadrilloBloackPesadoCosto());
			row.createCell(3).setCellValue(consulta.getLadrilloBloackPesado()*consulta.getLadrilloBloackPesadoCosto());
			
			row = sheet.createRow(4);
			row.createCell(0).setCellValue(nombreMaterial[3]);
			row.createCell(1).setCellValue(consulta.getSaco());
			row.createCell(2).setCellValue(consulta.getSacoCosto());
			row.createCell(3).setCellValue(consulta.getSaco()*consulta.getSacoCosto());
			
			row = sheet.createRow(5);
			row.createCell(0).setCellValue(nombreMaterial[4]);
			row.createCell(1).setCellValue(consulta.getSacoMortero());
			row.createCell(2).setCellValue(consulta.getSacoMorteroCosto());
			row.createCell(3).setCellValue(consulta.getSacoMortero()*consulta.getSacoMorteroCosto());
			
			row = sheet.createRow(6);
			row.createCell(0).setCellValue(nombreMaterial[5]);
			row.createCell(1).setCellValue(consulta.getArena());
			row.createCell(2).setCellValue(consulta.getArenaCosto());
			row.createCell(3).setCellValue(consulta.getArena()*consulta.getArenaCosto());
			
			row = sheet.createRow(7);
			row.createCell(0).setCellValue(nombreMaterial[6]);
			row.createCell(1).setCellValue(consulta.getGrava());
			row.createCell(2).setCellValue(consulta.getGravaCosto());
			row.createCell(3).setCellValue(consulta.getGrava()*consulta.getGravaCosto());
			
			row = sheet.createRow(8);
			row.createCell(0).setCellValue(nombreMaterial[7]);
			row.createCell(1).setCellValue(consulta.getVarilla());
			row.createCell(2).setCellValue(consulta.getVarillaCosto());
			row.createCell(3).setCellValue(consulta.getVarilla()*consulta.getVarillaCosto());
			
			row = sheet.createRow(9);
			row.createCell(0).setCellValue(nombreMaterial[8]);
			row.createCell(1).setCellValue(consulta.getAgua());
			row.createCell(2).setCellValue("44.94");
			row.createCell(3).setCellValue("44.94");
			String url = System.getProperty("user.dir")+"\\Archivos";
			FileOutputStream out = new FileOutputStream(url+"Excel.xls");
			workbook.write(out);
			workbook.close();
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
