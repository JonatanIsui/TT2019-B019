package com.init.construccion1.entitys;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="administrador")
public class Administrador implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="idadministrador")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	@Column(name="nombre", nullable=false, columnDefinition = "VARCHAR(30)")
	private String nombre;
	public String getNombre() {
		return nombre;
	}
	public int getId() {
		return id;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
