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
@Table(name = "ALIMENTO")
public class Alimento {

	private int idAlimento;
	private String nombre;
	private String estadoMaterial;
	private String unidadMedida;
	private float cantidadDisponible;
	private float cantidadMaxima;
	private float cantidadMinima;
	private float cantidadTemporalPlanificacion;
	private List<PaqueteAlimento> stockAlimentos = new ArrayList<PaqueteAlimento>();
	private List<Especie> especies = new ArrayList<Especie>();
	private List<Alimentacion> alimentaciones;

	@Id
	@GeneratedValue
	public int getIdAlimento() {
		return idAlimento;
	}

	public void setIdAlimento(int idAlimento) {
		this.idAlimento = idAlimento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getCantidadDisponible() {
		return cantidadDisponible;
	}

	public void setCantidadDisponible(float cantidadDisponible) {
		this.cantidadDisponible = cantidadDisponible;
	}

	public float getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(float cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public float getCantidadMinima() {
		return cantidadMinima;
	}

	public void setCantidadMinima(float cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}

	public String getEstadoMaterial() {
		return estadoMaterial;
	}

	public void setEstadoMaterial(String estadoMaterial) {
		this.estadoMaterial = estadoMaterial;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public float getCantidadTemporalPlanificacion() {
		return cantidadTemporalPlanificacion;
	}

	public void setCantidadTemporalPlanificacion(float cantidadTemporalPlanificacion) {
		this.cantidadTemporalPlanificacion = cantidadTemporalPlanificacion;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	@ManyToMany
	@JoinTable(name = "ALIMENTO_PAQUETEALIMENTO", joinColumns = { @JoinColumn(name = "idAlimento") }, inverseJoinColumns = { @JoinColumn(name = "idPaqueteAlimento") })
	public List<PaqueteAlimento> getStockAlimento() {
		return stockAlimentos;
	}

	public void setStockAlimento(List<PaqueteAlimento> stockAlimento) {
		this.stockAlimentos = stockAlimento;
	}

	@ManyToMany
	@JoinTable(name = "ESPECIE_ALIMENTO", joinColumns = { @JoinColumn(name = "idAlimento") }, inverseJoinColumns = { @JoinColumn(name = "idEspecie") })
	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	@OneToMany(targetEntity = Alimentacion.class, mappedBy = "alimento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alimentacion> getAlimentaciones() {
		return alimentaciones;
	}

	public void setAlimentaciones(List<Alimentacion> alimentaciones) {
		this.alimentaciones = alimentaciones;
	}

	public void ingresarAlimentoStock(PaqueteAlimento paqueteAlimento) {
		stockAlimentos.add(paqueteAlimento);
		setCantidadDisponible(getCantidadDisponible() + paqueteAlimento.getCantidad());
	}

	// Dado un paquete alimento, lo remueve si lo encuentra en la colecci�n.
	// Util para quitar el paquete recien agregado.
	public void quitarAlimentoStock(PaqueteAlimento paqueteAlimento) {
		int i = 0;
		boolean encontrado = false;

		while ((i < stockAlimentos.size()) && !encontrado) {
			if (stockAlimentos.get(i) == paqueteAlimento) {
				setCantidadDisponible(getCantidadDisponible() - paqueteAlimento.getCantidad());
				stockAlimentos.remove(i);
				encontrado = true;
			}
		}
	}

	public void actualizarStock() {
		cantidadDisponible = 0f;
		for (int i = 0; i < stockAlimentos.size(); i++) {
			if (stockAlimentos.get(i).isDisponible()) {
				cantidadDisponible += stockAlimentos.get(i).getCantidad();
			}
		}
	}

	public boolean cantidadDecrementarDisponbile(float cantidad) {
		return this.cantidadDisponible >= cantidad;
	}

	public boolean decrementarAlimento(float cant) {
		actualizarStock();
		System.out.println(cant + " cantidad por parametro," + this.cantidadDisponible + " cantidad disponible");
		boolean decrementado = false;

		if (cantidadDecrementarDisponbile(cant)) {
			for (int i = 0; i < stockAlimentos.size(); i++) {
				if (cant > 0) {
					if (stockAlimentos.get(i).isDisponible()) {
						if (stockAlimentos.get(i).getCantidad() > cant) {
							stockAlimentos.get(i).setCantidad(stockAlimentos.get(i).getCantidad() - cant);
							cant = cant - cant;
						} else if (stockAlimentos.get(i).getCantidad() == cant) {
							stockAlimentos.get(i).setCantidad(stockAlimentos.get(i).getCantidad() - cant);
							cant = cant - cant;
							stockAlimentos.get(i).setDisponible(false);
						} else if ((stockAlimentos.get(i).getCantidad() > 0)
								& (stockAlimentos.get(i).getCantidad() < cant)) {
							cant = cant - stockAlimentos.get(i).getCantidad();
							stockAlimentos.get(i).setCantidad(0f);
							stockAlimentos.get(i).setDisponible(false);
						}
					}
				}
			}
			if (cant == 0) {
				decrementado = true;
				actualizarStock();
			}
		}
		return decrementado;
	}

}
