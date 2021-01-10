package com.tt2.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "solicitud_proveedor")
@SequenceGenerator(name = "S_solicitud_proveedor", sequenceName = "S_solicitud_proveedor")
public class SolicitudProveedor implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_proveedor")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator ="S_solicitud_proveedor")
	private int id;
	
	@Column(name = "nombre_empresa", nullable = false, unique = true, columnDefinition="VARCHAR(30)" )
	private String nombreEmpresa;
	
	@Column(name = "nombre_encargado", nullable = false, columnDefinition = "VARCHAR(20)")
	private String nombreEncargado;
	
	@Column(name = "apellido_encargado", nullable = false, columnDefinition = "VARCHAR(30)")
	private String apellidoEncargado;
	
	@Column(name = "telefono", nullable = false, columnDefinition = "VARCHAR(10)")
	private String telefono;
	
	@Column(name = "direccion", nullable = false, columnDefinition = "VARCHAR(100)")
	private String direccion;
	
	@Column(name = "estado_solicitud", nullable = false, columnDefinition = "INT(1)")
	private int estadoSolicitud;

	@Column(name = "correo", nullable = false, unique = true, columnDefinition = "VARCHAR(76)")
	private String correo;
	
	private String identificacion;
	
	@Column(name = "password", nullable = false,columnDefinition = "VARCHAR(16)")
	private String password;
	
	@Column(name = "fecha_solicitud", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	private Date fechaLogin;
	
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public String getNombreEncargado() {
		return nombreEncargado;
	}

	public void setNombreEncargado(String nombreEncargado) {
		this.nombreEncargado = nombreEncargado;
	}

	public String getApellidoEncargado() {
		return apellidoEncargado;
	}

	public void setApellidoEncargado(String apellidoEncargado) {
		this.apellidoEncargado = apellidoEncargado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(int estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaLogin() {
		return fechaLogin;
	}

	public void setFechaLogin(Date fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public int getId() {
		return id;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
	
	
}
