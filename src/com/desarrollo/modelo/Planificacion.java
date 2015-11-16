package com.desarrollo.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * Clase Planificacion
 *
 */
public class Planificacion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1465453714298618101L;
	private ArrayList<Tarea> tareas;

	// Constructor
	public Planificacion() {
		super();
		this.tareas = new ArrayList<Tarea>();

	}

	public Planificacion(ArrayList<Tarea> tareas) {
		super();
		this.tareas = tareas;
	}

	// Getters & Setters
	public ArrayList<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(ArrayList<Tarea> tareas) {
		this.tareas = tareas;
	}

	// Metodos

	public Boolean tareaExiste(Integer idTarea) {
		Boolean btmp = false;
		for (int i = 0; i < tareas.size(); i++) {
			if (tareas.get(i).getIdTarea() == idTarea) {
				btmp = true;
			}
		}
		return btmp;

	}

	public void asentarActividad(Integer idActividad, String observaciones) {

		if (tareaExiste(idActividad)) {

		}
	}

	public void actualizarPlanificacion() {

	}

	public void buscarTarea(Integer idTarea) {

	}

	public void asentarMedicion(Integer idMedicion, float valorMedido,
			String observaciones) {

	}

	public void asentarTarea(Integer idTarea, String observaciones) {

	}

	public void mostrarTareas(Integer idAcuario, Date fechaInicio, Date fechaFin) {

	}

	public void agregarActividad(Integer idAcuario, Date fechaLimite,
			Date horaLimite, String tipoActividad) {

	}

	public void agregarRecursoEnActividad(Integer idActividad,
			String nombreRecurso, float cantidad) {

	}

	public void agregarIndicacionesEnTarea(Integer idTarea, String indicaciones) {
		
		if (tareaExiste(idTarea)){
			for (int i = 0; i < tareas.size();i++){
				if (tareas.get(i).getIdTarea()== idTarea){
					tareas.get(i).setIndicaciones(indicaciones);
				}
			}
		}
		
		
		
	}

	public void agregarMedicion(Integer idAcuario, Date fechaLimite,
			Date horaLimite, float parametroMedido) {

	}

}
