package com.tt2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.tt2.dao.AdministradorDao;
import com.tt2.dao.DiccionarioDao;
import com.tt2.dao.ProveedorDao;
import com.tt2.dao.SolicitudProveedorDao;
import com.tt2.dao.UsuarioDao;
import com.tt2.entity.Diccionario;
import com.tt2.entity.Proveedor;
import com.tt2.entity.SolicitudProveedor;
import com.tt2.entity.Usuario;
import com.tt2.service.interfaz.AdministradorBeanInterfaz;

@Service ("administradorService")
public class AdministradorBean extends UsuarioBean implements AdministradorBeanInterfaz{
	@Autowired
	@Qualifier("administradorDao")
	private AdministradorDao administradorDao;
	
	@Autowired
	@Qualifier("solicitudProveedorDao")
	private SolicitudProveedorDao solicitudProveedorDao;
	
	@Autowired
	@Qualifier("proveedorDao")
	private ProveedorDao proveedorDao;
	
	@Autowired
	@Qualifier("usuarioDao")
	private UsuarioDao usuarioDao;
	
	@Autowired
	@Qualifier("diccionarioDao")
	private DiccionarioDao diccionarioDao;
	
	@Autowired
	@Qualifier("emailDao")
	private Email email;
	
	@Override
	public List<SolicitudProveedor> getSolicitudes() {
		return solicitudProveedorDao.getPedientes();
	}

	@Override
	public boolean altaProveedor(int id) {
		Usuario usuario = new Usuario();
		Proveedor proveedor = new Proveedor();
		Optional<SolicitudProveedor> solicitud = solicitudProveedorDao.findById(id);
		SolicitudProveedor solicitudProveedor = solicitud.get();
		boolean aux = false;
		System.out.println(usuarioDao.findByCorreo(solicitudProveedor.getCorreo()));
		System.out.println(usuarioDao.findByCorreo(proveedorDao.findByNombreEmpresa(solicitudProveedor.getNombreEmpresa())));
		if(usuarioDao.findByCorreo(solicitudProveedor.getCorreo()) == null && proveedorDao.findByNombreEmpresa(solicitudProveedor.getNombreEmpresa()) == null) {
			usuario.setCorreo(solicitudProveedor.getCorreo());
			usuario.setPassword(solicitudProveedor.getPassword());
			proveedor.setApellidoEncargado(solicitudProveedor.getApellidoEncargado());
			proveedor.setDireccion(solicitudProveedor.getDireccion());
			proveedor.setNombreEmpresa(solicitudProveedor.getNombreEmpresa());
			proveedor.setNombreEncargado(solicitudProveedor.getNombreEncargado());
			proveedor.setTelefono(solicitudProveedor.getTelefono());
			usuario.setProveedor(proveedor);
			usuarioDao.save(usuario);
			email.aceptarProveedor(usuario);
			solicitudProveedorDao.delete(solicitudProveedor);
			aux = true;
		}
		return aux;
	}

	@Override
	public boolean rechazarSolicitudProveedor(Integer id) {
		try {
			Optional<SolicitudProveedor> pro = solicitudProveedorDao.findById(id);
			SolicitudProveedor aux = pro.get();
			aux.setEstadoSolicitud(1);
			solicitudProveedorDao.save(aux);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean eliminarUsuario(Integer id) {
		try {
			administradorDao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modificarProveedor(Proveedor proveedor) {
		try {
			proveedorDao.save(proveedor);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Usuario> getProveedores() {
		return administradorDao.getAllProveedores();
	}

	@Override
	public List<Diccionario> getDiccionario() {
		return diccionarioDao.findAll();
	}

	@Override
	public boolean modificarDefinicion(Diccionario definicion) {
		try {
			diccionarioDao.save(definicion);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean agregarDefinicion(Diccionario definicion) {
		try {
			diccionarioDao.save(definicion);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminarDefinicion(Integer id) {
		try {
			diccionarioDao.deleteById(id);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Usuario> getAllArquitectos() {
		return administradorDao.getAllArquitectos();
	}

}
