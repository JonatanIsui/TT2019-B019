package com.tt2.entity;

import java.io.Serializable;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "material")
@SequenceGenerator(name = "S_material", sequenceName = "S_material")
public class Material implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_material")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_material")
	private int id;
	
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(30)")
	private String nombre;
	
	@Column(name = "categoria", nullable = false, columnDefinition = "VARCHAR(30)")
	private String categoria;

	@Column(name = "descripcion", nullable = false, columnDefinition = "VARCHAR(150)")
	private String descripcion;
	
	@Column(name = "costo", nullable = false, columnDefinition = "FLOAT(8,2)")
	private float costo;
	
	@Column(name = "clave", nullable = false, columnDefinition = "VARCHAR(30)", unique=true)
	private String clave;
	
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_proveedor")
	private Proveedor proveedor; 
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
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
	
}
