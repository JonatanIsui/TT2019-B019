package com.tt2.model;

import java.io.Serializable;
import com.tt2.entity.Arquitecto;

public class ConsultaModel implements Serializable{
private static final long serialVersionUID = 1L;	
	private double agua;
	private double arena;
	private double grava;
	private double saco;
	private double sacoMortero;
	private double varilla;
	private double ladrilloRojo;
	private double ladrilloBlockLigero;
	private double ladrilloBloackPesado;
	private String excel;
	private Arquitecto arquitecto;
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
	private int pisos;
	private int tipoladrillo;
	private double arenaCosto;
	private double gravaCosto;
	private double sacoCosto;
	private double sacoMorteroCosto;
	private double varillaCosto;
	private double ladrilloRojoCosto;
	private double ladrilloBlockLigeroCosto;
	private double ladrilloBloackPesadoCosto;
	
	
	
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
	
	
}
