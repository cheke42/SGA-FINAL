package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICION")
public class Medicion extends Tarea {

	private int numeroMedicion;
	private Parametro parametro;
	private float cantidad;
	private Date fechaMedicion;
	private Usuario usuario;

	public int getNumeroMedicion() {
		return numeroMedicion;
	}

	public void setNumeroMedicion(int numeroMedicion) {
		this.numeroMedicion = numeroMedicion;
	}

	@ManyToOne
	@JoinColumn(name = "idParametro")
	public Parametro getParametro() {
		return parametro;
	}

	public void setParametro(Parametro parametro) {
		this.parametro = parametro;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaMedicion() {
		return fechaMedicion;
	}

	public void setFechaMedicion(Date fechaMedicion) {
		this.fechaMedicion = fechaMedicion;
	}

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}

/**
 * 
 * idTarea + nroMedicion + idParametro + cantidadMedicion + fechaMedicion +
 * responsableMedicion
 * 
 */
