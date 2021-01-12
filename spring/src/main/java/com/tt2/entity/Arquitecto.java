package com.tt2.entity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/*
 * Existen 4 tipos de relacion de clases Asociativa,asignacion, composicion y herencia
 * En base de datos solo se implementan 3 y estas pueden ser bidirecionales o unidireciona
 * asociativa los objetos no dependen de la otra clase
 * asignacion existe una dependecia de un solo lado
 * composicion la dependecia es por parte de las 2 clases
 * */
@Entity
@Table(name = "arquitecto")
@SequenceGenerator(name = "S_arquitecto", sequenceName = "S_arquitecto")
public class Arquitecto implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_arquitecto")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_arquitecto")
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombre",nullable = false, columnDefinition = "VARCHAR(20)")
	private String nombre;
	
	@Column(name = "apellido", nullable = false, columnDefinition = "VARCHAR(25)")
	private String apellido;
	
	@Column(name = "direccion", columnDefinition = "VARCHAR(100)")
	private String direccion;
	
	@Column(name = "telefono", columnDefinition = "VARCHAR(10)")
	private String telefono;
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}	
}
