package com.desarrollo.modelo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ALERTA")
public class Alerta {
	private int IdAlerta;
	private Date fechaAlerta;
	private String tipoAlerta;
	private String Descripcion;
	private Acuario acuario;

	@Id
	@GeneratedValue
	public int getIdAlerta() {
		return IdAlerta;
	}

	public void setIdAlerta(int idAlerta) {
		IdAlerta = idAlerta;
	}

	public Date getFechaAlerta() {
		return fechaAlerta;
	}

	public void setFechaAlerta(Date fechaAlerta) {
		this.fechaAlerta = fechaAlerta;
	}

	public String getTipoAlerta() {
		return tipoAlerta;
	}

	public void setTipoAlerta(String tipoAlerta) {
		this.tipoAlerta = tipoAlerta;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	
	@ManyToOne
	@JoinColumn(name="idAcuario")
	public Acuario getAcuario() {
		return acuario;
	}

	public void setAcuario(Acuario acuario) {
		this.acuario = acuario;
	}

}
