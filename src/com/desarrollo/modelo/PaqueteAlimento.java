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
@Table(name = "PAQUETEALIMENTO")
public class PaqueteAlimento {
	private int idPaqueteAlimento;
	private Date fechaAdquisicion;
	private Date fechaVencimiento;
	private float cantidad;
	private boolean disponible;
	private List<Alimento> alimentos = new ArrayList<Alimento>();
	
	@Id
	@GeneratedValue
	public int getIdPaqueteAlimento() {
		return idPaqueteAlimento;
	}

	public void setIdPaqueteAlimento(int idPaqueteAlimento) {
		this.idPaqueteAlimento = idPaqueteAlimento;
	}

	public Date getFechaAdquisicion() {
		return fechaAdquisicion;
	}

	public void setFechaAdquisicion(Date fechaAdquisicion) {
		this.fechaAdquisicion = fechaAdquisicion;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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
	@JoinTable(name="ALIMENTO_PAQUETEALIMENTO",
	joinColumns={@JoinColumn(name="idPaqueteAlimento")},
	inverseJoinColumns={@JoinColumn(name="idAlimento")})
	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	
	
}
