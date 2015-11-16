package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaqueteProperty {

	private final StringProperty idPaquete;
	private final StringProperty fechaAdquisicion;
	private final StringProperty fechaVencimiento;
	private final StringProperty cantidad;
	private final StringProperty disponible;

	public PaqueteProperty(StringProperty idPaquete, StringProperty fechaAdquisicion, StringProperty fechaVencimiento,
			StringProperty cantidad, StringProperty disponible) {

		this.idPaquete = idPaquete;
		this.fechaAdquisicion = fechaAdquisicion;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidad = cantidad;
		this.disponible = disponible;
	}

	public PaqueteProperty(String id, String fechaAdquisicion, String fechaVencimiento, String cantidad,
			String disponible) {
		this.idPaquete = new SimpleStringProperty(id);
		this.fechaAdquisicion = new SimpleStringProperty(fechaAdquisicion);
		this.fechaVencimiento = new SimpleStringProperty(fechaVencimiento);
		this.cantidad = new SimpleStringProperty(cantidad);
		this.disponible = new SimpleStringProperty(disponible);
	}

	public StringProperty idPaquete() {
		return idPaquete;
	}

	public String getIdPaquete() {
		return idPaquete.get();
	}

	public void setIdPaquete(String id) {
		this.idPaquete.set(id);
	}

	public StringProperty fechaAdquisicion() {
		return fechaAdquisicion;
	}

	public String getFechaAdquisicion() {
		return fechaAdquisicion.get();
	}

	public void setFechaAdquisicion(String fechaAdquisicion) {
		this.fechaAdquisicion.set(fechaAdquisicion);
	}

	public StringProperty fechaVencimiento() {
		return fechaVencimiento;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento.get();
	}

	public void setFechaVenimiento(String fechaVencimiento) {
		this.fechaVencimiento.set(fechaVencimiento);
	}

	public StringProperty cantidad() {
		return cantidad;
	}

	public String getCantidad() {
		return cantidad.get();
	}

	public void setCantidad(String cantidad) {
		this.cantidad.set(cantidad);
	}

	public StringProperty disponible() {
		return disponible;
	}

	public String getDisponible() {
		return disponible.get();
	}

	public void setDisponible(String disponible) {
		this.disponible.set(disponible);
	}

}
