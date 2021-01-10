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
@Table(name = "diccionario")
@SequenceGenerator(name = "S_diccionario", sequenceName = "S_diccionario")
public class Diccionario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_diccionario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_diccionario")
	private int id;
	
	@Column(name = "Nombre", unique=true  ,nullable = false, columnDefinition = "VARCHAR(60)")
	private String nombre;
	
	@Column(name = "definicion", nullable = false, columnDefinition = "VARCHAR(1000)")
	private String definicion;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDefinicion() {
		return definicion;
	}

	public void setDefinicion(String definicion) {
		this.definicion = definicion;
	}

	public int getId() {
		return id;
	}
}
