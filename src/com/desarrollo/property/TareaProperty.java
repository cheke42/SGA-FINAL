package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TareaProperty {

	private final StringProperty numeroTarea;
	private final StringProperty fechaCreacion;
	private final StringProperty tipoTarea;
	private final StringProperty creador;
	private final StringProperty estado;

	public TareaProperty(StringProperty numeroTarea, StringProperty fechaCreacion, StringProperty tipoTarea,
			StringProperty creador, StringProperty estado) {

		this.numeroTarea = numeroTarea;
		this.fechaCreacion = fechaCreacion;
		this.tipoTarea = tipoTarea;
		this.creador = creador;
		this.estado = estado;
	}

	public TareaProperty(String numeroTarea, String fechaCreacion, String tipoTarea, String creador, String estado) {
		this.numeroTarea = new SimpleStringProperty(numeroTarea);
		this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
		this.tipoTarea = new SimpleStringProperty(tipoTarea);
		this.creador = new SimpleStringProperty(creador);
		this.estado = new SimpleStringProperty(estado);
	}

	public StringProperty numeroTareaProperty() {
		return this.numeroTarea;
	}

	public String getNumeroTarea() {
		return this.numeroTarea.get();
	}

	public void setNumeroTarea(String numeroTarea) {
		this.numeroTarea.set(numeroTarea);
	}

	public StringProperty fechaCreacionProperty() {
		return this.fechaCreacion;
	}

	public String getFechaCreacion() {
		return this.fechaCreacion.get();
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion.set(fechaCreacion);
	}

	public StringProperty tipoTareaProperty() {
		return this.tipoTarea;
	}

	public String getTipoTarea() {
		return this.tipoTarea.get();
	}

	public void setTipoTarea(String tipoTarea) {
		this.tipoTarea.set(tipoTarea);
	}

	public StringProperty creadorProperty() {
		return this.creador;
	}

	public String getCreador() {
		return this.creador.get();
	}

	public void setCreador(String creador) {
		this.creador.set(creador);
	}

	public StringProperty estadoProperty() {
		return this.estado;
	}

	public String getEstado() {
		return this.estado.get();
	}

	public void setEstado(String estado) {
		this.estado.set(estado);
	}

}
