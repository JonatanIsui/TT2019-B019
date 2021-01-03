package com.tt2.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_consulta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	
	@Column(name = "nombre" ,nullable = false, columnDefinition = "VARCHAR(300)")
	private String nombre;
	
	@Column(name = "agua",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double agua;
	
	@Column(name = "arena",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double arena;
	
	@Column(name = "grava",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double grava;
	
	@Column(name = "saco",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double saco;
	
	@Column(name = "sacoMortero",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double sacoMortero;
	
	@Column(name = "varilla",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double varilla;
	
	@Column(name = "ladrillo_rojo",nullable = false, columnDefinition = "FLOAT(8,2)")	
	private double ladrilloRojo;
	
	@Column(name = "ladrillo_block_ligero",nullable = false, columnDefinition = "FLOAT(8,2)")
	private double ladrilloBlockLigero;
	
	@Column(name = "ladrillo_block_pesado",nullable = false, columnDefinition = "FLOAT(8,2)")
	private double ladrilloBloackPesado;
	
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
	
	@Column(name = "anchoterreno",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchoterreno;
	
	@Column(name = "largoHabitacion1",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoHabitacion1;
	
	@Column(name = "largoHabitacion2",nullable = true, columnDefinition = "FLOAT(3,2)")
	private double largoHabitacion2;
	
	@Column(name = "largobano",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largobano;
	
	@Column(name = "largococina",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largococina;
	
	@Column(name = "largoterreno",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoterreno;
	
	@Column(name = "pisos",nullable = false, columnDefinition = "int(1)")
	private int pisos;
	
	@Column(name = "tipoladrillo",nullable = false, columnDefinition = "int(1)")
	private int tipoladrillo;
	
	@Column(name = "costo_arena",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double arenaCosto;
	
	@Column(name = "costo_grava",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double gravaCosto;
	
	@Column(name = "costo_saco",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double sacoCosto;
	
	@Column(name = "costo_mortero",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double sacoMorteroCosto;
	
	@Column(name = "costo_varilla",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double varillaCosto;
	
	@Column(name = "costo_ladrillo_rojo",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double ladrilloRojoCosto;
	
	@Column(name = "costo_ladrillo_ligero",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double ladrilloBlockLigeroCosto;
	
	@Column(name = "costo_ladrillo_pesado",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double ladrilloBloackPesadoCosto;
	
	public int getId() {
		return id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getLadrilloRojo() {
		return ladrilloRojo;
	}
	public void setLadrilloRojo(double ladrilloRojo) {
		this.ladrilloRojo = ladrilloRojo;
	}
	public double getLadrilloBlockLigero() {
		return ladrilloBlockLigero;
	}
	public void setLadrilloBlockLigero(double ladrilloBlockLigero) {
		this.ladrilloBlockLigero = ladrilloBlockLigero;
	}
	public double getLadrilloBloackPesado() {
		return ladrilloBloackPesado;
	}
	public void setLadrilloBloackPesado(double ladrilloBloackPesado) {
		this.ladrilloBloackPesado = ladrilloBloackPesado;
	}
	public int getTipoladrillo() {
		return tipoladrillo;
	}
	public void setTipoladrillo(int tipoladrillo) {
		this.tipoladrillo = tipoladrillo;
	}
	public double getArenaCosto() {
		return arenaCosto;
	}
	public void setArenaCosto(double arenaCosto) {
		this.arenaCosto = arenaCosto;
	}
	public double getGravaCosto() {
		return gravaCosto;
	}
	public void setGravaCosto(double gravaCosto) {
		this.gravaCosto = gravaCosto;
	}
	public double getSacoCosto() {
		return sacoCosto;
	}
	public void setSacoCosto(double sacoCosto) {
		this.sacoCosto = sacoCosto;
	}
	public double getSacoMorteroCosto() {
		return sacoMorteroCosto;
	}
	public void setSacoMorteroCosto(double sacoMorteroCosto) {
		this.sacoMorteroCosto = sacoMorteroCosto;
	}
	public double getVarillaCosto() {
		return varillaCosto;
	}
	public void setVarillaCosto(double varillaCosto) {
		this.varillaCosto = varillaCosto;
	}
	public double getLadrilloRojoCosto() {
		return ladrilloRojoCosto;
	}
	public void setLadrilloRojoCosto(double ladrilloRojoCosto) {
		this.ladrilloRojoCosto = ladrilloRojoCosto;
	}
	public double getLadrilloBlockLigeroCosto() {
		return ladrilloBlockLigeroCosto;
	}
	public void setLadrilloBlockLigeroCosto(double ladrilloBlockLigeroCosto) {
		this.ladrilloBlockLigeroCosto = ladrilloBlockLigeroCosto;
	}
	public double getLadrilloBloackPesadoCosto() {
		return ladrilloBloackPesadoCosto;
	}
	public void setLadrilloBloackPesadoCosto(double ladrilloBloackPesadoCosto) {
		this.ladrilloBloackPesadoCosto = ladrilloBloackPesadoCosto;
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
	public double getAgua() {
		return agua;
	}
	public void setAgua(double agua) {
		this.agua = agua;
	}
	public double getArena() {
		return arena;
	}
	public void setArena(double arena) {
		this.arena = arena;
	}
	public double getGrava() {
		return grava;
	}
	public void setGrava(double grava) {
		this.grava = grava;
	}
	public double getSaco() {
		return saco;
	}
	public void setSaco(double saco) {
		this.saco = saco;
	}
	public double getSacoMortero() {
		return sacoMortero;
	}
	public void setSacoMortero(double sacoMortero) {
		this.sacoMortero = sacoMortero;
	}
	public double getVarilla() {
		return varilla;
	}
	public void setVarilla(double varilla) {
		this.varilla = varilla;
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
	public int getTipoLadrillo() {
		return tipoladrillo;
	}
	public void setTipoLadrillo(int tipoladrillo) {
		this.tipoladrillo = tipoladrillo;
	}
	public Arquitecto getArquitecto() {
		return arquitecto;
	}
	
	
}
