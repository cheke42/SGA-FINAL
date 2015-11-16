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
@Table(name = "USUARIO")
public class Usuario {
	private int idUsuario;
	private String nombre;
	private String password;
	private boolean estado;
	private List<Perfil> perfiles = new ArrayList<Perfil>();
	private List<Tarea> tareas;
	private List<Alimentacion> alimentacion;
	private List<Limpieza> limpiezas;
	private List<Medicion> mediciones;

	@Id
	@GeneratedValue
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = { @JoinColumn(name = "idUsuario") }, inverseJoinColumns = { @JoinColumn(name = "idPerfil") })
	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	@OneToMany(targetEntity = Tarea.class, mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	@OneToMany(targetEntity = Alimentacion.class, mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Alimentacion> getAlimentacion() {
		return alimentacion;
	}

	public void setAlimentacion(List<Alimentacion> alimentacion) {
		this.alimentacion = alimentacion;
	}

	@OneToMany(targetEntity = Limpieza.class, mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Limpieza> getLimpiezas() {
		return limpiezas;
	}

	public void setLimpiezas(List<Limpieza> limpiezas) {
		this.limpiezas = limpiezas;
	}

	@OneToMany(targetEntity = Medicion.class, mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Medicion> getMediciones() {
		return mediciones;
	}

	public void setMediciones(List<Medicion> mediciones) {
		this.mediciones = mediciones;
	}

	// Verifica si existe en la lista de perfiles el perfil pasado por parametro
	public Boolean existePerfil(Perfil perfil) {
		Boolean btmp = false;

		for (int i = 0; i < this.perfiles.size(); i++) {
			if (perfiles.get(i).getIdPerfil() == perfil.getIdPerfil()) {
				btmp = true;
			}

		}
		return btmp;
	}

	// Agregar un perfil a la lista de perfiles siempre y cuando no exista.
	public void agregarPerfil(Perfil perfil) {
		if (!existePerfil(perfil)) {
			perfiles.add(perfil);
		}
	}

	// Quitar un perfil de la lista de perfiles siempre y cuando exista.
	public void quitarPerfil(Perfil perfil) {
		Integer itmp = null;
		if (existePerfil(perfil)) {
			for (int i = 0; i < perfiles.size(); i++) {
				if (perfiles.get(i).getIdPerfil() == perfil.getIdPerfil()) {
					itmp = i;
				}
			}
		}

		if (itmp != null) {
			perfiles.remove(itmp);
		}

	}

	// Desactivar usuario.
	public void darBaja() {
		this.setEstado(false);
	}

	// Activar usuario.
	public void reactivar() {
		this.setEstado(true);
	}

	// Validar datos de usuario
	public Boolean validarDatos(Usuario usuario) {
		Boolean btmp = false;
		if ((this.getNombre() == usuario.getNombre()) & (this.getPassword() == usuario.getPassword())) {
			btmp = true;
		}
		return btmp;
	}

	// Cambiar la contrase�a actual por una nueva
	public void cambiarPassword(String passwordAnterior, String passwordNueva) {

		if (this.password == passwordAnterior) {
			this.password = passwordNueva;
		}

	}

}
