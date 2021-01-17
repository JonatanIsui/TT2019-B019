package com.tt2.service;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.ArquitectoDao;
import com.tt2.dao.ConsultaDao;
import com.tt2.dao.DiccionarioDao;
import com.tt2.dao.MaterialDao;
import com.tt2.dao.ProveedorDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Consulta;
import com.tt2.entity.ConsultaProveedor;
import com.tt2.entity.Diccionario;
import com.tt2.entity.MaterialConsulta;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.ConsultaAux;
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
	@Qualifier("proveedorDao")
	private ProveedorDao proveedorDao;
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;
	
	@Autowired
	@Qualifier("proveedorConsulta")
	private ProveedorConsulta proveedorConsulta;
	
	@Autowired
	@Qualifier("emailDao")
	private Email emailDao;
	
	@Override
	public Usuario registroArquitecto(Usuario usuario) {
		Usuario aux = usuarioDao.findByCorreo(usuario.getCorreo());
		if(aux == null) {
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			usuario.setFechaLogin(fecha.format(new Date()));
			aux = usuarioDao.save(usuario);
		}else {
			aux=null;
		}
		return aux;
	}

	@Override
	public List<Usuario> verProveedores() {
		return usuarioDao.findAllProveedor();
	}
	@Override
	public List<Diccionario> getDiccionario() {
		return diccionarioDao.findAll();
	}

	@Override
	public ConsultaModel consulta(MedidasModel medidas) {
		Optional<Arquitecto> arquitectoOption = arquitectoDao.findById(medidas.getIdArquitecto());
		try {
			consultaBean.varillasLozas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			consultaBean.coladoLozas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			//consultaBean.castillos(medidas.getPisos());
			consultaBean.cadenas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			consultaBean.paredesPerimetro(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getTipoladrillo());
			consultaBean.cuartos(medidas.getPisos(),medidas.getAnchoHabitacion1(),medidas.getLargoHabitacion1(),medidas.getAnchoHabitacion2(),medidas.getLargoHabitacion2(),
								medidas.getAnchobano(),medidas.getLargobano(),medidas.getAnchococina(),medidas.getLargococina(),medidas.getAncholavado(),medidas.getLargolavado(),
								medidas.getTipoladrillo()
					);
			consultaBean.escalera(medidas.getPisos());
			consultaBean.cimentacion(medidas.getAnchoterreno(), medidas.getLargoterreno());
			consultaBean.imprimir();
			ConsultaModel consulta = consultaBean.getConsulta();
			consulta.setArquitecto(arquitectoOption.get());
			consulta.setAnchoHabitacion1(medidas.getAnchoHabitacion1());
			consulta.setAnchoHabitacion2(medidas.getAnchoHabitacion2());
			consulta.setAnchoLavado(medidas.getAncholavado());
			consulta.setAnchobano(medidas.getAnchobano());
			consulta.setAnchococina(medidas.getAnchococina());
			consulta.setAnchoterreno(medidas.getAnchoterreno());
			consulta.setLargoHabitacion1(medidas.getLargoHabitacion1());
			consulta.setLargoHabitacion2(medidas.getLargoHabitacion2());
			consulta.setLargobano(medidas.getLargobano());
			consulta.setLargococina(medidas.getLargococina());
			consulta.setLargoLavado(medidas.getLargolavado());
			consulta.setLargoSala(medidas.getLargoSala());
			consulta.setAnchoSala(medidas.getLargoSala());
			consulta.setLargoterreno(medidas.getLargoterreno());
			consulta.setPisos(medidas.getPisos());
			consulta.setTipoLadrillo(medidas.getTipoladrillo());
			consulta.setNombre(medidas.getNombre());
			consulta.setExcel("false");
			consulta=proveedorConsulta.selectProveedor(consulta);
			consulta.setArenaDesc("Bote de arena de 19 L");
			consulta.setGravaDesc("Bote de 19 L de grava de 3/4");
			consulta.setLadrilloBloackPesadoDesc("Block pesado 12x20x40 cm");
			consulta.setLadrilloBlockLigeroDesc("Block ligero 12x20x40 cm");
			consulta.setLadrilloRojoDesc("Ladrillo recocido de 12x10x24 cm");
			consulta.setSacoDesc("Saco de cemento 50K");
			consulta.setSacoMorteroDesc("Saco de mortero 50k");
			consulta.setVarillaDesc("Varilla de 3/8 de 12 m de largo");
			consulta.setAlambreDesc("Metros de alambre recocido");
			consulta.setVarillaArmexDesc("Tramo de 6 m");
			return consulta;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean saveConsulta(ConsultaModel consultaModel) {
		Consulta consulta = new Consulta();
		MaterialConsulta materialConsulta = new MaterialConsulta();
		try {
			consulta.setNombre(consultaModel.getNombre());
			materialConsulta.setAgua(consultaModel.getAgua());
			materialConsulta.setArena(consultaModel.getArena());
			materialConsulta.setGrava(consultaModel.getGrava());
			materialConsulta.setSaco(consultaModel.getSaco());
			materialConsulta.setSacoMortero(consultaModel.getSacoMortero());
			materialConsulta.setVarilla(consultaModel.getVarilla());
			materialConsulta.setLadrilloRojo(consultaModel.getLadrilloRojo());
			materialConsulta.setLadrilloBlockLigero(consultaModel.getLadrilloBlockLigero());
			materialConsulta.setLadrilloBloackPesado(consultaModel.getLadrilloBloackPesado());
			materialConsulta.setVarillaArmex(consultaModel.getVarillaArmex());
			materialConsulta.setAlambre(consultaModel.getAlambre());
			materialConsulta.setArenaDesc(consultaModel.getArenaDesc());
			materialConsulta.setGravaDesc(consultaModel.getGravaDesc());
			materialConsulta.setLadrilloBloackPesadoDesc(consultaModel.getLadrilloBloackPesadoDesc());
			materialConsulta.setLadrilloBlockLigeroDesc(consultaModel.getLadrilloBlockLigeroDesc());
			materialConsulta.setLadrilloRojoDesc(consultaModel.getLadrilloRojoDesc());
			materialConsulta.setVarillaArmexDes(consultaModel.getVarillaArmexDesc());
			materialConsulta.setSacoDesc(consultaModel.getSacoDesc());
			materialConsulta.setSacoMorteroDesc(consultaModel.getSacoMorteroDesc());
			materialConsulta.setVarillaDesc(consultaModel.getVarillaDesc());
			materialConsulta.setAlambreDesc(consultaModel.getAlambreDesc());
			materialConsulta.setArenaCosto(consultaModel.getArenaCosto());
			materialConsulta.setGravaCosto(consultaModel.getGravaCosto());
			materialConsulta.setSacoCosto(consultaModel.getSacoCosto());
			materialConsulta.setVarillaArmexCosto(consultaModel.getVarillaArmexCosto());
			materialConsulta.setSacoMorteroCosto(consultaModel.getSacoMorteroCosto());
			materialConsulta.setVarillaCosto(consultaModel.getVarillaCosto());
			materialConsulta.setLadrilloRojoCosto(consultaModel.getLadrilloRojoCosto());
			materialConsulta.setLadrilloBlockLigeroCosto(consultaModel.getLadrilloBlockLigeroCosto());
			materialConsulta.setLadrilloBloackPesadoCosto(consultaModel.getLadrilloBloackPesadoCosto());
			materialConsulta.setAlambreCosto(consultaModel.getAlambreCosto());
			consulta.setArquitecto(consultaModel.getArquitecto());
			consulta.setAnchoHabitacion1(consultaModel.getAnchoHabitacion1());
			consulta.setAnchoHabitacion2(consultaModel.getAnchoHabitacion2());
			consulta.setAnchobano(consultaModel.getAnchobano());
			consulta.setAnchococina(consultaModel.getAnchococina());
			consulta.setAnchoLavado(consultaModel.getAnchoLavado());
			consulta.setAnchoterreno(consultaModel.getAnchoterreno());
			consulta.setAnchoSala(consultaModel.getAnchoSala());
			consulta.setLargoSala(consultaModel.getLargoSala());
			consulta.setLargoHabitacion1(consultaModel.getLargoHabitacion1());
			consulta.setLargoHabitacion2(consultaModel.getLargoHabitacion2());
			consulta.setLargobano(consultaModel.getLargobano());
			consulta.setLargococina(consultaModel.getLargococina());
			consulta.setLargoLavado(consultaModel.getLargoLavado());
			consulta.setLargoterreno(consultaModel.getLargoterreno());
			consulta.setPisos(consultaModel.getPisos());
			if(consultaModel.getTipoladrillo()==1) {
				consulta.setTipoladrillo("Ladrillo rojo");
			}else {
				consulta.setTipoladrillo("Ladrillo de block");
			}
			consulta.setProveedorConsulta(consultaModel.getProveedorConsulta());
			consulta.setTotalConsulta(consultaModel.getTotal());
			SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			consulta.setFechaConsulta(fecha.format(new Date()));
			consulta.setMaterialConsulta(materialConsulta);
			consulta.setExcel(generarExcel(consulta));
			consultaDao.save(consulta);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public String generarExcel(Consulta consulta){
		String[] columnas= {"Nombre","Descripcion","Cantidad","Precio promedio por pieza","Sub total"};
		String[] nombreMaterial = {"ladrillo rojo","ladrillo block ligero","ladrillo block pesado","cemento","mortero","arena","grava","varilla","agua","alambre","varilla armex"};
		String[] nombreHerramienta= {"Pala","Pico","Marro o  mazo","Cizallas","Cincel","Maceta","Paleta", "Llana","Nivel", "Flexómetro","Carretilla"};
		String[] nombreMaquinaria= {"Apisonadores para compactación o bailarinas","Excavadora","Retroexcavadora","Mezcladora de Cemento"};

		try {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet =workbook.createSheet(consulta.getNombre());
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue(consulta.getNombre());
			row.createCell(2).setCellValue("Fecha de consulta:");
			row.createCell(3).setCellValue(consulta.getFechaConsulta());
			
			row = sheet.createRow(1);
			for(int i=0;i<columnas.length;i++){
				Cell cell=row.createCell(i);
				cell.setCellValue(columnas[i]);
			}
			
			row = sheet.createRow(2);
			row.createCell(0).setCellValue(nombreMaterial[0]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getLadrilloRojoDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getLadrilloRojo());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getLadrilloRojoCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getLadrilloRojo()*consulta.getMaterialConsulta().getLadrilloRojoCosto());
			
			row = sheet.createRow(3);
			row.createCell(0).setCellValue(nombreMaterial[1]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getLadrilloBlockLigeroDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getLadrilloBlockLigero());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getLadrilloBlockLigeroCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getLadrilloBlockLigero()*consulta.getMaterialConsulta().getLadrilloBlockLigeroCosto());
			
			row = sheet.createRow(4);
			row.createCell(0).setCellValue(nombreMaterial[2]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getLadrilloBloackPesadoDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getLadrilloBloackPesado());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getLadrilloBloackPesadoCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getLadrilloBloackPesado()*consulta.getMaterialConsulta().getLadrilloBloackPesadoCosto());
			
			row = sheet.createRow(5);
			row.createCell(0).setCellValue(nombreMaterial[3]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getSacoDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getSaco());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getSacoCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getSaco()*consulta.getMaterialConsulta().getSacoCosto());
			
			row = sheet.createRow(6);
			row.createCell(0).setCellValue(nombreMaterial[4]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getSacoMorteroDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getSacoMortero());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getSacoMorteroCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getSacoMortero()*consulta.getMaterialConsulta().getSacoMorteroCosto());
			
			row = sheet.createRow(7);
			row.createCell(0).setCellValue(nombreMaterial[5]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getArenaDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getArena());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getArenaCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getArena()*consulta.getMaterialConsulta().getArenaCosto());
			
			row = sheet.createRow(8);
			row.createCell(0).setCellValue(nombreMaterial[6]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getGravaDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getGrava());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getGravaCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getGrava()*consulta.getMaterialConsulta().getGravaCosto());
			
			row = sheet.createRow(9);
			row.createCell(0).setCellValue(nombreMaterial[7]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getVarillaDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getVarilla());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getVarillaCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getVarilla()*consulta.getMaterialConsulta().getVarillaCosto());
			
			row = sheet.createRow(10);
			row.createCell(0).setCellValue(nombreMaterial[8]);
			row.createCell(1).setCellValue("Botes de agua de 19L");
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getAgua());
			row.createCell(3).setCellValue(44.94);
			row.createCell(4).setCellValue(44.94);
			
			row = sheet.createRow(11);
			row.createCell(0).setCellValue(nombreMaterial[9]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getAlambreDesc());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getAlambre());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getAlambreCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getAlambre()*consulta.getMaterialConsulta().getAlambreCosto());
			
			row = sheet.createRow(12);
			row.createCell(0).setCellValue(nombreMaterial[10]);
			row.createCell(1).setCellValue(consulta.getMaterialConsulta().getVarillaArmexDes());
			row.createCell(2).setCellValue(consulta.getMaterialConsulta().getVarillaArmex());
			row.createCell(3).setCellValue(consulta.getMaterialConsulta().getVarillaArmexCosto());
			row.createCell(4).setCellValue(consulta.getMaterialConsulta().getVarillaArmexCosto()*consulta.getMaterialConsulta().getVarillaArmex());
			
			
			row=sheet.createRow(13);
			row.createCell(0).setCellValue("Total de la estimacion");
			row.createCell(4).setCellValue(consulta.getTotalConsulta());
			
			row=sheet.createRow(15);
			row.createCell(0).setCellValue("Datos de la construccion");
			row.createCell(4).setCellValue("Datos de las habitaciones");
			
			row=sheet.createRow(16);
			row.createCell(0).setCellValue("Ancho del terreno");
			row.createCell(1).setCellValue(consulta.getAnchoterreno());
			row.createCell(4).setCellValue("Nombre de la habitacion");
			row.createCell(5).setCellValue("Ancho de la habitacion");
			row.createCell(6).setCellValue("Largo de la habitacion");
			
			row=sheet.createRow(17);
			row.createCell(0).setCellValue("Largo del terreno");
			row.createCell(1).setCellValue(consulta.getLargoterreno());
			row.createCell(4).setCellValue("Baño");
			row.createCell(5).setCellValue(consulta.getAnchobano());
			row.createCell(6).setCellValue(consulta.getLargobano());

			row= sheet.createRow(18);
			row.createCell(0).setCellValue("Número de pisos");
			row.createCell(1).setCellValue(consulta.getPisos());
			row.createCell(4).setCellValue("Cuarto de lavado");
			row.createCell(5).setCellValue(consulta.getAnchoLavado());
			row.createCell(6).setCellValue(consulta.getLargoLavado());
			
			row=sheet.createRow(19);
			row.createCell(0).setCellValue("Tipo de ladrillo");
			if(consulta.getTipoladrillo()=="1") {
				row.createCell(1).setCellValue("Ladrillo rojo");
			}else {
				row.createCell(1).setCellValue("Ladrillo de block");
			}
			row.createCell(4).setCellValue("Cocina");
			row.createCell(5).setCellValue(consulta.getAnchococina());
			row.createCell(6).setCellValue(consulta.getLargococina());
			
			row= sheet.createRow(20);
			row.createCell(4).setCellValue("Sala comedor");
			row.createCell(5).setCellValue(consulta.getAnchoSala());
			row.createCell(6).setCellValue(consulta.getLargoSala());
		
			
			row= sheet.createRow(21);
			row.createCell(4).setCellValue("Habitacion 1");
			row.createCell(5).setCellValue(consulta.getAnchoHabitacion1());
			row.createCell(6).setCellValue(consulta.getLargoHabitacion1());
			
			if(consulta.getAnchoHabitacion2()!=0.0) {
				row= sheet.createRow(22);
				row.createCell(4).setCellValue("Habitacion 2");
				row.createCell(5).setCellValue(consulta.getAnchoHabitacion2());
				row.createCell(6).setCellValue(consulta.getLargoHabitacion2());
			}
			
			row=sheet.createRow(24);
			row.createCell(0).setCellValue("Datos de los proveedor");
			
			row=sheet.createRow(25);
			row.createCell(0).setCellValue("Nombre del proveedor");
			row.createCell(1).setCellValue("Telefono");
			row.createCell(2).setCellValue("Correo electronico");
			row.createCell(3).setCellValue("Direccion del proveedor");
			
			List<ConsultaProveedor> consultaProveedores=consulta.getProveedorConsulta();
			int i=26;
			for(ConsultaProveedor consultaProveedor:consultaProveedores) {
				row=sheet.createRow(i);
				row.createCell(0).setCellValue(consultaProveedor.getNombreProveedor());
				row.createCell(1).setCellValue(consultaProveedor.getTelefonoProveedor());
				row.createCell(2).setCellValue(consultaProveedor.getCorreoProveedor());
				row.createCell(3).setCellValue(consultaProveedor.getDireccionProveedor());
				i++;
			}
			
			i=i+2;
			row = sheet.createRow(i);
			row.createCell(0).setCellValue("Herramientas y maquinaria recomendadas");
			i=i+2;
			row = sheet.createRow(i);
			row.createCell(0).setCellValue("Herramientas");
			i=i+1;
			row = sheet.createRow(i);
			for(int j=0;j<nombreHerramienta.length;j++) {
				row.createCell(j).setCellValue(nombreHerramienta[j]);
			}
			i=i+2;
			row = sheet.createRow(i);
			row.createCell(0).setCellValue("Maquinaria");
			i=i+1;
			row = sheet.createRow(i);
			for(int j=0;j<nombreMaquinaria.length;j++) {
				row.createCell(j).setCellValue(nombreMaquinaria[j]);
			}
			
			String url = System.getProperty("user.dir")+"\\Archivos\\Consultas\\"+consulta.getArquitecto().getId()+"\\"+consulta.getNombre()+".xls";
			String urlCarpeta=System.getProperty("user.dir")+"\\Archivos\\Consultas\\"+consulta.getArquitecto().getId();
			File carpeta = new File(urlCarpeta);
			if(!carpeta.exists()){
				carpeta.mkdirs();
			}
			FileOutputStream out = new FileOutputStream(url);
			workbook.write(out);
			workbook.close();
			out.close();
			return url;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean eliminarConsulta(ConsultaAux consulta) {
		try {
			Optional<Arquitecto> arquitecto=arquitectoDao.findById(consulta.getArquitecto());
			Consulta consulta1 = consultaDao.findByNombreAndArquitecto(consulta.getNombre(), arquitecto.get());
			File consultasExcel=new File(consulta1.getExcel());
			consultasExcel.delete();
			consultaDao.delete(consulta1);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Consulta> getAllConsultas(int id) {
		try {
			Optional<Arquitecto> arquitecto=arquitectoDao.findById(id);
			List<Consulta> consultas = consultaDao.findByArquitecto(arquitecto.get());
			return consultas;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean enviarCorreo(ConsultaAux consulta) {
		try {
			Optional<Arquitecto>arquitecto= arquitectoDao.findById(consulta.getArquitecto());
			Usuario usuario=usuarioDao.findByArquitecto(arquitecto.get());
			emailDao.enviarConsulta(usuario, consulta.getNombre());
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Usuario perfilUsuario(int id) {
		try {
			return usuarioDao.findByArquitecto(arquitectoDao.findById(id).get());
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean bajaPerfil(Usuario arquitecto) {
		try {
			List<Consulta> consultas=consultaDao.findByArquitecto(arquitecto.getArquitecto());
			for(Consulta consulta:consultas) {
				File consultasExcel=new File(consulta.getExcel());
				consultasExcel.delete();
				consultaDao.delete(consulta);
			}
			usuarioDao.delete(arquitecto);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}


}
