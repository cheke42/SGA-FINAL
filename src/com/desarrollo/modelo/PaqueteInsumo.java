package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PAQUETEINSUMO")
public class PaqueteInsumo {
	private int idPaqueteInsumo;
	private Date FechaAdquisicion;
	private Date fechaCaducacion;
	private float cantidad;
	private boolean disponible;
	private List<Insumo> insumos = new ArrayList<Insumo>();
	@Id
	@GeneratedValue
	public int getIdPaqueteInsumo() {
		return idPaqueteInsumo;
	}

	public void setIdPaqueteInsumo(int idPaqueteInsumo) {
		this.idPaqueteInsumo = idPaqueteInsumo;
	}

	public Date getFechaAdquisicion() {
		return FechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		FechaAdquisicion = fechaAdquisicion;
	}

	public Date getFechaCaducacion() {
		return fechaCaducacion;
	}

	public void setFechaCaducacion(Date fechaCaducacion) {
		this.fechaCaducacion = fechaCaducacion;
	}

	public float getCantidad() {
		return cantidad;
	}

	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@ManyToMany
	@JoinTable(name="INSUMO_PAQUETEINSUMO",
	joinColumns={@JoinColumn(name="idPaqueteInsumo")},
	inverseJoinColumns={@JoinColumn(name="idInsumo")})
	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	
}
