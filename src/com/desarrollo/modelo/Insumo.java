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
@Table(name = "INSUMO")
public class Insumo {
	private int idInsumo;
	private String nombre;
	private String estadoMaterial;
	private String unidadMedida;
	private float cantidadDisponible;
	private float cantidadMaxima;
	private float cantidadMinima;
	private List<PaqueteInsumo> stockInsumos = new ArrayList<PaqueteInsumo>();
	private List<Limpieza> limpiezas;

	@Id
	@GeneratedValue
	public int getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(int idInsumo) {
		this.idInsumo = idInsumo;
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

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
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

	@ManyToMany
	@JoinTable(name = "INSUMO_PAQUETEINSUMO", joinColumns = { @JoinColumn(name = "idInsumo") }, inverseJoinColumns = { @JoinColumn(name = "idPaqueteInsumo") })
	public List<PaqueteInsumo> getStockInsumos() {
		return stockInsumos;
	}

	public void setStockInsumos(List<PaqueteInsumo> stockInsumos) {
		this.stockInsumos = stockInsumos;
	}

	@OneToMany(targetEntity = Limpieza.class, mappedBy = "insumo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Limpieza> getLimpiezas() {
		return limpiezas;
	}

	public void setLimpiezas(List<Limpieza> limpiezas) {
		this.limpiezas = limpiezas;
	}

	public void actualizarStock() {
		cantidadDisponible = 0f;
		for (int i = 0; i < stockInsumos.size(); i++) {
			if (stockInsumos.get(i).isDisponible()) {
				cantidadDisponible += stockInsumos.get(i).getCantidad();
			}
		}
	}

	public boolean cantidadDecrementarDisponbile(float cant) {
		return this.cantidadDisponible >= cant;
	}

	public boolean decrementarInsumo(float cant) {
		actualizarStock();
		boolean decrementado = false;
		if (cantidadDecrementarDisponbile(cant)) {
			for (int i = 0; i < stockInsumos.size(); i++) {
				if (cant > 0) {
					if (stockInsumos.get(i).isDisponible()) {
						if (stockInsumos.get(i).getCantidad() > cant) {
							stockInsumos.get(i).setCantidad(stockInsumos.get(i).getCantidad() - cant);
							cant = cant - cant;
						} else if (stockInsumos.get(i).getCantidad() == cant) {
							stockInsumos.get(i).setCantidad(0f);
							cant = 0f;
							stockInsumos.get(i).setDisponible(false);
						} else if ((stockInsumos.get(i).getCantidad() > 0) & (stockInsumos.get(i).getCantidad() < cant)) {
							cant = cant - stockInsumos.get(i).getCantidad();
							stockInsumos.get(i).setCantidad(0f);
							stockInsumos.get(i).setDisponible(false);
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
