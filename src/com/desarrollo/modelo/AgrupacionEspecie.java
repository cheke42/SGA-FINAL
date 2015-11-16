package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class AgrupacionEspecie {

	private int idAgrupacion;
	private int cantidad;
	private Especie especie;
	private List<Acuario> acuarios = new ArrayList<Acuario>();

	@Id
	@GeneratedValue
	public int getIdAgrupacion() {
		return idAgrupacion;
	}

	public void setIdAgrupacion(int idAgrupacion) {
		this.idAgrupacion = idAgrupacion;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@ManyToOne
	@JoinColumn(name="idEspecie")
	public Especie getEspecie() {
		return especie;
	}

	public void setEspecie(Especie especie) {
		this.especie = especie;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_AGRUPACIONESPECIE", joinColumns = { @JoinColumn(name = "idAgrupacion") }, inverseJoinColumns = { @JoinColumn(name = "idAcuario") })
	public List<Acuario> getAcuarios() {
		return acuarios;
	}

	public void setAcuarios(List<Acuario> acuarios) {
		this.acuarios = acuarios;
	}

}
