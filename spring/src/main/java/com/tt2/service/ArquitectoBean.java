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
import com.tt2.dao.ProveedorDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Arquitecto;
import com.tt2.entity.Consulta;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Proveedor;
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
			consultaBean.castillos(medidas.getPisos());
			consultaBean.cadenas(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos());
			consultaBean.paredesPerimetro(medidas.getAnchoterreno(), medidas.getLargoterreno(),medidas.getPisos(),medidas.getTipoladrillo());
			consultaBean.cuartos(medidas.getPisos(),medidas.getAnchoHabitacion1(),medidas.getLargoHabitacion1(),medidas.getAnchoHabitacion2(),medidas.getLargoHabitacion2(),
								medidas.getAnchobano(),medidas.getLargobano(),medidas.getAnchococina(),medidas.getLargococina(),medidas.getAncholavado(),medidas.getLargolavado(),
								medidas.getTipoladrillo()
					);
			consultaBean.escalera();
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
			consulta.setLargoterreno(medidas.getLargoterreno());
			consulta.setPisos(medidas.getPisos());
			consulta.setTipoLadrillo(medidas.getTipoladrillo());
			consulta.setNombre(medidas.getNombre());
			consulta.setExcel("false");
			consulta=proveedorConsulta.selectProveedor(consulta);
			Optional<Proveedor> proveedor=proveedorDao.findById(consulta.getIdProveedor());
			consulta.setArenaDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"arena").getDescripcion());
			consulta.setGravaDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"grava").getDescripcion());
			consulta.setLadrilloBloackPesadoDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"ladrillo block pesado").getDescripcion());
			consulta.setLadrilloBlockLigeroDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"ladrillo block ligero").getDescripcion());
			consulta.setLadrilloRojoDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"ladrillo rojo").getDescripcion());
			consulta.setSacoDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"cemento").getDescripcion());
			consulta.setSacoMorteroDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"mortero").getDescripcion());
			consulta.setVarillaDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"varilla").getDescripcion());
			consulta.setAlambreDesc(materialDao.findByProveedorAndNombre(proveedor.get(),"alambre").getDescripcion());
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
			consulta.setAnchoLavado(consultaModel.getAnchoLavado());
			consulta.setAnchoterreno(consultaModel.getAnchoterreno());
			consulta.setLargoHabitacion1(consultaModel.getLargoHabitacion1());
			consulta.setLargoHabitacion2(consultaModel.getLargoHabitacion2());
			consulta.setLargobano(consultaModel.getLargobano());
			consulta.setLargococina(consultaModel.getLargococina());
			consulta.setLargoLavado(consultaModel.getLargoLavado());
			consulta.setLargoterreno(consultaModel.getLargoterreno());
			consulta.setAlambre(consultaModel.getAlambre());
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
			consulta.setLadrilloRojoCosto(consultaModel.getLadrilloRojoCosto());
			consulta.setLadrilloBlockLigeroCosto(consultaModel.getLadrilloBlockLigeroCosto());
			consulta.setLadrilloBloackPesadoCosto(consultaModel.getLadrilloBloackPesadoCosto());
			consulta.setAlambreCosto(consultaModel.getAlambreCosto());
			consulta.setNombreProveedor(consultaModel.getNombreProveedor());
			consulta.setTelefonoProveedor(consultaModel.getTelefonoProveedor());
			consulta.setCorreoProveedor(consultaModel.getCorreoProveedor());
			consulta.setDireccionProveedor(consultaModel.getDireccionProveedor());
			consulta.setTotalConsulta(consultaModel.getTotal());
			consulta.setArenaDesc(consultaModel.getArenaDesc());
			consulta.setGravaDesc(consultaModel.getGravaDesc());
			consulta.setLadrilloBloackPesadoDesc(consultaModel.getLadrilloBloackPesadoDesc());
			consulta.setLadrilloBlockLigeroDesc(consultaModel.getLadrilloBlockLigeroDesc());
			consulta.setLadrilloRojoDesc(consultaModel.getLadrilloRojoDesc());
			consulta.setSacoDesc(consultaModel.getSacoDesc());
			consulta.setSacoMorteroDesc(consultaModel.getSacoMorteroDesc());
			consulta.setVarillaDesc(consultaModel.getVarillaDesc());
			consulta.setAlambreDesc(consultaModel.getAlambreDesc());
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
		String[] nombreMaterial = {
				"ladrillo rojo",
				"ladrillo block ligero",
				"ladrillo block pesado",
				"cemento",
				"mortero",
				"arena",
				"grava",
				"varilla",
				"agua",
				"alambre"
				};
		try {
			Workbook workbook = new HSSFWorkbook();
			Sheet sheet =workbook.createSheet(consulta.getNombre());
			Row row = sheet.createRow(0);
			row.createCell(0).setCellValue(consulta.getNombre());
			
			row = sheet.createRow(1);
			for(int i=0;i<columnas.length;i++){
				Cell cell=row.createCell(i);
				cell.setCellValue(columnas[i]);
			}
			
			row = sheet.createRow(2);
			row.createCell(0).setCellValue(nombreMaterial[0]);
			row.createCell(1).setCellValue(consulta.getLadrilloRojoDesc());
			row.createCell(2).setCellValue(consulta.getLadrilloRojo());
			row.createCell(3).setCellValue(consulta.getLadrilloRojoCosto());
			row.createCell(4).setCellValue(consulta.getLadrilloRojo()*consulta.getLadrilloRojoCosto());
			
			row = sheet.createRow(3);
			row.createCell(0).setCellValue(nombreMaterial[1]);
			row.createCell(1).setCellValue(consulta.getLadrilloBlockLigeroDesc());
			row.createCell(2).setCellValue(consulta.getLadrilloBlockLigero());
			row.createCell(3).setCellValue(consulta.getLadrilloBlockLigeroCosto());
			row.createCell(4).setCellValue(consulta.getLadrilloBlockLigero()*consulta.getLadrilloBlockLigeroCosto());
			
			row = sheet.createRow(4);
			row.createCell(0).setCellValue(nombreMaterial[2]);
			row.createCell(1).setCellValue(consulta.getLadrilloBloackPesadoDesc());
			row.createCell(2).setCellValue(consulta.getLadrilloBloackPesado());
			row.createCell(3).setCellValue(consulta.getLadrilloBloackPesadoCosto());
			row.createCell(4).setCellValue(consulta.getLadrilloBloackPesado()*consulta.getLadrilloBloackPesadoCosto());
			
			row = sheet.createRow(5);
			row.createCell(0).setCellValue(nombreMaterial[3]);
			row.createCell(1).setCellValue(consulta.getSacoDesc());
			row.createCell(2).setCellValue(consulta.getSaco());
			row.createCell(3).setCellValue(consulta.getSacoCosto());
			row.createCell(4).setCellValue(consulta.getSaco()*consulta.getSacoCosto());
			
			row = sheet.createRow(6);
			row.createCell(0).setCellValue(nombreMaterial[4]);
			row.createCell(1).setCellValue(consulta.getSacoMorteroDesc());
			row.createCell(2).setCellValue(consulta.getSacoMortero());
			row.createCell(3).setCellValue(consulta.getSacoMorteroCosto());
			row.createCell(4).setCellValue(consulta.getSacoMortero()*consulta.getSacoMorteroCosto());
			
			row = sheet.createRow(7);
			row.createCell(0).setCellValue(nombreMaterial[5]);
			row.createCell(1).setCellValue(consulta.getArenaDesc());
			row.createCell(2).setCellValue(consulta.getArena());
			row.createCell(3).setCellValue(consulta.getArenaCosto());
			row.createCell(4).setCellValue(consulta.getArena()*consulta.getArenaCosto());
			
			row = sheet.createRow(8);
			row.createCell(0).setCellValue(nombreMaterial[6]);
			row.createCell(1).setCellValue(consulta.getGravaDesc());
			row.createCell(2).setCellValue(consulta.getGrava());
			row.createCell(3).setCellValue(consulta.getGravaCosto());
			row.createCell(4).setCellValue(consulta.getGrava()*consulta.getGravaCosto());
			
			row = sheet.createRow(9);
			row.createCell(0).setCellValue(nombreMaterial[7]);
			row.createCell(1).setCellValue(consulta.getVarillaDesc());
			row.createCell(2).setCellValue(consulta.getVarilla());
			row.createCell(3).setCellValue(consulta.getVarillaCosto());
			row.createCell(4).setCellValue(consulta.getVarilla()*consulta.getVarillaCosto());
			
			row = sheet.createRow(10);
			row.createCell(0).setCellValue(nombreMaterial[8]);
			row.createCell(1).setCellValue("Botes de agua de 19L");
			row.createCell(2).setCellValue(consulta.getAgua());
			row.createCell(3).setCellValue(44.94);
			row.createCell(4).setCellValue(44.94);
			
			row = sheet.createRow(11);
			row.createCell(0).setCellValue(nombreMaterial[9]);
			row.createCell(1).setCellValue(consulta.getAlambreDesc());
			row.createCell(2).setCellValue(consulta.getAlambre());
			row.createCell(3).setCellValue(consulta.getAlambreCosto());
			row.createCell(4).setCellValue(consulta.getAlambre()*consulta.getAlambreCosto());
			
			
			
			row=sheet.createRow(12);
			row.createCell(0).setCellValue("Total de la estimacion");
			row.createCell(4).setCellValue(consulta.getTotalConsulta());
			
			row=sheet.createRow(14);
			row.createCell(0).setCellValue("Datos de la construccion");
			row.createCell(4).setCellValue("Datos de las habitaciones");
			
			row=sheet.createRow(15);
			row.createCell(0).setCellValue("Ancho del terreno");
			row.createCell(1).setCellValue(consulta.getAnchoterreno());
			row.createCell(4).setCellValue("Nombre de la habitacion");
			row.createCell(5).setCellValue("Ancho de la habitacion");
			row.createCell(6).setCellValue("Largo de la habitacion");
			
			row=sheet.createRow(16);
			row.createCell(0).setCellValue("Largo del terreno");
			row.createCell(1).setCellValue(consulta.getLargoterreno());
			row.createCell(4).setCellValue("Baño");
			row.createCell(5).setCellValue(consulta.getAnchobano());
			row.createCell(6).setCellValue(consulta.getLargobano());

			row= sheet.createRow(17);
			row.createCell(0).setCellValue("Número de pisos");
			row.createCell(1).setCellValue(consulta.getPisos());
			row.createCell(4).setCellValue("Cuarto de lavado");
			row.createCell(5).setCellValue(consulta.getAnchoLavado());
			row.createCell(6).setCellValue(consulta.getLargoLavado());
			
			row=sheet.createRow(18);
			row.createCell(0).setCellValue("Tipo de ladrillo");
			if(consulta.getTipoladrillo()=="1") {
				row.createCell(1).setCellValue("Ladrillo rojo");
			}else {
				row.createCell(1).setCellValue("Ladrillo de block");
			}
			row.createCell(4).setCellValue("Cocina");
			row.createCell(5).setCellValue(consulta.getAnchococina());
			row.createCell(6).setCellValue(consulta.getLargococina());
			
			row= sheet.createRow(19);
			row.createCell(4).setCellValue("Sala comedor");
			row.createCell(5).setCellValue("Ancho en metros");
			row.createCell(6).setCellValue("Largo en metros");
		
			
			row= sheet.createRow(20);
			row.createCell(4).setCellValue("Habitacion 1");
			row.createCell(5).setCellValue(consulta.getAnchoHabitacion1());
			row.createCell(6).setCellValue(consulta.getLargoHabitacion1());
			
			if(consulta.getAnchoHabitacion2()!=0.0) {
				row= sheet.createRow(21);
				row.createCell(4).setCellValue("Habitacion 2");
				row.createCell(5).setCellValue(consulta.getAnchoHabitacion2());
				row.createCell(6).setCellValue(consulta.getLargoHabitacion2());
			}
			
			row=sheet.createRow(23);
			row.createCell(0).setCellValue("Datos del proveedor");
			
			row=sheet.createRow(24);
			row.createCell(0).setCellValue("Nombre del proveedor");
			row.createCell(1).setCellValue(consulta.getNombreProveedor());
			
			row=sheet.createRow(25);
			row.createCell(0).setCellValue("Telefono");
			row.createCell(1).setCellValue(consulta.getTelefonoProveedor());
			
			row=sheet.createRow(26);
			row.createCell(0).setCellValue("Correo electronico");
			row.createCell(1).setCellValue(consulta.getCorreoProveedor());
			
			row=sheet.createRow(27);
			row.createCell(0).setCellValue("Direccion del proveedor");
			row.createCell(1).setCellValue(consulta.getDireccionProveedor());
			
			String url = System.getProperty("user.dir")+"\\Archivos\\"+consulta.getNombre()+".xls";
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
			consultaDao.delete(consultaDao.findByNombreAndArquitecto(consulta.getNombre(), arquitecto.get()));
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
