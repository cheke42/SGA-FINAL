package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIMPIEZA")
public class Limpieza extends Tarea {
	private int nroLimpieza;
	private Insumo insumo;
	private Date fechaLimpieza;
	private float cantidad;
	private Usuario usuario;

	public int getNroLimpieza() {
		return nroLimpieza;
	}

	public void setNroLimpieza(int nroLimpieza) {
		this.nroLimpieza = nroLimpieza;
	}

	@ManyToOne
	@JoinColumn(name = "idInsumo")
	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public Date getFechaLimpieza() {
		return fechaLimpieza;
	}

	public void setFechaLimpieza(Date fechaLimpieza) {
		this.fechaLimpieza = fechaLimpieza;
	}

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

}

/**
 * idTarea +nroLimpieza + idInsumo + fechaLimpieza + responsableLimpieza
 */
