package com.desarrollo.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.principal.Principal;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorPantallaPrincipal {

	@FXML
	private ImageView imagenAlertas;

	@FXML
	private ImageView toolbarImageInsumos;

	@FXML
	private ImageView imagenUsuarios;

	@FXML
	private Button botonTareas;

	@FXML
	private Button botonAccuario;

	@FXML
	private ImageView toolbarImageInformes;

	@FXML
	private Button botonInsumos;

	@FXML
	private Button botonAlimentos;

	@FXML
	private ImageView toolbarImageTareas;

	@FXML
	private ImageView imagenTareas;

	@FXML
	private ImageView toolbarImageAlertas;

	@FXML
	private ImageView imagenInsumos;

	@FXML
	private ImageView imagenAlimentos;

	@FXML
	private Button botonAlertas;

	@FXML
	private ImageView toolbarImageAcuario;

	@FXML
	private Button botonInformes;

	@FXML
	private ImageView imagenAcuario;

	@FXML
	private ImageView toolbarImageAlimentos;

	@FXML
	private ImageView imagenInformes;

	@FXML
	private ImageView toolbarImageUsuarios;

	@FXML
	private Button botonEspecies;

	@FXML
	private Button botonUsuarios;

	@FXML
	private ImageView imagenEspecies;

	@FXML
	private ImageView toolbarImageEspecies;

	@FXML
	private Button botonToolbarUsuario;

	@FXML
	private Button botonToolbarEspecie;

	@FXML
	private Button botonToolbarAcuario;

	@FXML
	private Button botonToolbarAlerta;

	@FXML
	private Button botonToolbarTarea;

	@FXML
	private Button botonToolbarInsumo;

	@FXML
	private Button botonToolbarInforme;

	@FXML
	private Button botonToolbarAlimento;

	private Principal principal;

	public Button getBotonToolbarUsuario() {
		return botonToolbarUsuario;
	}

	public void setBotonToolbarUsuario(Button botonToolbarUsuario) {
		this.botonToolbarUsuario = botonToolbarUsuario;
	}

	public Button getBotonToolbarEspecie() {
		return botonToolbarEspecie;
	}

	public void setBotonToolbarEspecie(Button botonToolbarEspecie) {
		this.botonToolbarEspecie = botonToolbarEspecie;
	}

	public Button getBotonToolbarAcuario() {
		return botonToolbarAcuario;
	}

	public void setBotonToolbarAcuario(Button botonToolbarAcuario) {
		this.botonToolbarAcuario = botonToolbarAcuario;
	}

	public Button getBotonToolbarAlerta() {
		return botonToolbarAlerta;
	}

	public void setBotonToolbarAlerta(Button botonToolbarAlerta) {
		this.botonToolbarAlerta = botonToolbarAlerta;
	}

	public Button getBotonToolbarTarea() {
		return botonToolbarTarea;
	}

	public void setBotonToolbarTarea(Button botonToolbarTarea) {
		this.botonToolbarTarea = botonToolbarTarea;
	}

	public Button getBotonToolbarInsumo() {
		return botonToolbarInsumo;
	}

	public void setBotonToolbarInsumo(Button botonToolbarInsumo) {
		this.botonToolbarInsumo = botonToolbarInsumo;
	}

	public Button getBotonToolbarInforme() {
		return botonToolbarInforme;
	}

	public void setBotonToolbarInforme(Button botonToolbarInforme) {
		this.botonToolbarInforme = botonToolbarInforme;
	}

	public Button getBotonToolbarAlimento() {
		return botonToolbarAlimento;
	}

	public void setBotonToolbarAlimento(Button botonToolbarAlimento) {
		this.botonToolbarAlimento = botonToolbarAlimento;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public ImageView getToolbarImageInformes() {
		return toolbarImageInformes;
	}

	public void setToolbarImageInformes(ImageView toolbarImageInformes) {
		this.toolbarImageInformes = toolbarImageInformes;
	}

	public ImageView getToolbarImageTareas() {
		return toolbarImageTareas;
	}

	public void setToolbarImageTareas(ImageView toolbarImageTareas) {
		this.toolbarImageTareas = toolbarImageTareas;
	}

	public ImageView getToolbarImageAlertas() {
		return toolbarImageAlertas;
	}

	public void setToolbarImageAlertas(ImageView toolbarImageAlertas) {
		this.toolbarImageAlertas = toolbarImageAlertas;
	}

	public ImageView getToolbarImageAcuario() {
		return toolbarImageAcuario;
	}

	public void setToolbarImageAcuario(ImageView toolbarImageAcuario) {
		this.toolbarImageAcuario = toolbarImageAcuario;
	}

	public ImageView getToolbarImageUsuarios() {
		return toolbarImageUsuarios;
	}

	public void setToolbarImageUsuarios(ImageView toolbarImageUsuarios) {
		this.toolbarImageUsuarios = toolbarImageUsuarios;
	}

	public ImageView getToolbarImageEspecies() {
		return toolbarImageEspecies;
	}

	public void setToolbarImageEspecies(ImageView toolbarImageEspecies) {
		this.toolbarImageEspecies = toolbarImageEspecies;
	}

	public ImageView getImagenAlertas() {
		return imagenAlertas;
	}

	public void setImagenAlertas(ImageView imagenAlertas) {
		this.imagenAlertas = imagenAlertas;
	}

	public Button getBotonAccuario() {
		return botonAccuario;
	}

	public void setBotonAccuario(Button botonAccuario) {
		this.botonAccuario = botonAccuario;
	}

	public Button getBotonTareas() {
		return botonTareas;
	}

	public void setBotonTareas(Button botonTareas) {
		this.botonTareas = botonTareas;
	}

	public ImageView getImagenUsuarios() {
		return imagenUsuarios;
	}

	public void setImagenUsuarios(ImageView imagenUsuarios) {
		this.imagenUsuarios = imagenUsuarios;
	}

	public ImageView getImagenTareas() {
		return imagenTareas;
	}

	public void setImagenTareas(ImageView imagenTareas) {
		this.imagenTareas = imagenTareas;
	}

	public Button getBotonAlertas() {
		return botonAlertas;
	}

	public void setBotonAlertas(Button botonAlertas) {
		this.botonAlertas = botonAlertas;
	}

	public ImageView getImagenAcuario() {
		return imagenAcuario;
	}

	public void setImagenAcuario(ImageView imagenAcuario) {
		this.imagenAcuario = imagenAcuario;
	}

	public Button getBotonInformes() {
		return botonInformes;
	}

	public void setBotonInformes(Button botonInformes) {
		this.botonInformes = botonInformes;
	}

	public ImageView getImagenInformes() {
		return imagenInformes;
	}

	public void setImagenInformes(ImageView imagenInformes) {
		this.imagenInformes = imagenInformes;
	}

	public Button getBotonEspecies() {
		return botonEspecies;
	}

	public void setBotonEspecies(Button botonEspecies) {
		this.botonEspecies = botonEspecies;
	}

	public Button getBotonUsuarios() {
		return botonUsuarios;
	}

	public void setBotonUsuarios(Button botonUsuarios) {
		this.botonUsuarios = botonUsuarios;
	}

	public ImageView getImagenEspecies() {
		return imagenEspecies;
	}

	public void setImagenEspecies(ImageView imagenEspecies) {
		this.imagenEspecies = imagenEspecies;
	}

	public ImageView getToolbarImageInsumos() {
		return toolbarImageInsumos;
	}

	public void setToolbarImageInsumos(ImageView toolbarImageInsumos) {
		this.toolbarImageInsumos = toolbarImageInsumos;
	}

	public Button getBotonInsumos() {
		return botonInsumos;
	}

	public void setBotonInsumos(Button botonInsumos) {
		this.botonInsumos = botonInsumos;
	}

	public Button getBotonAlimentos() {
		return botonAlimentos;
	}

	public void setBotonAlimentos(Button botonAlimentos) {
		this.botonAlimentos = botonAlimentos;
	}

	public ImageView getImagenInsumos() {
		return imagenInsumos;
	}

	public void setImagenInsumos(ImageView imagenInsumos) {
		this.imagenInsumos = imagenInsumos;
	}

	public ImageView getImagenAlimentos() {
		return imagenAlimentos;
	}

	public void setImagenAlimentos(ImageView imagenAlimentos) {
		this.imagenAlimentos = imagenAlimentos;
	}

	public ImageView getToolbarImageAlimentos() {
		return toolbarImageAlimentos;
	}

	public void setToolbarImageAlimentos(ImageView toolbarImageAlimentos) {
		this.toolbarImageAlimentos = toolbarImageAlimentos;
	}

	@FXML
	void ventanaUsuario() throws Exception {

		principal.cargarUsuarioPrincipal();
	}

	@FXML
	void venanaAlimento() throws Exception {
		principal.cargarAlimentoInsumoPrincipal(TipoRecurso.ALIMENTO);
	}

	@FXML
	void ventanaInsumo() throws Exception {
		principal.cargarAlimentoInsumoPrincipal(TipoRecurso.INSUMO);
	}

	@FXML
	void ventanaEspecie() throws Exception {
		principal.cargarEspeciePrincipal();
	}

	@FXML
	void salir() {
		principal.getPrimaryStage().close();
		HibernateUtil.getSessionFactory().close();
	}

	@FXML
	void venanaAcuario() throws Exception {
		principal.cargarAcuarioPrincipal();
	}

	@FXML
	void ventanaTarea() throws Exception {
		principal.cargarTareaPrincipal();
	}

	@FXML
	void ventanaLoggin() throws Exception {
		principal.setUsuarioActivo(null);
		principal.cargarVentanaLogin();
	}

	@FXML
	void ventanaReportes() throws Exception {
		principal.cargarPantallaReporte();
	}

	@FXML
	void acercaDe() {
		try {
			principal.cargarAcercaDe();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/Pantallaprincipal.html");
	}

}