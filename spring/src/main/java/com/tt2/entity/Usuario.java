package com.tt2.entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
/*Indica a que tabla de la base de datos ingresar hibernate
 * se puede realizar mediate notaciones o configuracion de archivo xml
 * en esta ocasion se uso notaciones
 * */
@Table(name = "usuario")
@SequenceGenerator(name = "S_usuario", sequenceName = "S_usuario")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "S_usuario")
	private int id;
	/*
	 * nullabel indica que el campo no puede ser nulo
	 * unique indica que el campo no se puede repetir en la base
	 * columnDefinition indica el tipo y longitud en la base de datos
	 * */
	@Column(name = "correo", nullable = false, unique = true, columnDefinition = "VARCHAR(76)")
	private String correo;
	
	@Column(name = "password", nullable = false,columnDefinition = "VARCHAR(16)")
	private String password;
	
	@Column(name = "fechaLogin", columnDefinition = "DATETIME")
	@Temporal(TemporalType.DATE)
	private Date fechaLogin;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fk_id_arquitecto")
	private Arquitecto arquitecto;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fk_id_proveedor")
	private Proveedor proveedor;
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "fk_id_administrador")
	private Administrador administrador;
	
	/*El constructor sin argumentos son necesarios en las clases persistentes
	 * (Capaces de guardarse y recuperarce de un medio de almacenamiento)
	 * Para que recuperacion sea de forma efectiva
	 * */ 	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaLogin() {
		return fechaLogin;
	}

	public void setFechaLogin(Date fechaLogin) {
		this.fechaLogin = fechaLogin;
	}

	public Arquitecto getArquitecto() {
		return arquitecto;
	}

	public void setArquitecto(Arquitecto arquitecto) {
		this.arquitecto = arquitecto;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Administrador getAdministrador() {
		return administrador;
	}

	public void setAdministrador(Administrador administrador) {
		this.administrador = administrador;
	}

	public int getId() {
		return id;
	}
}
