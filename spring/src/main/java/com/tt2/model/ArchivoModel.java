package com.tt2.model;

import java.io.Serializable;
public class ArchivoModel implements Serializable{

	private static final long serialVersionUID = 1L;
	private String catalogo;
	private int id;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}

	public void setArchivo(String catalogo) {
		this.catalogo = catalogo;
	}

}
