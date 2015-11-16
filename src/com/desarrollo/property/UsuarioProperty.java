package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class UsuarioProperty {

	private final StringProperty nombre;
	private final StringProperty password;
	private final StringProperty estado;
	private final StringProperty asterisco;

	public UsuarioProperty(String nombre, String password, String estado) {
		this.nombre = new SimpleStringProperty(nombre);
		this.password = new SimpleStringProperty(password);
		this.estado = new SimpleStringProperty(estado);
		this.asterisco = new SimpleStringProperty("********");
	}

	public UsuarioProperty(StringProperty nombre, StringProperty password, StringProperty estado) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.estado = estado;
		this.asterisco = null;
	}

	public StringProperty nombreProperty() {
		return nombre;
	}

	public String getNombre() {
		return nombre.get();
	}

	public void setnombre(String nombre) {
		this.nombre.set(nombre);
	}

	public StringProperty passwordProperty() {
		return password;
	}

	public String getPassword() {
		return password.get();
	}

	public void setPassword(String password) {
		this.password.set(password);
	}

	public StringProperty estadoProperty() {
		return estado;
	}

	public String getEstado() {
		return estado.get();
	}

	public void SetEstado(String estado) {
		this.estado.set(estado);
	}

	public StringProperty asteriscoProperty() {
		return asterisco;
	}

	public String getAsterisco() {
		return asterisco.get();
	}

	public void SetAsterisco() {
		this.asterisco.set("********");
	}

}
