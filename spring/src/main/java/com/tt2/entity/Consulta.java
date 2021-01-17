package com.tt2.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "consulta")
@SequenceGenerator(name = "S_consulta", sequenceName = "S_consulta")
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_consulta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_consulta")
	private int id;
	
	@Column(name = "nombre" ,nullable = false, columnDefinition = "VARCHAR(300)")
	private String nombre;
		
	@Column(name = "excel",nullable = false, columnDefinition = "VARCHAR(300)")
	private String excel;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_arquitecto")
	private Arquitecto arquitecto;
	
	@Column(name = "anchoHabitacion1",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchoHabitacion1;
	
	@Column(name = "anchoHabitacion2",nullable = true, columnDefinition = "FLOAT(3,2)")
	private double anchoHabitacion2;
	
	@Column(name = "anchobano",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchobano;
	
	@Column(name = "anchococina",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchococina;
	
	@Column(name = "anchoterreno",nullable = false, columnDefinition = "FLOAT(4,2)")
	private double anchoterreno;
	
	@Column(name = "anchoSala",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchoSala;
	
	@Column(name = "largoSala",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoSala;
	
	@Column(name = "largoHabitacion1",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoHabitacion1;
	
	@Column(name = "largoHabitacion2",nullable = true, columnDefinition = "FLOAT(3,2)")
	private double largoHabitacion2;
	
	@Column(name = "largobano",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largobano;
	
	@Column(name = "largococina",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largococina;
	
	@Column(name = "largoterreno",nullable = false, columnDefinition = "FLOAT(4,2)")
	private double largoterreno;
	
	@Column(name = "pisos",nullable = false, columnDefinition = "int(1)")
	private int pisos;
	
	@Column(name = "tipoladrillo",nullable = false, columnDefinition = "VARCHAR(50)")
	private String tipoladrillo;
		
	@Column(name="total_consulta",nullable= false, columnDefinition="FLOAT(8,2)")
	private double totalConsulta;
	
	@Column(name = "ancholavado",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchoLavado;
	
	@Column(name = "largolavado",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoLavado;
	
	@Column(name = "fechaConsulta", columnDefinition = "VARCHAR(100)")
	private String fechaConsulta;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fk_id_material_consulat")
	private MaterialConsulta materialConsulta;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_id_Consulta")
	private List<ConsultaProveedor> proveedorConsulta; 
	
	public double getAnchoSala() {
		return anchoSala;
	}
	public void setAnchoSala(double anchoSala) {
		this.anchoSala = anchoSala;
	}
	public double getLargoSala() {
		return largoSala;
	}
	public void setLargoSala(double largoSala) {
		this.largoSala = largoSala;
	}
	public List<ConsultaProveedor> getProveedorConsulta() {
		return proveedorConsulta;
	}
	public void setProveedorConsulta(List<ConsultaProveedor> proveedorConsulta) {
		this.proveedorConsulta = proveedorConsulta;
	}
	public MaterialConsulta getMaterialConsulta() {
		return materialConsulta;
	}
	public void setMaterialConsulta(MaterialConsulta materialConsulta) {
		this.materialConsulta = materialConsulta;
	}
	public String getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}
	public String getTipoladrillo() {
		return tipoladrillo;
	}
	public void setTipoladrillo(String tipoladrillo) {
		this.tipoladrillo = tipoladrillo;
	}
	public double getAnchoLavado() {
		return anchoLavado;
	}
	public void setAnchoLavado(double anchoLavado) {
		this.anchoLavado = anchoLavado;
	}
	public double getLargoLavado() {
		return largoLavado;
	}
	public void setLargoLavado(double largoLavado) {
		this.largoLavado = largoLavado;
	}
		
	public double getTotalConsulta() {
		return totalConsulta;
	}
	public void setTotalConsulta(double totalConsulta) {
		this.totalConsulta = totalConsulta;
	}
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getExcel() {
		return excel;
	}
	public void setExcel(String excel) {
		this.excel = excel;
	}
	public void setArquitecto(Arquitecto arquitecto) {
		this.arquitecto = arquitecto;
	}
	
	public double getAnchoHabitacion1() {
		return anchoHabitacion1;
	}
	public void setAnchoHabitacion1(double anchoHabitacion1) {
		this.anchoHabitacion1 = anchoHabitacion1;
	}
	public double getAnchoHabitacion2() {
		return anchoHabitacion2;
	}
	public void setAnchoHabitacion2(double anchoHabitacion2) {
		this.anchoHabitacion2 = anchoHabitacion2;
	}
	public double getAnchobano() {
		return anchobano;
	}
	public void setAnchobano(double anchobano) {
		this.anchobano = anchobano;
	}
	public double getAnchococina() {
		return anchococina;
	}
	public void setAnchococina(double anchococina) {
		this.anchococina = anchococina;
	}
	public double getAnchoterreno() {
		return anchoterreno;
	}
	public void setAnchoterreno(double anchoterreno) {
		this.anchoterreno = anchoterreno;
	}
	public double getLargoHabitacion1() {
		return largoHabitacion1;
	}
	public void setLargoHabitacion1(double largoHabitacion1) {
		this.largoHabitacion1 = largoHabitacion1;
	}
	public double getLargoHabitacion2() {
		return largoHabitacion2;
	}
	public void setLargoHabitacion2(double largoHabitacion2) {
		this.largoHabitacion2 = largoHabitacion2;
	}
	public double getLargobano() {
		return largobano;
	}
	public void setLargobano(double largobano) {
		this.largobano = largobano;
	}
	public double getLargococina() {
		return largococina;
	}
	public void setLargococina(double largococina) {
		this.largococina = largococina;
	}
	public double getLargoterreno() {
		return largoterreno;
	}
	public void setLargoterreno(double largoterreno) {
		this.largoterreno = largoterreno;
	}
	public int getPisos() {
		return pisos;
	}
	public void setPisos(int pisos) {
		this.pisos = pisos;
	}
	public Arquitecto getArquitecto() {
		return arquitecto;
	}
	
	
}
