package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAREA")
@Inheritance(strategy = InheritanceType.JOINED)
public class Tarea {

	protected int idTarea;
	protected Usuario usuario;
	protected Date fechaCrecion;
	protected Date fechaFinalizacion;
	protected String tipo;
	protected String Indicaciones;
	protected String observaciones;
	protected boolean realizada;
	protected int numeroAcuario;

	@Id
	@GeneratedValue
	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	@ManyToOne
	@JoinColumn(name = "idUsuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public Date getFechaCrecion() {
		return fechaCrecion;
	}

	public void setFechaCrecion(Date fechaCrecion) {
		this.fechaCrecion = fechaCrecion;
	}

	public Date getFechaFinalizacion() {
		return fechaFinalizacion;
	}

	public void setFechaFinalizacion(Date fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getIndicaciones() {
		return Indicaciones;
	}

	public void setIndicaciones(String indicaciones) {
		Indicaciones = indicaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public int getNumeroAcuario() {
		return numeroAcuario;
	}

	public void setNumeroAcuario(int numeroAcuario) {
		this.numeroAcuario = numeroAcuario;
	}

}