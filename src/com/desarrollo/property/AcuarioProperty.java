package com.desarrollo.property;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AcuarioProperty {

	private final StringProperty numero;
	private final StringProperty fechaCreacion;
	private final StringProperty capacidad;
	private final StringProperty estado;

	public AcuarioProperty(StringProperty numero, StringProperty fechaCreacion, StringProperty capacidad,
			StringProperty estado) {

		this.numero = numero;
		this.fechaCreacion = fechaCreacion;
		this.capacidad = capacidad;
		this.estado = estado;
	}
	
	public AcuarioProperty(String numero, String fechaCreacion,String capacidad,String estado){
		this.numero = new SimpleStringProperty(numero);
		this.fechaCreacion = new SimpleStringProperty(fechaCreacion);
		this.capacidad = new SimpleStringProperty(capacidad);
		this.estado = new SimpleStringProperty(estado);
	}
	
	
	public StringProperty numeroProperty(){
		return this.numero;
	}
	
	public String getNumero(){
		return this.numero.get();
	}
	
	public void setNumero(String numero){
		this.numero.set(numero);
	}
	
	public StringProperty fechaCreacionProperty(){
		return this.fechaCreacion;
	}
	
	public String getFechaCreacion(){
		return this.fechaCreacion.get();
	}
	
	public void setFechaCreaacion(String fechaCreacion){
		this.fechaCreacion.set(fechaCreacion);
	}
	
	public StringProperty capacidadProperty(){
		return this.capacidad;
	}
	
	public String getCapacidad(){
		return this.capacidad.get();
	}
	
	public void setCapacidad(String capacidad){
		this.capacidad.set(capacidad);
	}
	
	public StringProperty estadoProperty(){
		return this.estado;
	}
	
	public String getEstado(){
		return this.estado.get();
	}
	
	public void setEstado(String estado){
		this.estado.set(estado);
	}

}
