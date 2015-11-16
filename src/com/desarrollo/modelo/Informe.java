package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INFORME")
public class Informe {
	private int idInforme;
	private String tipoInforme;
	private Date fechaGeneracionInforme;
	private String descripcion;

	@Id
	@GeneratedValue
	public int getIdInforme() {
		return idInforme;
	}

	public void setIdInforme(int idInforme) {
		this.idInforme = idInforme;
	}

	public String getTipoInforme() {
		return tipoInforme;
	}

	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}

	public Date getFechaGeneracionInforme() {
		return fechaGeneracionInforme;
	}

	public void setFechaGeneracionInforme(Date fechaGeneracionInforme) {
		this.fechaGeneracionInforme = fechaGeneracionInforme;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
