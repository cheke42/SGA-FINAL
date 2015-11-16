package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Acuario {

	private int idAcuario;
	private Date fechaCreacion;
	private float capacidad;
	private boolean disponible;
	private String tipoAgua;
	private List<Especie> especies = new ArrayList<Especie>();
	private List<Parametro> parametros = new ArrayList<Parametro>();
	private List<AgrupacionEspecie> agrupacionEspecie = new ArrayList<AgrupacionEspecie>();
	private List<Alerta> alertas;

	@Id
	public int getIdAcuario() {
		return idAcuario;
	}

	public void setIdAcuario(int idAcuario) {
		this.idAcuario = idAcuario;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public float getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(float capacidad) {
		this.capacidad = capacidad;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getTipoAgua() {
		return tipoAgua;
	}

	public void setTipoAgua(String tipoAgua) {
		this.tipoAgua = tipoAgua;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_ESPECIE", joinColumns = { @JoinColumn(name = "idAcuario") }, inverseJoinColumns = { @JoinColumn(name = "idEspecie") })
	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_PARAMETRO", joinColumns = { @JoinColumn(name = "idAcuario") }, inverseJoinColumns = { @JoinColumn(name = "idParametro") })
	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	@OneToMany(targetEntity = Alerta.class, mappedBy = "acuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}

	@ManyToMany
	@JoinTable(name = "ACUARIO_AGRUPACIONESPECIE", joinColumns = { @JoinColumn(name = "idAcuario") }, inverseJoinColumns = { @JoinColumn(name = "idAgrupacion") })
	public List<AgrupacionEspecie> getAgrupacionEspecie() {
		return agrupacionEspecie;
	}

	public void setAgrupacionEspecie(List<AgrupacionEspecie> agrupacionEspecie) {
		this.agrupacionEspecie = agrupacionEspecie;
	}

	public void agregarParametro(Parametro parametro) {
		boolean agregado = false;
		int i = 0;

		while ((i < this.parametros.size()) && (!agregado)) {
			if (this.parametros.get(i).getNombre().equals(parametro.getNombre())) {
				agregado = true;
			}
			i++;
		}

		if (!agregado) {
			this.parametros.add(parametro);
		}
	}

	public void quitarParametro(String nombreParametro) {
		int i = 0;
		boolean encontrado = false;

		while ((i < this.parametros.size()) && (!encontrado)) {
			if (this.parametros.get(i).getNombre().equals(nombreParametro)) {
				encontrado = true;
				this.parametros.remove(i);
			}
			i++;
		}

	}

	public void quitarParametro(Parametro parametro) {
		int i = 0;
		boolean encontrado = false;

		while ((i < this.parametros.size()) && (!encontrado)) {
			if (this.parametros.get(i).getNombre().equals(parametro.getNombre())) {
				encontrado = true;
				this.parametros.remove(i);
			}
			i++;
		}
	}

	public void agregarEspecie(Especie especie) {
		boolean agregada = false;
		int i = 0;

		while ((i < this.especies.size()) && (!agregada)) {
			if (this.especies.get(i).getNombre().equals(especie.getNombre())) {
				agregada = true;
			}
			i++;
		}

		if (!agregada) {
			this.especies.add(especie);
		}
	}

	public void quitarEspecie(String nombreEspecie) {
		int i = 0;
		boolean encontrado = false;

		while ((i < this.especies.size()) && (!encontrado)) {
			if (this.especies.get(i).getNombre().equals(nombreEspecie)) {
				this.especies.remove(i);
			}
			i++;
		}

	}

	public void quitarEspecie(Especie especie) {
		int i = 0;
		boolean encontrado = false;

		while ((i < this.especies.size()) && (!encontrado)) {
			if (this.especies.get(i) == especie) {
				this.especies.remove(i);
			}
			i++;
		}

	}

	public Parametro obtenerParametro(Parametro parametroMedido) {

		int i = 0;
		boolean encontrado = false;

		while ((i < this.parametros.size()) && (!encontrado)) {
			if (this.parametros.get(i) == parametroMedido) {
				encontrado = true;
			}
			if (!encontrado) {
				i++;
			}
		}

		if (encontrado) {

			return this.parametros.get(i);
		} else {
			return null;
		}
	}

	public Parametro obtenerParametro(String nombreParametro) {
		int i = 0;
		boolean encontrado = false;

		while ((i < this.parametros.size()) && (!encontrado)) {
			if (this.parametros.get(i).getNombre().equals(nombreParametro)) {
				encontrado = true;
			}
			if (!encontrado) {
				i++;
			}
		}
		if (encontrado) {
			i = i - 1;
			return this.parametros.get(i);
		} else {
			return null;
		}
	}

	public boolean verificarParametros(ArrayList<Parametro> parametros) {

		int i = 0;
		boolean aceptado = true;
		int j;

		while ((i < getParametros().size()) && (aceptado)) {
			j = 0;
			boolean encontrado = false;
			while ((j < parametros.size()) && (!encontrado)) {
				if (this.parametros.get(i).getNombre().equals(parametros.get(j).getNombre())) {
					encontrado = true;
				}
				if (!encontrado) {
					j++;
				}
				;
			}
			if (encontrado) {
				if ((parametros.get(j).getMaximo() > this.parametros.get(i).getMaximo())
						|| (parametros.get(j).getMinimo() < this.parametros.get(i).getMinimo())) {
					aceptado = false;
				}
			}
			i++;
		}

		return aceptado;
	}

	// public void agregarIndividuos(Integer idEspecie, Integer cantidad){
	//
	// int i = 0;
	// boolean encontrada = false;
	//
	// while (i < this.especies.size() && !encontrada) {
	// if (especies.get(i).getIdEspecie().equals(idEspecie)) {
	// encontrada = true;
	// }
	// i++;
	// }
	//
	// i= i-1;
	//
	// if (encontrada)
	// {especies.get(i).setCantidad(especies.get(i).getCantidad() + cantidad); }
	// }
	//
	//
	// // public void quitarIndividuos(Integer idEspecie, Integer cantidad){
	// // int i = 0;
	// // boolean encontrada = false;
	// //
	// // while (i < this.especies.size() && !encontrada) {
	// // if (especies.get(i).getIdEspecie().equals(idEspecie)) {
	// // encontrada = true;
	// // }
	// i++;
	// }
	//
	// i= i-1;
	//
	// if (encontrada)
	// {especies.get(i).setCantidad(especies.get(i).getCantidad() - cantidad); }
	// }
	//
	// public String getResumenAcuario(){
	// String especiesAlojadas = new String();
	// for (int i = 0; i < this.especies.size(); i++) {
	// String concat =
	// especiesAlojadas.concat(especies.get(i).getResumenEspecie());
	// especiesAlojadas = especiesAlojadas +" ."+ concat;
	//
	// }
	// return "Acuario id: " +getIdAcuario()+" .Fecha y hora de creaci�n: "
	// +getFechaHoraCreacion().toString()+" .Capacidad: "+getCapacidad()+"lts. .Especies alojadas: "+especiesAlojadas;
	// }
}
