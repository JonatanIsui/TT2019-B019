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
	
	@Column(name = "ladrillos",nullable = false, columnDefinition = "FLOAT(8,2)")
	private double ladrillos;
	
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
	
	public int getId() {
		return id;
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
	public double getLadrillos() {
		return ladrillos;
	}
	public void setLadrillos(double ladrillos) {
		this.ladrillos = ladrillos;
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
