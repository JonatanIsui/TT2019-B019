package com.tt2.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@Table(name = "proveedor")
@SequenceGenerator(name = "S_proveedor", sequenceName = "S_proveedor")
public class Proveedor implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_proveedor")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="S_proveedor")
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

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

	public int getId() {
		return id;
	}
	
}
