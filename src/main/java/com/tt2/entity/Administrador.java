package com.tt2.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "administrador")
public class Administrador implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_administrador")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name="nombre_administrador", nullable = false, columnDefinition="VARCHAR(50)")
	private String nombreAdministrador;

	public String getNombreAdministrador() {
		return nombreAdministrador;
	}

	public void setNombreAdministrador(String nombreAdministrador) {
		this.nombreAdministrador = nombreAdministrador;
	}

	public int getId() {
		return id;
	}
}
