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
	
	@Column(name="nombre_proveedor",nullable= false, columnDefinition="VARCHAR(250)")
	private String nombreProveedor;
	
	@Column(name="telefono_proveedor",nullable= false, columnDefinition="VARCHAR(10)")
	private String telefonoProveedor;
	
	@Column(name="correo_proveedor",nullable= false, columnDefinition="VARCHAR(300)")
	private String correoProveedor;
	
	@Column(name="direccion_proveedor",nullable= false, columnDefinition="VARCHAR(250)")
	private String direccionProveedor;
	
	@Column(name="total_consulta",nullable= false, columnDefinition="FLOAT(8,2)")
	private double totalConsulta;
	
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
	
	@Column(name = "ancholavado",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double anchoLavado;
	
	@Column(name = "alambre_descripcion",nullable = false, columnDefinition = "VARCHAR(300)")
	private String alambreDesc;
	
	@Column(name = "largolavado",nullable = false, columnDefinition = "FLOAT(3,2)")
	private double largoLavado;
	
	@Column(name = "alambre",nullable = false, columnDefinition = "FLOAT(7,2)")
	private double alambre;
	
	@Column(name = "fechaConsulta", columnDefinition = "VARCHAR(100)")
	private String fechaConsulta;
	
	public double getVarillaArmex() {
		return varillaArmex;
	}
	public void setVarillaArmex(double varillaArmex) {
		this.varillaArmex = varillaArmex;
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
	public String getFechaConsulta() {
		return fechaConsulta;
	}
	public void setFechaConsulta(String fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
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
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getTelefonoProveedor() {
		return telefonoProveedor;
	}
	public void setTelefonoProveedor(String telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}
	public String getCorreoProveedor() {
		return correoProveedor;
	}
	public void setCorreoProveedor(String correoProveedor) {
		this.correoProveedor = correoProveedor;
	}
	public String getDireccionProveedor() {
		return direccionProveedor;
	}
	public void setDireccionProveedor(String direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
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
	public String getAlambreDesc() {
		return alambreDesc;
	}
	public void setAlambreDesc(String alambreDesc) {
		this.alambreDesc = alambreDesc;
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
