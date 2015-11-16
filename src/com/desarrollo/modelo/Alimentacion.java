package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ALIMENTACION")
public class Alimentacion extends Tarea {
	private int nroAlimentacion;
	private Alimento alimento;
	private float cantidad;
	private Date fechaAlimentacion;
	private Usuario usuario;

	public int getNroAlimentacion() {
		return nroAlimentacion;
	}

	public void setNroAlimentacion(int nroAlimentacion) {
		this.nroAlimentacion = nroAlimentacion;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToOne
	@JoinColumn(name = "idAlimento")
	public Alimento getAlimento() {
		return alimento;
	}

	public void setAlimento(Alimento alimento) {
		this.alimento = alimento;
	}

	public Date getFechaAlimentacion() {
		return fechaAlimentacion;
	}

	public void setFechaAlimentacion(Date fechaAlimentacion) {
		this.fechaAlimentacion = fechaAlimentacion;
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
