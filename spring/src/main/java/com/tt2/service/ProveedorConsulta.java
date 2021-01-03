package com.tt2.service;
import java.util.List;
import java.lang.Math;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.MaterialDao;
import com.tt2.dao.ProveedorDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Proveedor;
import com.tt2.model.ConsultaModel;

@Service("proveedorConsulta")
public class ProveedorConsulta {
	@Autowired
	@Qualifier("proveedorDao")
	private ProveedorDao proveedorDao;
	
	@Autowired
	@Qualifier("materialDao")
	private MaterialDao materialDao;
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	private String nombreMaterial[] = {
			"ladrillo rojo",
			"ladrillo block ligero",
			"ladrillo block pesado",
			"cemento",
			"mortero",
			"arena",
			"grava",
			"varilla",
			"alambre"};
	
	public ConsultaModel selectProveedor(ConsultaModel consulta) {
		int aux= 0;
		boolean notNull=true;
		double promedioaux=0;
		int provedorSugerido=0;
		double totalConstrucion = 0;
		Proveedor proveedorSugerido=new Proveedor();
		double promedio = consulta.getArena()*consulta.getArenaCosto() 
				+consulta.getGrava()*consulta.getGravaCosto()
				+consulta.getSaco()*consulta.getSacoCosto()
				+consulta.getSacoMortero()*consulta.getSacoMorteroCosto()
				+consulta.getVarilla()*consulta.getVarillaCosto()
				+consulta.getLadrilloRojo()*consulta.getLadrilloRojoCosto()
				+consulta.getLadrilloBlockLigero()*consulta.getLadrilloBlockLigeroCosto()
				+consulta.getLadrilloBloackPesado()*consulta.getLadrilloBloackPesadoCosto();
		List<Proveedor> proveedores = proveedorDao.findAll();
		for(Proveedor proveedor:proveedores) {
			notNull= true;
			for(int i = 0;i<nombreMaterial.length;i++) {
				if(materialDao.findByProveedorAndNombre(proveedor, nombreMaterial[i])==null) {
					notNull= false;
				}
			}
			if(notNull) {
				for(int j = 0;j<nombreMaterial.length;j++) {
					totalConstrucion=materialDao.findByProveedorAndNombre(proveedor, "grava").getCosto()*consulta.getGravaCosto()
							+materialDao.findByProveedorAndNombre(proveedor, "arena").getCosto()*consulta.getArena()
							+materialDao.findByProveedorAndNombre(proveedor, "cemento").getCosto()*consulta.getSaco()
							+materialDao.findByProveedorAndNombre(proveedor, "mortero").getCosto()*consulta.getSacoMortero()
							+materialDao.findByProveedorAndNombre(proveedor, "varilla").getCosto()*consulta.getVarilla()
							+materialDao.findByProveedorAndNombre(proveedor, "ladrillo rojo").getCosto()*consulta.getLadrilloRojo()
							+materialDao.findByProveedorAndNombre(proveedor, "ladrillo block ligero").getCosto()*consulta.getLadrilloBlockLigero()
							+materialDao.findByProveedorAndNombre(proveedor, "ladrillo block pesado").getCosto()*consulta.getLadrilloBloackPesado();

				}
				if(Math.pow((totalConstrucion-promedio),2)<promedioaux){
					provedorSugerido=aux;
					promedioaux=totalConstrucion-promedio;
				}
				aux++;
			}
		}
		proveedorSugerido=proveedores.get(provedorSugerido);
		consulta.setNombreProveedor(proveedorSugerido.getNombreEmpresa());
		consulta.setTelefonoProveedor(proveedorSugerido.getTelefono());
		consulta.setCorreoProveedor(usuarioDao.findByProveedor(proveedorSugerido).getCorreo());
		consulta.setDireccionProveedor(proveedorSugerido.getDireccion());
		consulta.setNombreProveedor(proveedorSugerido.getNombreEmpresa());
		consulta.setTelefonoProveedor(proveedorSugerido.getTelefono());
		consulta.setCorreoProveedor(usuarioDao.findByProveedor(proveedorSugerido).getCorreo());
		consulta.setDireccionProveedor(proveedorSugerido.getDireccion());
		consulta.setTotal(promedio);
		return consulta;
	}
}
