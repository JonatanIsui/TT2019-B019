package com.tt2.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "material_consulta")
@SequenceGenerator(name = "S_material_consulta", sequenceName = "S_material_consulta")
public class MaterialConsulta implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_material_consulta")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_material_consulta")
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
	
	@Column(name = "varillaArmex",nullable=false,columnDefinition="FLOAT(6,2)")
	private double varillaArmex;
	
	@Column(name = "ladrillo_rojo",nullable = false, columnDefinition = "FLOAT(8,2)")	
	private double ladrilloRojo;
	
	@Column(name = "ladrillo_block_ligero",nullable = false, columnDefinition = "FLOAT(8,2)")
	private double ladrilloBlockLigero;
	
	@Column(name = "ladrillo_block_pesado",nullable = false, columnDefinition = "FLOAT(8,2)")
	private double ladrilloBloackPesado;
	
	@Column(name = "costo_arena",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double arenaCosto;
	
	@Column(name = "costo_varillaArmex",nullable=false,columnDefinition="FLOAT(7,2)")
	private double varillaArmexCosto;
	
	@Column(name = "costo_alambre",nullable = false, columnDefinition = "FLOAT(6,2)")
	private double alambreCosto;
	
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
	
	@Column(name = "arena_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String arenaDesc;
	
	@Column(name = "grava_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String gravaDesc;
	
	@Column(name = "cemento_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String sacoDesc;
	
	@Column(name = "varillaArmex_descripcion",nullable=false,columnDefinition="VARCHAR(300)")
	private String varillaArmexDes;
	
	@Column(name = "mortero_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String sacoMorteroDesc;
	
	@Column(name = "varilla_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String varillaDesc;
	
	@Column(name = "ladrilloRojo_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String ladrilloRojoDesc;
	
	@Column(name = "ladrilloBlockLigero_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String ladrilloBlockLigeroDesc;
	
	@Column(name = "ladrilloBloackPesado_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String ladrilloBloackPesadoDesc;
	
	@Column(name = "alambre",nullable = false, columnDefinition = "FLOAT(7,2)")
	private double alambre;
	
	@Column(name = "alambre_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String alambreDesc;
	
	
	public String getAlambreDesc() {
		return alambreDesc;
	}
	public void setAlambreDesc(String alambreDesc) {
		this.alambreDesc = alambreDesc;
	}
	public double getVarillaArmex() {
		return varillaArmex;
	}
	public void setVarillaArmex(double varillaArmex) {
		this.varillaArmex = varillaArmex;
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
	
	public double getVarillaArmexCosto() {
		return varillaArmexCosto;
	}
	public void setVarillaArmexCosto(double varillaArmexCosto) {
		this.varillaArmexCosto = varillaArmexCosto;
	}
	public String getVarillaArmexDes() {
		return varillaArmexDes;
	}
	public void setVarillaArmexDes(String varillaArmexDes) {
		this.varillaArmexDes = varillaArmexDes;
	}
	public double getAlambreCosto() {
		return alambreCosto;
	}
	public void setAlambreCosto(double alambreCosto) {
		this.alambreCosto = alambreCosto;
	}
	public double getAlambre() {
		return alambre;
	}
	public void setAlambre(double alambre) {
		this.alambre = alambre;
	}
	public String getArenaDesc() {
		return arenaDesc;
	}
	public void setArenaDesc(String arenaDesc) {
		this.arenaDesc = arenaDesc;
	}
	public String getGravaDesc() {
		return gravaDesc;
	}
	public void setGravaDesc(String gravaDesc) {
		this.gravaDesc = gravaDesc;
	}
	public String getSacoDesc() {
		return sacoDesc;
	}
	public void setSacoDesc(String sacoDesc) {
		this.sacoDesc = sacoDesc;
	}
	public String getSacoMorteroDesc() {
		return sacoMorteroDesc;
	}
	public void setSacoMorteroDesc(String sacoMorteroDesc) {
		this.sacoMorteroDesc = sacoMorteroDesc;
	}
	public String getVarillaDesc() {
		return varillaDesc;
	}
	public void setVarillaDesc(String varillaDesc) {
		this.varillaDesc = varillaDesc;
	}
	public String getLadrilloRojoDesc() {
		return ladrilloRojoDesc;
	}
	public void setLadrilloRojoDesc(String ladrilloRojoDesc) {
		this.ladrilloRojoDesc = ladrilloRojoDesc;
	}
	public String getLadrilloBlockLigeroDesc() {
		return ladrilloBlockLigeroDesc;
	}
	public void setLadrilloBlockLigeroDesc(String ladrilloBlockLigeroDesc) {
		this.ladrilloBlockLigeroDesc = ladrilloBlockLigeroDesc;
	}
	public String getLadrilloBloackPesadoDesc() {
		return ladrilloBloackPesadoDesc;
	}
	public void setLadrilloBloackPesadoDesc(String ladrilloBloackPesadoDesc) {
		this.ladrilloBloackPesadoDesc = ladrilloBloackPesadoDesc;
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
}
