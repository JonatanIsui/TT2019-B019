package com.tt2.service;
import java.util.List;
import java.util.Optional;
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
		boolean notNull=true;
		double promedioaux=1000000;
		double totalConstrucionaux=1000000;
		int provedorSugerido=0;
		double totalConstrucion = 0;
		try {
			consulta.setArenaCosto(materialDao.promedioCostoArena());
			consulta.setGravaCosto(materialDao.promedioCostoGrava());
			consulta.setSacoCosto(materialDao.promedioCostoCemento());
			consulta.setSacoMorteroCosto(materialDao.promedioCostoMortero());
			consulta.setVarillaCosto(materialDao.promedioCostoVarilla());
			consulta.setLadrilloRojoCosto(materialDao.promedioCostoladrilloRojo());
			consulta.setLadrilloBlockLigeroCosto(materialDao.promedioCostoladrilloLigero());
			consulta.setLadrilloBloackPesadoCosto(materialDao.promedioCostoladrilloPesado());
			consulta.setAlambreCosto(materialDao.promedioCostoAlambre());
			double promedio = consulta.getArena()*consulta.getArenaCosto() 
					+consulta.getGrava()*consulta.getGravaCosto()
					+consulta.getSaco()*consulta.getSacoCosto()
					+consulta.getSacoMortero()*consulta.getSacoMorteroCosto()
					+consulta.getVarilla()*consulta.getVarillaCosto()
					+consulta.getLadrilloRojo()*consulta.getLadrilloRojoCosto()
					+consulta.getLadrilloBlockLigero()*consulta.getLadrilloBlockLigeroCosto()
					+consulta.getLadrilloBloackPesado()*consulta.getLadrilloBloackPesadoCosto()
					+consulta.getAlambre()*consulta.getAlambreCosto()
					+44.94;
			promedioaux=promedio;
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
								+materialDao.findByProveedorAndNombre(proveedor, "ladrillo block pesado").getCosto()*consulta.getLadrilloBloackPesado()
								+materialDao.findByProveedorAndNombre(proveedor, "alambre").getCosto()*consulta.getAlambre()
								+44.94;
					}
					if(totalConstrucion-promedio==0){
						provedorSugerido=proveedor.getId();
					}else {
						if(Math.abs(totalConstrucion-promedio)<=totalConstrucionaux) {
							if(totalConstrucion<promedioaux) {
								provedorSugerido=proveedor.getId();
								promedioaux=totalConstrucion;
								totalConstrucionaux=Math.abs(totalConstrucion-promedio);
							}
						}
					}
				}
			}
			Optional<Proveedor> proveedorSugerido=proveedorDao.findById(provedorSugerido);
			consulta.setNombreProveedor(proveedorSugerido.get().getNombreEmpresa());
			consulta.setTelefonoProveedor(proveedorSugerido.get().getTelefono());
			consulta.setCorreoProveedor(usuarioDao.findByProveedor(proveedorSugerido.get()).getCorreo());
			consulta.setDireccionProveedor(proveedorSugerido.get().getDireccion());
			consulta.setNombreProveedor(proveedorSugerido.get().getNombreEmpresa());
			consulta.setTelefonoProveedor(proveedorSugerido.get().getTelefono());
			consulta.setCorreoProveedor(usuarioDao.findByProveedor(proveedorSugerido.get()).getCorreo());
			consulta.setDireccionProveedor(proveedorSugerido.get().getDireccion());
			consulta.setTotal(promedio);
			consulta.setIdProveedor(proveedorSugerido.get().getId());
			return consulta;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
