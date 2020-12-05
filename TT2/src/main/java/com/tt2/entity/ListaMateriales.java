package com.tt2.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lista_materiales")
public class ListaMateriales implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_lista_Materiales")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "cantidad", nullable = false,columnDefinition = "INT(10)")
	private int cantidad;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_id_estimacion")
	private Estimacion estimacion;
	
	@ManyToMany(mappedBy = "listaMateriales")
	private List<Material> material = new ArrayList<>();

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Estimacion getEstimacion() {
		return estimacion;
	}

	public void setEstimacion(Estimacion estimacion) {
		this.estimacion = estimacion;
	}

	public List<Material> getMaterial() {
		return material;
	}

	public void setMaterial(List<Material> material) {
		this.material = material;
	}

	public int getId() {
		return id;
	}	
}
