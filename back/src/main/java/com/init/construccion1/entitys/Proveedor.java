package com.init.construccion1.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity(name="proveedor")
public class Proveedor implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idarquitecto")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	@Column(name="empresa", nullable=false, columnDefinition = "VARCHAR(50)")
	private String empresa;
	@Column(name="nombreEncargado", nullable=false, columnDefinition = "VARCHAR(30)")
	private String nombreEncargado;
	@Column(name="apellidoEncargado", nullable=false, columnDefinition = "VARCHAR(30)")
	private String apellidoEncargado;
	@Column(name="telefono", nullable=false, columnDefinition = "VARCHAR(20)")
	private String telefono;
	@Column(name="direccion", nullable=false, columnDefinition = "VARCHAR(150)")
	private String direccion;
	public String getEmpresa() {
		return empresa;
	}
	public int getId() {
		return id;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
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
}
