package com.desarrollo.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.principal.Principal;

public class ControladorSelectorTarea {

	@FXML
	private ImageView ImagenMedicion;

	@FXML
	private Button botonMedicion;

	@FXML
	private ImageView imagenAlimentacion;

	@FXML
	private Button botonLimpieza;

	@FXML
	private Button botonAlimentacion;

	@FXML
	private ImageView imagenLimpieza;

	private Principal principal;

	private Stage escenario;

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public ImageView getImagenMedicion() {
		return ImagenMedicion;
	}

	public void setImagenMedicion(ImageView imagenMedicion) {
		ImagenMedicion = imagenMedicion;
	}

	public Button getBotonMedicion() {
		return botonMedicion;
	}

	public void setBotonMedicion(Button botonMedicion) {
		this.botonMedicion = botonMedicion;
	}

	public ImageView getImagenAlimentacion() {
		return imagenAlimentacion;
	}

	public void setImagenAlimentacion(ImageView imagenAlimentacion) {
		this.imagenAlimentacion = imagenAlimentacion;
	}

	public Button getBotonLimpieza() {
		return botonLimpieza;
	}

	public void setBotonLimpieza(Button botonLimpieza) {
		this.botonLimpieza = botonLimpieza;
	}

	public Button getBotonAlimentacion() {
		return botonAlimentacion;
	}

	public void setBotonAlimentacion(Button botonAlimentacion) {
		this.botonAlimentacion = botonAlimentacion;
	}

	public ImageView getImagenLimpieza() {
		return imagenLimpieza;
	}

	public void setImagenLimpieza(ImageView imagenLimpieza) {
		this.imagenLimpieza = imagenLimpieza;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	@FXML
	void cargarAlimentacion() throws Exception {
		principal.cargarTarea(TipoRecurso.ALIMENTO, TipoVentana.NUEVO, -1);
		salir();
	}

	@FXML
	void cargarMedicion() throws Exception {
		principal.cargarTarea(TipoRecurso.MEDICION, TipoVentana.NUEVO, -1);
		salir();
	}

	@FXML
	void cargarLimpieza() throws Exception {
		principal.cargarTarea(TipoRecurso.INSUMO, TipoVentana.NUEVO, -1);
		salir();
	}

}
