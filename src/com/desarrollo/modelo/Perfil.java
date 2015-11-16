package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERFIL")
public class Perfil {
	private int idPerfil;
	private String nombre;
	private String descripcion;
	private List<Permiso> permisos = new ArrayList<Permiso>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();

	
	public Perfil(String nombre, String descripcion){
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public Perfil(){
		
	}
	
	@Id
	@GeneratedValue
	public int getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(int idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@ManyToMany
	@JoinTable(name = "PERFIL_PERMISO", joinColumns = { @JoinColumn(name = "idPerfil") }, inverseJoinColumns = { @JoinColumn(name = "idPermiso") })
	public List<Permiso> getPermisos() {
		return permisos;
	}

	public void setPermisos(List<Permiso> permisos) {
		this.permisos = permisos;
	}

	@ManyToMany
	@JoinTable(name = "USUARIO_PERFIL", joinColumns = { @JoinColumn(name = "idPerfil") }, inverseJoinColumns = { @JoinColumn(name = "idUsuario") })
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public void agregarPermiso(Permiso permiso) {
		if (!existePermiso(permiso)) {
			permisos.add(permiso);
		}
	}

	public Boolean existePermiso(Permiso permiso) {
		Boolean btmp = false;
		for (int i = 0; i < permisos.size(); i++) {
			if (permisos.get(i).getIdPermiso() == permiso.getIdPermiso()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void quitarPermiso(Permiso permiso) {
		Integer tmp = null;

		if (existePermiso(permiso)) {
			for (int i = 0; i < permisos.size(); i++) {
				if (permisos.get(i).getIdPermiso() == permiso.getIdPermiso()) {
					tmp = i;
				}
			}
		}

		if (tmp != null) {
			permisos.remove(tmp);
		}
	}

}
