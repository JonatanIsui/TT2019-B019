package com.tt2.service;

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
import com.tt2.entity.Diccionario;
import com.tt2.entity.Estimacion;
import com.tt2.entity.Proveedor;
import com.tt2.entity.Usuario;
import com.tt2.model.ConsultaModel;
import com.tt2.model.MedidasModel;
import com.tt2.service.interfaz.ArquitectoBeanInterfaz;

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
	
	@Override
	public Usuario registroArquitecto(Usuario usuario) {
		Usuario aux = null;
		if(usuarioDao.findByCorreo(usuario.getCorreo()) == null) {
			aux = usuarioDao.save(usuario);
		}
		return aux;
	}

	@Override
	public Estimacion ingresarMedidas(Medidas medidas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estimacion> verConsultasAnteriores() {
		// TODO Auto-generated method stub
		return null;
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
			consulta.setExcel("false");
			consulta.setArenaCosto(materialDao.promedioCostoArena());
			consulta.setGravaCosto(materialDao.promedioCostoGrava());
			consulta.setSacoCosto(materialDao.promedioCostoCemento());
			consulta.setSacoMorteroCosto(materialDao.promedioCostoMortero());
			consulta.setVarillaCosto(materialDao.promedioCostoVarilla());
			consulta.setLadrilloRojoCosto(materialDao.promedioCostoladrilloRojo());
			consulta.setLadrilloBlockLigeroCosto(materialDao.promedioCostoladrilloLigero());
			consulta.setLadrilloBloackPesadoCosto(materialDao.promedioCostoladrilloPesado());
			return consulta;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
