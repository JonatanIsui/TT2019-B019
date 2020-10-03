package com.init.construccion1.entitys;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//Indica que es una identidad
@Entity(name = "usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	// Indica que es el id de la tabla
	@Id
	// Indica el nombre de la columna
	@Column(name = "iduser")
	// ID autoincrementable
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	// No permite campo vacio e indica tamaño y tipo
	@Column(name = "email", nullable = false, unique = true, columnDefinition = "VARCHAR(50)")
	private String email;
	@Column(name = "password", nullable = false, columnDefinition = "VARCHAR(16)")
	private String password;
	@Column(name = "tipo",columnDefinition = "VARCHAR(15)")
	private String tipo;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idarquitecto", unique = true)
	private Arquitecto arquitecto;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idadministrador", unique = true)
	private Administrador administrador;
	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "idproveedor", unique = true)
	private Proveedor proveedor;
	public Arquitecto getArquitecto() {
		return arquitecto;
	}
	public void setArquitecto(Arquitecto arquitecto) {
		this.arquitecto = arquitecto;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
