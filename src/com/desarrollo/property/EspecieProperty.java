package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EspecieProperty {

	private final StringProperty nombre;
	private final StringProperty descripcion;
	private final StringProperty cantidad;

	public EspecieProperty(String nombre, String descripcion, String cantidad) {
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
		this.cantidad = new SimpleStringProperty(cantidad);

	}
	
	public EspecieProperty(StringProperty nombre, StringProperty descripcion, StringProperty cantidad){
		this.nombre= nombre;
		this.cantidad= cantidad;
		this.descripcion = descripcion;
	}
	
	
	public StringProperty nombreProperty(){
		return nombre;
	}
	
	public String getNombre(){
		return nombre.get();
	}
	
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	
	public StringProperty descripcionProperty(){
		return descripcion;
	}
	
	public String getDescripcion(){
		return descripcion.get();
	}
	
	public void setDescripcion(String descripcion){
		this.descripcion.set(descripcion);
	}
	
	public StringProperty cantidadProperty(){
		return cantidad;
	}
	
	public String getCantidad(){
		return cantidad.get();
	}
	
	public void setCantidad(String cantidad){
		this.cantidad.set(cantidad);
	}
	
	
	
}
