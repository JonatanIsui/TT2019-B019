package com.tt2.model;

import java.io.Serializable;

public class MedidasModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private double anchoHabitacion1;
	private double anchoHabitacion2;
	private double anchobano;
	private double anchococina;
	private double anchoterreno;
	private double largoHabitacion1;
	private double largoHabitacion2;
	private double largobano;
	private double largococina;
	private double largoterreno;
	private int tipoladrillo;
	private int pisos;
	private int idArquitecto;
	
	public int getTipoladrillo() {
		return tipoladrillo;
	}
	public void setTipoladrillo(int tipoladrillo) {
		this.tipoladrillo = tipoladrillo;
	}
	public int getPisos() {
		return pisos;
	}
	public int getIdArquitecto() {
		return idArquitecto;
	}
	public void setIdArquitecto(int idArquitecto) {
		this.idArquitecto = idArquitecto;
	}
	public void setPisos(int pisos) {
		this.pisos = pisos;
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
	
	
}
