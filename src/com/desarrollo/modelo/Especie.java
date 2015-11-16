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
@Table(name = "ESPECIE")
public class Especie {
	private int idEspecie;
	private String nombre;
	private int cantidad;
	private String descripcion;
	private String tipoAgua;
	private List<Alimento> alimentos = new ArrayList<Alimento>();
	private List<Acuario> acuarios = new ArrayList<Acuario>();
	private List<Parametro> parametros = new ArrayList<Parametro>();
	private List<AgrupacionEspecie> agrupacionEspecie = new ArrayList<AgrupacionEspecie>();

	@Id
	@GeneratedValue
	public int getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(int idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTipoAgua() {
		return tipoAgua;
	}

	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToMany
	@JoinTable(name = "ESPECIE_ALIMENTO", joinColumns = { @JoinColumn(name = "idEspecie") }, inverseJoinColumns = { @JoinColumn(name = "idAlimento") })
	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_ESPECIE", joinColumns = { @JoinColumn(name = "idEspecie") }, inverseJoinColumns = { @JoinColumn(name = "idAcuario") })
	public List<Acuario> getAcuarios() {
		return acuarios;
	}

	public void setAcuarios(List<Acuario> acuarios) {
		this.acuarios = acuarios;
	}

	@ManyToMany
	@JoinTable(name = "ESPECIE_PARAMETRO", joinColumns = { @JoinColumn(name = "idEspecie") }, inverseJoinColumns = { @JoinColumn(name = "idParametro") })
	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	@OneToMany(targetEntity = AgrupacionEspecie.class, mappedBy = "especie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<AgrupacionEspecie> getAgrupacionEspecie() {
		return agrupacionEspecie;
	}

	public void setAgrupacionEspecie(List<AgrupacionEspecie> agrupacionEspecie) {
		this.agrupacionEspecie = agrupacionEspecie;
	}

	public void calcularTotal() {
		this.cantidad = 0;
		for (int i = 0; i < agrupacionEspecie.size(); i++) {
			this.cantidad += agrupacionEspecie.get(i).getCantidad();
		}
	}

}
