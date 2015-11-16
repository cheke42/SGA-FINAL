package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AlimentoInsumoProperty {

	private final StringProperty nombre;
	private final StringProperty cantidadDisponible;
	private final StringProperty cantidadMaxima;
	private final StringProperty cantidadMinima;

	public AlimentoInsumoProperty(String nombre, String cantidadDisponible, String cantidadMinima, String cantidadMaxima) {
		this.nombre = new SimpleStringProperty(nombre);
		this.cantidadDisponible = new SimpleStringProperty(cantidadDisponible);
		this.cantidadMaxima = new SimpleStringProperty(cantidadMaxima);
		this.cantidadMinima = new SimpleStringProperty(cantidadMinima);
	}

	public AlimentoInsumoProperty(StringProperty nombre, StringProperty cantidadDisponible,
			StringProperty cantidadMaxima, StringProperty cantidadMinima) {

		this.nombre = nombre;
		this.cantidadDisponible = cantidadDisponible;
		this.cantidadMaxima = cantidadMaxima;
		this.cantidadMinima = cantidadMinima;
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
	
	public StringProperty cantidadDisponible(){
		return cantidadDisponible;
	}
	
	public String getCantidadDisponible(){
		return nombre.get();
	}
	
	public void setCantidadDisponible(String cantidadDisponible){
		this.cantidadDisponible.set(cantidadDisponible);
	}
	
	public StringProperty cantidadMinima(){
		return cantidadMinima;
	}
	
	public String getCantidadMinima(){
		return cantidadMinima.get();
	}
	
	public void setCantidadMinima(String cantidadMinima){
		this.cantidadMinima.set(cantidadMinima);
	}
	
	public StringProperty cantidadMaxima(){
		return cantidadMaxima;
	}
	
	public String getCantidadMaxima(){
		return cantidadMaxima.get();
	}
	
	public void setCantidadMaxima(String cantidadMaxima){
		this.cantidadMaxima.set(cantidadMaxima);
	}

}
