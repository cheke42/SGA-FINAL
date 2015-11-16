package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EVENTOS")
public class Evento {
	private int numeroEvento;
	private String nombreUsuario;
	private String descripcion;
	private Date fecha;

	@Id
	@GeneratedValue
	public int getNumeroEvento() {
		return numeroEvento;
	}

	public void setNumeroEvento(int numeroEvento) {
		this.numeroEvento = numeroEvento;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
