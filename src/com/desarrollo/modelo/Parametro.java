package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PARAMETRO")
public class Parametro {

	private int idParametro;
	private String nombre;
	private String descripcion;
	private float maximo;
	private float minimo;
	private List<Especie> especies = new ArrayList<Especie>();
	private List<Acuario> acuarios = new ArrayList<Acuario>();
	private List<Medicion> mediciones;

	@Id
	@GeneratedValue
	public int getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getMaximo() {
		return maximo;
	}

	public void setMaximo(float maximo) {
		this.maximo = maximo;
	}

	public float getMinimo() {
		return minimo;
	}

	public void setMinimo(float minimo) {
		this.minimo = minimo;
	}

	@ManyToMany
	@JoinTable(name = "ESPECIE_PARAMETRO", joinColumns = { @JoinColumn(name = "idParametro") }, inverseJoinColumns = { @JoinColumn(name = "idEspecie") })
	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_PARAMETRO", joinColumns = { @JoinColumn(name = "idParametro") }, inverseJoinColumns = { @JoinColumn(name = "idAcuario") })
	public List<Acuario> getAcuarios() {
		return acuarios;
	}

	public void setAcuarios(List<Acuario> acuarios) {
		this.acuarios = acuarios;
	}

	@OneToMany(targetEntity = Medicion.class, mappedBy = "parametro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

}
