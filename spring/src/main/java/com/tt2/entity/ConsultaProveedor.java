package com.tt2.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "proveedor_consulta")
@SequenceGenerator(name = "S_proveedor_consulta", sequenceName = "S_proveedor_consulta")

public class ConsultaProveedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "S_proveedor_consulta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_proveedor_consulta")
	private int id;
	
	@Column(name="nombre_proveedor",nullable= false, columnDefinition="VARCHAR(250)")
	private String nombreProveedor;
	
	@Column(name="telefono_proveedor",nullable= false, columnDefinition="VARCHAR(10)")
	private String telefonoProveedor;
	
	@Column(name="correo_proveedor",nullable= false, columnDefinition="VARCHAR(300)")
	private String correoProveedor;
	
	@Column(name="direccion_proveedor",nullable= false, columnDefinition="VARCHAR(250)")
	private String direccionProveedor;

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}

	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public String getCorreoProveedor() {
		return correoProveedor;
	}

	public void setCorreoProveedor(String correoProveedor) {
		this.correoProveedor = correoProveedor;
	}

	public String getDireccionProveedor() {
		return direccionProveedor;
	}

	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}
	
	
	
}
