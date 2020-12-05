package com.tt2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "material")
public class Material implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_material")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "nombre", nullable = false, columnDefinition = "VARCHAR(30)")
	private String nombre;
	
	@Column(name = "categoria", nullable = false, columnDefinition = "VARCHAR(30)")
	private String categoria;

	@Column(name = "descripcion", nullable = false, columnDefinition = "VARCHAR(150)")
	private String descripcion;
	
	@Column(name = "costo", nullable = false, columnDefinition = "FLOAT(6,2)")
	private float costo;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_proveedor")
	private Proveedor proveedor; 
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "material_lista_material",
				joinColumns = @JoinColumn(name = "id_material"),
				inverseJoinColumns = @JoinColumn(name = "id_lista_Materiales"))
	private List<ListaMateriales> listaMateriales=new ArrayList<>();

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

	public List<ListaMateriales> getListaMateriales() {
		return listaMateriales;
	}

	public void setListaMateriales(List<ListaMateriales> listaMateriales) {
		this.listaMateriales = listaMateriales;
	}

	public int getId() {
		return id;
	}
	
}