package com.desarrollo.principal;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import org.hibernate.Session;

import com.desarrollo.controladores.ControladorAcercaDe;
import com.desarrollo.controladores.ControladorAcuario;
import com.desarrollo.controladores.ControladorAgrupacionEspecie;
import com.desarrollo.controladores.ControladorAlimentoInsumo;
import com.desarrollo.controladores.ControladorEspecie;
import com.desarrollo.controladores.ControladorPantallaAcuario;
import com.desarrollo.controladores.ControladorPantallaAlimentoInsumo;
import com.desarrollo.controladores.ControladorPantallaEspecie;
import com.desarrollo.controladores.ControladorPantallaPrincipal;
import com.desarrollo.controladores.ControladorPantallaTarea;
import com.desarrollo.controladores.ControladorPaquete;
import com.desarrollo.controladores.ControladorReporte;
import com.desarrollo.controladores.ControladorSelectorTarea;
import com.desarrollo.controladores.ControladorTarea;
import com.desarrollo.controladores.ControladorTransferirEspecie;
import com.desarrollo.controladores.ControladorUsuario;
import com.desarrollo.controladores.ControladorUsuarioPrincipal;
import com.desarrollo.controladores.Controladorloggin;
import com.desarrollo.enumerados.TipoAgua;
import com.desarrollo.enumerados.TipoPerfil;
import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoReportes;
import com.desarrollo.enumerados.TipoUnidad;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Acuario;
import com.desarrollo.modelo.AgrupacionEspecie;
import com.desarrollo.modelo.Alimentacion;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Insumo;
import com.desarrollo.modelo.Limpieza;
import com.desarrollo.modelo.Medicion;
import com.desarrollo.modelo.PaqueteAlimento;
import com.desarrollo.modelo.PaqueteInsumo;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.modelo.Usuario;
import com.desarrollo.property.AcuarioProperty;
import com.desarrollo.property.AlimentoInsumoProperty;
import com.desarrollo.property.EspecieProperty;
import com.desarrollo.property.PaqueteProperty;
import com.desarrollo.property.TareaProperty;
import com.desarrollo.property.UsuarioProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class Principal extends Application {

	private Stage primaryStage;
	private AnchorPane layoutPrincipal;
	private Image imagenLogo = new Image("file:recursos/imagenes/imagen1.jpg");
	public static Usuario usuarioActivo;

	private ObservableList<UsuarioProperty> usuariosObservables = FXCollections.observableArrayList();

	private ObservableList<EspecieProperty> especiesObservables = FXCollections.observableArrayList();

	private ObservableList<AlimentoInsumoProperty> alimentoInsumoObservables = FXCollections.observableArrayList();

	private ObservableList<PaqueteProperty> PaqueteObservables = FXCollections.observableArrayList();

	private ObservableList<AcuarioProperty> acuariosObservables = FXCollections.observableArrayList();

	private ObservableList<TareaProperty> tareasObservables = FXCollections.observableArrayList();

	public static void main(String[] args) {

		HibernateUtil.getSessionFactory();
		HibernateUtil.traerDatosBase();
		Sistema.getSistema();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		this.primaryStage.setTitle(Sistema.getSistema().getNombre());
		this.primaryStage.getIcons().add(Sistema.getSistema().getFavicon());
		cargarVentanaLogin();
		primaryStage.setOnHiding(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				HibernateUtil.getSessionFactory().close();
			}
		});

	}

	public ObservableList<TareaProperty> getTareasObservables() {
		return tareasObservables;
	}

	public void setTareasObservables(ObservableList<TareaProperty> tareasObservables) {
		this.tareasObservables = tareasObservables;
	}

	public ObservableList<AcuarioProperty> getAcuariosObservables() {
		return acuariosObservables;
	}

	public void setAcuariosObservables(ObservableList<AcuarioProperty> acuariosObservables) {
		this.acuariosObservables = acuariosObservables;
	}

	public ObservableList<PaqueteProperty> getPaqueteObservables() {
		return PaqueteObservables;
	}

	public void setPaqueteObservables(ObservableList<PaqueteProperty> paqueteObservables) {
		PaqueteObservables = paqueteObservables;
	}

	public ObservableList<AlimentoInsumoProperty> getAlimentoInsumoObservables() {
		return alimentoInsumoObservables;
	}

	public void setAlimentoInsumoObservables(ObservableList<AlimentoInsumoProperty> alimentoInsumoObservables) {
		this.alimentoInsumoObservables = alimentoInsumoObservables;
	}

	public ObservableList<EspecieProperty> getEspeciesObservables() {
		return especiesObservables;
	}

	public void setEspeciesObservables(ObservableList<EspecieProperty> especiesObservables) {
		this.especiesObservables = especiesObservables;
	}

	public AnchorPane getLayoutPrincipal() {
		return layoutPrincipal;
	}

	public void setLayoutPrincipal(AnchorPane layoutPrincipal) {
		this.layoutPrincipal = layoutPrincipal;
	}

	public Image getImagenLogo() {
		return imagenLogo;
	}

	public void setImagenLogo(Image imagenLogo) {
		this.imagenLogo = imagenLogo;
	}

	public Usuario getUsuarioActivo() {
		return usuarioActivo;
	}

	public void setUsuarioActivo(Usuario usuarioActivo) {
		Principal.usuarioActivo = usuarioActivo;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public ObservableList<UsuarioProperty> getUsuariosObservables() {
		return usuariosObservables;
	}

	public void setUsuariosObservables(ObservableList<UsuarioProperty> usuariosObservables) {
		this.usuariosObservables = usuariosObservables;
	}

	public void cargarVentanaLogin() throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/loggin.fxml"));
		layoutPrincipal = loader.load();
		Scene escena = new Scene(layoutPrincipal);
		primaryStage.setScene(escena);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		Controladorloggin controladorPrincipal = loader.getController();
		controladorPrincipal.getImagenLogo().setImage(this.imagenLogo);
		controladorPrincipal.setPrincipal(this);
		controladorPrincipal.getFieldUsuario().focusedProperty();
		primaryStage.show();

	}

	public void cargarPantallaPrincial() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaPrincipal.fxml"));
		layoutPrincipal = loader.load();
		Scene escena = new Scene(layoutPrincipal);
		primaryStage.setScene(escena);
		primaryStage.setResizable(false);
		primaryStage.sizeToScene();
		ControladorPantallaPrincipal controladorPantallaPrincipal = loader.getController();

		controladorPantallaPrincipal.getImagenAcuario().setImage(
				new Image("file:recursos/imagenes/acuarioBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageAcuario().setImage(
				new Image("file:recursos/imagenes/acuarioBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarAcuario().setTooltip(new Tooltip("Gestión de Acuario"));
		controladorPantallaPrincipal.getBotonToolbarAcuario().setDisable(true);

		controladorPantallaPrincipal.getImagenEspecies().setImage(
				new Image("file:recursos/imagenes/especieBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageEspecies().setImage(
				new Image("file:recursos/imagenes/especieBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarEspecie().setTooltip(new Tooltip("Gestión de Especies"));
		controladorPantallaPrincipal.getBotonToolbarEspecie().setDisable(true);

		controladorPantallaPrincipal.getImagenInformes().setImage(
				new Image("file:recursos/imagenes/informeBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageInformes().setImage(
				new Image("file:recursos/imagenes/informeBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarInforme().setTooltip(new Tooltip("Gestión de Informes"));
		controladorPantallaPrincipal.getBotonToolbarInforme().setDisable(true);

		controladorPantallaPrincipal.getImagenTareas().setImage(
				new Image("file:recursos/imagenes/tareaBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageTareas().setImage(
				new Image("file:recursos/imagenes/tareaBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarTarea().setTooltip(new Tooltip("Gestión de Tareas"));
		controladorPantallaPrincipal.getBotonToolbarTarea().setDisable(true);

		controladorPantallaPrincipal.getImagenUsuarios().setImage(
				new Image("file:recursos/imagenes/usuarioBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageUsuarios().setImage(
				new Image("file:recursos/imagenes/usuarioBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarUsuario().setTooltip(new Tooltip("Gestión de Usuarios"));
		controladorPantallaPrincipal.getBotonToolbarUsuario().setDisable(true);

		controladorPantallaPrincipal.getToolbarImageInsumos().setImage(
				new Image("file:recursos/imagenes/InsumoBlancoNegro.png"));
		controladorPantallaPrincipal.getImagenInsumos().setImage(
				new Image("file:recursos/imagenes/InsumoBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarInsumo().setTooltip(new Tooltip("Gestión de Insumos"));
		controladorPantallaPrincipal.getBotonToolbarInsumo().setDisable(true);

		controladorPantallaPrincipal.getImagenAlimentos().setImage(
				new Image("file:recursos/imagenes/AlimentoBlancoNegro.png"));
		controladorPantallaPrincipal.getToolbarImageAlimentos().setImage(
				new Image("file:recursos/imagenes/AlimentoBlancoNegro.png"));
		controladorPantallaPrincipal.getBotonToolbarAlimento().setTooltip(new Tooltip("Gestión de Alimentos"));
		controladorPantallaPrincipal.getBotonToolbarAlimento().setDisable(true);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario usuario = (Usuario) session.get(Usuario.class,
				Sistema.getSistema().buscarUsuario(usuarioActivo.getNombre()).getIdUsuario());

		for (int i = 0; i < usuario.getPerfiles().size(); i++) {
			if (usuario.getPerfiles().get(i).getNombre().equals(TipoPerfil.ADMINISTRADOR.toString())) {

				controladorPantallaPrincipal.getImagenUsuarios().setImage(
						new Image("file:recursos/imagenes/usuario.png"));
				controladorPantallaPrincipal.getToolbarImageUsuarios().setImage(
						new Image("file:recursos/imagenes/usuario.png"));
				controladorPantallaPrincipal.getBotonToolbarUsuario().setDisable(false);
				controladorPantallaPrincipal.getBotonUsuarios().setDisable(false);
			}

			if (usuario.getPerfiles().get(i).getNombre().equals(TipoPerfil.ESPECIALISTA.toString())) {

				controladorPantallaPrincipal.getImagenAcuario().setImage(
						new Image("file:recursos/imagenes/acuario.png"));
				controladorPantallaPrincipal.getToolbarImageAcuario().setImage(
						new Image("file:recursos/imagenes/acuario.png"));
				controladorPantallaPrincipal.getBotonToolbarAcuario().setTooltip(new Tooltip("Gestión de Acuario"));
				controladorPantallaPrincipal.getBotonToolbarAcuario().setDisable(false);
				controladorPantallaPrincipal.getBotonAccuario().setDisable(false);

				controladorPantallaPrincipal.getImagenEspecies().setImage(
						new Image("file:recursos/imagenes/especie.png"));
				controladorPantallaPrincipal.getToolbarImageEspecies().setImage(
						new Image("file:recursos/imagenes/especie.png"));
				controladorPantallaPrincipal.getBotonToolbarEspecie().setTooltip(new Tooltip("Gestión de Especies"));
				controladorPantallaPrincipal.getBotonToolbarEspecie().setDisable(false);
				controladorPantallaPrincipal.getBotonEspecies().setDisable(false);

				controladorPantallaPrincipal.getImagenInformes().setImage(
						new Image("file:recursos/imagenes/informe.png"));
				controladorPantallaPrincipal.getToolbarImageInformes().setImage(
						new Image("file:recursos/imagenes/informe.png"));
				controladorPantallaPrincipal.getBotonToolbarInforme().setTooltip(new Tooltip("Gestión de Informes"));
				controladorPantallaPrincipal.getBotonToolbarInforme().setDisable(false);
				controladorPantallaPrincipal.getBotonInformes().setDisable(false);

				controladorPantallaPrincipal.getImagenTareas().setImage(new Image("file:recursos/imagenes/tarea.png"));
				controladorPantallaPrincipal.getToolbarImageTareas().setImage(
						new Image("file:recursos/imagenes/tarea.png"));
				controladorPantallaPrincipal.getBotonToolbarTarea().setTooltip(new Tooltip("Gestión de Tareas"));
				controladorPantallaPrincipal.getBotonToolbarTarea().setDisable(false);
				controladorPantallaPrincipal.getBotonTareas().setDisable(false);

				controladorPantallaPrincipal.getToolbarImageInsumos().setImage(
						new Image("file:recursos/imagenes/Insumo.png"));
				controladorPantallaPrincipal.getImagenInsumos()
						.setImage(new Image("file:recursos/imagenes/Insumo.png"));
				controladorPantallaPrincipal.getBotonToolbarInsumo().setTooltip(new Tooltip("Gestión de Insumos"));
				controladorPantallaPrincipal.getBotonToolbarInsumo().setDisable(false);
				controladorPantallaPrincipal.getBotonInsumos().setDisable(false);

				controladorPantallaPrincipal.getImagenAlimentos().setImage(
						new Image("file:recursos/imagenes/Alimento.png"));
				controladorPantallaPrincipal.getToolbarImageAlimentos().setImage(
						new Image("file:recursos/imagenes/Alimento.png"));
				controladorPantallaPrincipal.getBotonToolbarAlimento().setTooltip(new Tooltip("Gestión de Alimentos"));
				controladorPantallaPrincipal.getBotonToolbarAlimento().setDisable(false);
				controladorPantallaPrincipal.getBotonAlimentos().setDisable(false);

			}

			if (usuario.getPerfiles().get(i).getNombre().equals(TipoPerfil.MEDIDOR.toString())) {

				controladorPantallaPrincipal.getImagenTareas().setImage(new Image("file:recursos/imagenes/tarea.png"));
				controladorPantallaPrincipal.getToolbarImageTareas().setImage(
						new Image("file:recursos/imagenes/tarea.png"));
				controladorPantallaPrincipal.getBotonToolbarTarea().setTooltip(new Tooltip("Gestión de Tareas"));
				controladorPantallaPrincipal.getBotonToolbarTarea().setDisable(false);
				controladorPantallaPrincipal.getBotonTareas().setDisable(false);

			}
		}

		session.getTransaction().commit();

		controladorPantallaPrincipal.setPrincipal(this);

	}

	public void cargarUsuarioPrincipal() throws Exception {

		usuariosObservables = Sistema.getSistema().pasarArrayUsuarioAProperty(Sistema.getSistema().getUsuarios());

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/usuarioPrincipal.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();

		Stage escenarioUsuario = new Stage();

		escenarioUsuario.setResizable(false);
		escenarioUsuario.sizeToScene();
		escenarioUsuario.setTitle("Usuarios del Sistema");
		escenarioUsuario.getIcons().add(new Image("file:recursos/imagenes/usuario.png"));
		escenarioUsuario.initModality(Modality.WINDOW_MODAL);
		escenarioUsuario.initOwner(primaryStage);
		Scene escena = new Scene(anchoUsuarios);
		escenarioUsuario.setScene(escena);

		ControladorUsuarioPrincipal controladorUsuarioPrincipal = loader.getController();
		controladorUsuarioPrincipal.setEscenarioUsuario(escenarioUsuario);
		controladorUsuarioPrincipal.setPrincipal(this);
		controladorUsuarioPrincipal.getItemToolbarBorrarImagen().setImage(
				new Image("file:recursos/imagenes/borrarUsuario.png"));
		controladorUsuarioPrincipal.getItemToolbarEditarImagen().setImage(
				new Image("file:recursos/imagenes/editarUsuario.png"));
		controladorUsuarioPrincipal.getItemToolbarNuevoImagen().setImage(
				new Image("file:recursos/imagenes/nuevoUsuario.png"));
		controladorUsuarioPrincipal.getItemToolbarSalirImagen().setImage(new Image("file:recursos/imagenes/salir.png"));
		controladorUsuarioPrincipal.getItemToolbarBorrar().setTooltip(new Tooltip("Borrar usuario seleccionado"));
		controladorUsuarioPrincipal.getItemToolbarEditar().setTooltip(new Tooltip("Editar usuario seleccionado"));
		controladorUsuarioPrincipal.getItemToolbarNuevo().setTooltip(new Tooltip("Agregar nuevo usuario"));
		controladorUsuarioPrincipal.getItemToolbarSalir().setTooltip(new Tooltip("Salir de gestión de usuario"));
		escenarioUsuario.showAndWait();

	}

	public void cargarUsuario(TipoVentana tipoVentana, TableView<UsuarioProperty> tablaUsuario, int idUsuario)
			throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/usuario.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();
		Stage escenarioUsuario = new Stage();
		escenarioUsuario.setResizable(false);
		escenarioUsuario.sizeToScene();

		String ventana = "";
		Scene escena = new Scene(anchoUsuarios);
		escenarioUsuario.setScene(escena);
		ControladorUsuario controladorUsuario = loader.getController();

		if (tipoVentana == TipoVentana.NUEVO) {
			ventana = "Nuevo";
		} else {
			ventana = "Editar";
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Usuario us = (Usuario) session.get(Usuario.class, idUsuario);
			controladorUsuario.getFieldNombre().setEditable(false);
			controladorUsuario.getFieldNombre().setText(us.getNombre());
			controladorUsuario.getFieldpassword().setText(us.getPassword());
			controladorUsuario.getCheckboxEstadoUsuario().setSelected(us.getEstado());
			for (int i = 0; i < us.getPerfiles().size(); i++) {
				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.ADMINISTRADOR.toString())) {
					controladorUsuario.getCheckboxAdministrador().setSelected(true);
				}

				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.MEDIDOR.toString())) {
					controladorUsuario.getCheckboxMedidor().setSelected(true);
				}

				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.ESPECIALISTA.toString())) {
					controladorUsuario.getCheckBoxEspecialista().setSelected(true);
				}

			}
			session.getTransaction().commit();

		}
		escenarioUsuario.setTitle(ventana + " Usuario");
		escenarioUsuario.getIcons().add(new Image("file:recursos/imagenes/" + ventana + "Usuario.png"));

		controladorUsuario.setIdUsuario(idUsuario);
		controladorUsuario.setEscenarioUsuario(escenarioUsuario);
		controladorUsuario.setPrincipal(this);
		controladorUsuario.setTablaUsuario(tablaUsuario);
		controladorUsuario.setTipoVentana(tipoVentana);
		escenarioUsuario.showAndWait();
	}

	public void cargarAlimentoInsumoPrincipal(TipoRecurso tipoRecurso) throws Exception {
		alimentoInsumoObservables.clear();
		String recurso = "Insumo";
		if (tipoRecurso == TipoRecurso.ALIMENTO) {
			alimentoInsumoObservables.setAll(Sistema.getSistema().pasarArrayAlimentosAProperty(
					Sistema.getSistema().getAlimentos()));
			recurso = "Alimento";
		} else if (tipoRecurso == TipoRecurso.INSUMO) {
			alimentoInsumoObservables.setAll(Sistema.getSistema().pasarArrayInsumosAProperty(
					Sistema.getSistema().getInsumos()));
		}

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaAlimentoInsumo.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();
		Stage escenarioAlimentoInsumo = new Stage();
		escenarioAlimentoInsumo.setResizable(false);
		escenarioAlimentoInsumo.sizeToScene();
		Scene escena = new Scene(anchoUsuarios);
		escenarioAlimentoInsumo.setScene(escena);

		ControladorPantallaAlimentoInsumo controlAlimEsp = loader.getController();

		escenarioAlimentoInsumo.setTitle(recurso + "s del Sistema");
		escenarioAlimentoInsumo.getIcons().add(new Image("file:recursos/imagenes/" + recurso + ".png"));
		controlAlimEsp.getImagenToolbarBorrar().setImage(new Image("file:recursos/imagenes/Borrar" + recurso + ".png"));
		controlAlimEsp.getImagenToolbarEditar().setImage(new Image("file:recursos/imagenes/Editar" + recurso + ".png"));
		controlAlimEsp.getImagenToolbarNuevo().setImage(new Image("file:recursos/imagenes/Nuevo" + recurso + ".png"));
		controlAlimEsp.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));
		controlAlimEsp.getImagenToolbarVer().setImage(new Image("file:recursos/imagenes/Ver" + recurso + ".png"));
		controlAlimEsp.setTipoRecurso(tipoRecurso);
		controlAlimEsp.setPrincipal(this);
		controlAlimEsp.setEscenario(escenarioAlimentoInsumo);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario us = (Usuario) session.get(Usuario.class, Sistema.getSistema().buscarUsuario(usuarioActivo.getNombre())
				.getIdUsuario());

		Boolean especialista = false;

		for (int i = 0; i < us.getPerfiles().size(); i++) {
			if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.ESPECIALISTA.toString())) {
				especialista = true;
			}
		}

		if (!especialista) {
			for (int i = 0; i < us.getPerfiles().size(); i++) {
				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.MEDIDOR.toString())) {
					controlAlimEsp.getBotonBorrar().setVisible(false);
					controlAlimEsp.getBotonNuevo().setVisible(false);
					controlAlimEsp.getBotonEditar().setLayoutX(controlAlimEsp.getBotonBorrar().getLayoutX());
					controlAlimEsp.getToolBar().getItems().remove(1);
					controlAlimEsp.getToolBar().getItems().remove(2);

				}
			}
		}

		session.getTransaction().commit();

		escenarioAlimentoInsumo.showAndWait();

	}

	public void cargarAlimentoInsumo(TipoRecurso tipoRecurso, TipoVentana tipoVentana,
			TableView<AlimentoInsumoProperty> tabllaAlimentoInsumo, int id) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/alimento-insumo.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();
		Stage escenarioAlimentoInsumo = new Stage();
		escenarioAlimentoInsumo.setResizable(false);
		escenarioAlimentoInsumo.sizeToScene();
		Scene escena = new Scene(anchoUsuarios);
		escenarioAlimentoInsumo.setScene(escena);
		String recurso = "";
		String ventana = "";

		if (tipoRecurso == TipoRecurso.ALIMENTO) {
			recurso = "Alimento";
		} else {
			recurso = "Insumo";
		}

		ControladorAlimentoInsumo contAliInsu = loader.getController();

		switch (tipoVentana.toString()) {
		case "NUEVO":
			contAliInsu.getFieldCantidadDisponible().setEditable(false);
			contAliInsu.getToolbar().setVisible(false);
			contAliInsu.getBotonBorrarPaquete().setVisible(false);
			contAliInsu.getBotonEditarPaquete().setVisible(false);
			contAliInsu.getBotonNuevoPaquete().setVisible(false);
			contAliInsu.getBotonVerPaquete().setVisible(false);
			contAliInsu.getFieldCantidadDisponible().setText("0");
			ventana = "Nuevo";
			contAliInsu.getComboEstadoMaterial().getItems().add(TipoUnidad.LIQUIDO.toString());
			contAliInsu.getComboEstadoMaterial().getItems().add(TipoUnidad.SOLIDO.toString());
			contAliInsu.getComboEstadoMaterial().getItems().add(TipoUnidad.GASEOSO.toString());
			break;

		case "EDITAR":
			contAliInsu.getFieldCantidadDisponible().setEditable(false);
			ventana = "Editar";
			contAliInsu.getImagenToolbarBorrarPaquete().setImage(
					new Image("file:recursos/imagenes/BorrarPaquete" + recurso + ".png"));
			contAliInsu.getImagenToolbarEditarPaquete().setImage(
					new Image("file:recursos/imagenes/EditarPaquete" + recurso + ".png"));
			contAliInsu.getImagenToolbarNuevoPaquete().setImage(
					new Image("file:recursos/imagenes/NuevoPaquete" + recurso + ".png"));
			contAliInsu.getImagenToolbarVerPaquete().setImage(
					new Image("file:recursos/imagenes/VerPaquete" + recurso + ".png"));
			contAliInsu.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));

			break;

		case "VER":
			contAliInsu.getToolbar().setVisible(false);
			contAliInsu.getFieldCantidadDisponible().setEditable(false);
			contAliInsu.getFieldCantidadMaxima().setEditable(false);
			contAliInsu.getFieldCantidadMinima().setEditable(false);
			contAliInsu.getFieldNombre().setEditable(false);
			contAliInsu.getBotonBorrarPaquete().setVisible(false);
			contAliInsu.getBotonEditarPaquete().setVisible(false);
			contAliInsu.getBotonGuardar().setVisible(false);
			contAliInsu.getBotonNuevoPaquete().setVisible(false);
			ventana = "Ver";
			break;
		}

		if (tipoVentana != TipoVentana.NUEVO) {
			contAliInsu.getFieldCantidadMinima().setEditable(false);
			contAliInsu.getFieldCantidadMaxima().setEditable(false);
			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Alimento alim = (Alimento) session.get(Alimento.class, id);
				if (alim.getStockAlimento().size() > 0) {
					contAliInsu.getListaPaqueteAlimento().clear();
					contAliInsu.setListaPaqueteAlimento(alim.getStockAlimento());
					PaqueteObservables = Sistema.getSistema().pasarArrayPaqueteAlimento(
							contAliInsu.getListaPaqueteAlimento());
				}

				contAliInsu.getComboEstadoMaterial().getItems().clear();
				contAliInsu.getComboEstadoMaterial().setPromptText(alim.getEstadoMaterial());
				contAliInsu.getComboTipoUnidad().getItems().clear();
				contAliInsu.getComboTipoUnidad().setPromptText(alim.getUnidadMedida());

				session.getTransaction().commit();
				contAliInsu.getFieldNombre().setText(alim.getNombre());
				contAliInsu.getFieldCantidadDisponible().setText(Float.toString(alim.getCantidadDisponible()));
				contAliInsu.getFieldCantidadMinima().setText(Float.toString(alim.getCantidadMinima()));
				contAliInsu.getFieldCantidadMaxima().setText(Float.toString(alim.getCantidadMaxima()));

			} else if (tipoRecurso == TipoRecurso.INSUMO) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Insumo ins = (Insumo) session.get(Insumo.class, id);
				if (ins.getStockInsumos().size() > 0) {
					contAliInsu.getListaPaqueteInsumo().clear();
					contAliInsu.setListaPaqueteInsumo(ins.getStockInsumos());
					PaqueteObservables = Sistema.getSistema().pasarArrayPaqueteInsumo(
							contAliInsu.getListaPaqueteInsumo());
				}

				contAliInsu.getComboEstadoMaterial().getItems().clear();
				contAliInsu.getComboEstadoMaterial().setPromptText(ins.getEstadoMaterial());
				contAliInsu.getComboTipoUnidad().getItems().clear();
				contAliInsu.getComboTipoUnidad().setPromptText(ins.getUnidadMedida());
				contAliInsu.getFieldNombre().setText(ins.getNombre());
				contAliInsu.getFieldCantidadDisponible().setText(Float.toString(ins.getCantidadDisponible()));
				contAliInsu.getFieldCantidadMinima().setText(Float.toString(ins.getCantidadMinima()));
				contAliInsu.getFieldCantidadMaxima().setText(Float.toString(ins.getCantidadMaxima()));
				session.getTransaction().commit();
			}

			contAliInsu.getLabelUnidadCantidadMaxima().setVisible(true);
			contAliInsu.getLabelUnidadCantidadMinima().setVisible(true);
			contAliInsu.getLabelUnidadCantidadMaxima().setText(
					Utilidades.miniUnidad.get(contAliInsu.getComboTipoUnidad().getPromptText()));
			contAliInsu.getLabelUnidadCantidadMinima().setText(
					Utilidades.miniUnidad.get(contAliInsu.getComboTipoUnidad().getPromptText()));

		}

		contAliInsu.getImagenInsumoPaquete()
				.setImage(new Image("file:recursos/imagenes/" + ventana + recurso + ".png"));
		escenarioAlimentoInsumo.setTitle(ventana + " " + recurso);
		escenarioAlimentoInsumo.getIcons().add(new Image("file:recursos/imagenes/" + ventana + recurso + ".png"));
		contAliInsu.setTipoVentana(tipoVentana);
		contAliInsu.setPrincipal(this);
		contAliInsu.setEscenario(escenarioAlimentoInsumo);
		contAliInsu.setTipoRecurso(tipoRecurso);
		contAliInsu.setId(id);
		contAliInsu.setTablaAlimentoInsumo(tabllaAlimentoInsumo);
		escenarioAlimentoInsumo.showAndWait();
	}

	@SuppressWarnings("deprecation")
	public void cargarPaquete(TableView<PaqueteProperty> tablaPaquete, TipoRecurso tipoRecurso,
			TipoVentana tipoVentana, List<PaqueteAlimento> listaPaqueteAlimento,
			List<PaqueteInsumo> listaPaqueteInsumo, int indiceSeleccionado, float cantidadMaxima, String unidad)
			throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/paquete.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();
		Stage escenarioPaquete = new Stage();
		escenarioPaquete.setResizable(false);
		escenarioPaquete.sizeToScene();
		Scene escena = new Scene(anchoUsuarios);
		ControladorPaquete controladorPaquete = loader.getController();
		escenarioPaquete.setScene(escena);
		controladorPaquete.setCantidadMaxima(cantidadMaxima);
		String recurso = "Insumo";
		String ventana = "Nuevo";
		controladorPaquete.getLabelUnidad().setText(unidad);

		if (tipoRecurso == TipoRecurso.ALIMENTO) {
			recurso = "Alimento";
		}
		if (tipoVentana == TipoVentana.EDITAR) {
			ventana = "Editar";
		}
		escenarioPaquete.setTitle(ventana + " Paquete " + recurso);
		escenarioPaquete.getIcons().add(new Image("file:recursos/imagenes/" + ventana + "Paquete" + recurso + ".png"));

		if (tipoVentana != TipoVentana.NUEVO) {
			controladorPaquete.getFieldFechaAdquisicion().getEditor().setEditable(false);
			controladorPaquete.getFieldFechaVencimiento().getEditor().setEditable(false);
			if (tipoRecurso == TipoRecurso.INSUMO) {
				if (Integer.parseInt(tablaPaquete.getItems().get(indiceSeleccionado).getIdPaquete()) == 0) {

					int diaAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getDate();
					int mesAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getMonth();
					int anioAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getYear();

					int diaVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getDate();
					int mesVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getMonth();
					int anioVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getYear();

					controladorPaquete.getFieldFechaAdquisicion().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioAdq, mesAdq, diaAdq)).getTime()),
									ZoneId.systemDefault()).toLocalDate());
					controladorPaquete.getFieldFechaVencimiento().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioVen, mesVen, diaVen)).getTime()),
									ZoneId.systemDefault()).toLocalDate());

					controladorPaquete.getFieldCantidad().setText(
							tablaPaquete.getItems().get(indiceSeleccionado).getCantidad());

				} else {
					controladorPaquete.getFieldCantidad().setText(
							Float.toString(listaPaqueteInsumo.get(indiceSeleccionado).getCantidad()));

					controladorPaquete.getFieldNumeroPaquete().setVisible(false);
					controladorPaquete.getFieldNumeroPaquete().setText(
							Integer.toString(listaPaqueteInsumo.get(indiceSeleccionado).getIdPaqueteInsumo()));
					int diaAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getDate();
					int mesAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getMonth();
					int anioAdq = listaPaqueteInsumo.get(indiceSeleccionado).getFechaAdquisicion().getYear();

					int diaVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getDate();
					int mesVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getMonth();
					int anioVen = listaPaqueteInsumo.get(indiceSeleccionado).getFechaCaducacion().getYear();

					controladorPaquete.getFieldFechaAdquisicion().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioAdq, mesAdq, diaAdq)).getTime()),
									ZoneId.systemDefault()).toLocalDate());
					controladorPaquete.getFieldFechaVencimiento().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioVen, mesVen, diaVen)).getTime()),
									ZoneId.systemDefault()).toLocalDate());

				}
			} else if (tipoRecurso == TipoRecurso.ALIMENTO) {
				if (Integer.parseInt(tablaPaquete.getItems().get(indiceSeleccionado).getIdPaquete()) == 0) {

					int diaAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getDate();
					int mesAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getMonth();
					int anioAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getYear();

					int diaVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getDate();
					int mesVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getMonth();
					int anioVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getYear();

					controladorPaquete.getFieldFechaAdquisicion().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioAdq, mesAdq, diaAdq)).getTime()),
									ZoneId.systemDefault()).toLocalDate());
					controladorPaquete.getFieldFechaVencimiento().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioVen, mesVen, diaVen)).getTime()),
									ZoneId.systemDefault()).toLocalDate());

					controladorPaquete.getFieldCantidad().setText(
							tablaPaquete.getItems().get(indiceSeleccionado).getCantidad());

				} else {

					controladorPaquete.getFieldCantidad().setText(
							Float.toString(listaPaqueteAlimento.get(indiceSeleccionado).getCantidad()));
					controladorPaquete.getFieldNumeroPaquete().setVisible(false);
					controladorPaquete.getFieldNumeroPaquete().setText(
							Integer.toString(listaPaqueteAlimento.get(indiceSeleccionado).getIdPaqueteAlimento()));
					int diaAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getDate();
					int mesAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getMonth();
					int anioAdq = listaPaqueteAlimento.get(indiceSeleccionado).getFechaAdquisicion().getYear();

					int diaVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getDate();
					int mesVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getMonth();
					int anioVen = listaPaqueteAlimento.get(indiceSeleccionado).getFechaVencimiento().getYear();

					controladorPaquete.getFieldFechaAdquisicion().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioAdq, mesAdq, diaAdq)).getTime()),
									ZoneId.systemDefault()).toLocalDate());
					controladorPaquete.getFieldFechaVencimiento().setValue(
							LocalDateTime.ofInstant(
									Instant.ofEpochMilli((new Date(anioVen, mesVen, diaVen)).getTime()),
									ZoneId.systemDefault()).toLocalDate());

				}
			}

			// Cargar los datos de los paquetes en la ventana
		}

		if (tipoVentana == TipoVentana.VER) {
			controladorPaquete.getFieldCantidad().setEditable(false);
			controladorPaquete.getFieldFechaAdquisicion().setEditable(false);
			controladorPaquete.getFieldFechaVencimiento().setEditable(false);
			controladorPaquete.getBotonGuardar().setVisible(false);

		}

		controladorPaquete.setPrincipal(this);
		controladorPaquete.setTipoRecurso(tipoRecurso);
		controladorPaquete.setTipoVentana(tipoVentana);
		controladorPaquete.setEscenario(escenarioPaquete);
		controladorPaquete.setTablaPaquete(tablaPaquete);
		controladorPaquete.getFieldNumeroPaquete().setText("0");
		controladorPaquete.getFieldNumeroPaquete().setEditable(false);
		controladorPaquete.setListaPaqueteAlimento(listaPaqueteAlimento);
		controladorPaquete.setListaPaqueteInsumo(listaPaqueteInsumo);
		controladorPaquete.setIndiceSeleccionado(indiceSeleccionado);
		escenarioPaquete.showAndWait();
	}

	public void cargarEspeciePrincipal() throws Exception {

		especiesObservables.setAll(Sistema.getSistema().pasarArrayEspecieAProperty(Sistema.getSistema().getEspecies()));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaEspecie.fxml"));
		AnchorPane anchoEspecie = (AnchorPane) loader.load();
		Stage escenarioEspecie = new Stage();
		escenarioEspecie.setResizable(false);
		escenarioEspecie.sizeToScene();
		Scene escena = new Scene(anchoEspecie);
		escenarioEspecie.setScene(escena);
		escenarioEspecie.setTitle("Especies del Sistema");
		escenarioEspecie.getIcons().add(new Image("file:recursos/imagenes/especie.png"));
		ControladorPantallaEspecie controladorEspecie = loader.getController();
		controladorEspecie.getImagenToolbarModificar().setImage(new Image("file:recursos/imagenes/EditarEspecie.png"));
		controladorEspecie.getImagenToolbarNueva().setImage(new Image("file:recursos/imagenes/NuevaEspecie.png"));
		controladorEspecie.getImagenToolbarQuitar().setImage(new Image("file:recursos/imagenes/BorrarEspecie.png"));
		controladorEspecie.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));
		controladorEspecie.getImagenToolbarVer().setImage(new Image("file:recursos/imagenes/VerEspecie.png"));
		controladorEspecie.setPrincipal(this);
		controladorEspecie.setEscenario(escenarioEspecie);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario us = (Usuario) session.get(Usuario.class, Sistema.getSistema().buscarUsuario(usuarioActivo.getNombre())
				.getIdUsuario());

		Boolean especialista = false;

		for (int i = 0; i < us.getPerfiles().size(); i++) {
			if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.ESPECIALISTA.toString())) {
				especialista = true;
			}
		}

		if (!especialista) {
			for (int i = 0; i < us.getPerfiles().size(); i++) {
				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.MEDIDOR.toString())) {
					controladorEspecie.getBotonModificar().setVisible(false);
					controladorEspecie.getBotonNueva().setVisible(false);
					controladorEspecie.getBotonQuitar().setVisible(false);
				}
			}
		}

		session.getTransaction().commit();
		escenarioEspecie.showAndWait();

	}

	public void cargarEspecie(TipoVentana tipoVentana, TableView<EspecieProperty> tablaEspecie, int indiceSeleccionado)
			throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/especie.fxml"));
		AnchorPane anchoUsuarios = (AnchorPane) loader.load();
		Stage escenarioEspecie = new Stage();
		escenarioEspecie.setResizable(false);
		escenarioEspecie.sizeToScene();
		Scene escena = new Scene(anchoUsuarios);
		escenarioEspecie.setScene(escena);

		escenarioEspecie.getIcons().add(new Image("file:recursos/imagenes/especie.png"));
		ControladorEspecie controladorEspecie = loader.getController();
		controladorEspecie.getImagenWarning().setVisible(false);
		controladorEspecie.getTextoWarning().setVisible(false);
		for (int i = 0; i < Sistema.getSistema().getAlimentos().size(); i++) {
			controladorEspecie.getListAlimentosSistema().getItems()
					.add(Sistema.getSistema().getAlimentos().get(i).getNombre());
		}

		controladorEspecie.getComboTipoAgua().getItems().addAll(TipoAgua.SALADA.toString(), TipoAgua.DULCE.toString());

		controladorEspecie.setEscenario(escenarioEspecie);
		controladorEspecie.setPrincipal(this);
		controladorEspecie.getFieldCantidad().setEditable(false);
		controladorEspecie.setTipoVentana(tipoVentana);
		controladorEspecie.setTablaEspecie(tablaEspecie);
		controladorEspecie.getImagenAgregarAlimento().setImage(new Image("file:recursos/imagenes/NuevoAlimento.png"));
		controladorEspecie.getImagenQuitarAlimento().setImage(new Image("file:recursos/imagenes/BorrarAlimento.png"));

		if (tipoVentana != TipoVentana.NUEVO) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Especie especie = (Especie) session.get(Especie.class,
					Sistema.getSistema().buscarEspecie(tablaEspecie.getItems().get(indiceSeleccionado).getNombre())
							.getIdEspecie());
			controladorEspecie.getComboTipoAgua().promptTextProperty().set(especie.getTipoAgua());
			controladorEspecie.getFieldCantidad().setText(Integer.toString(especie.getCantidad()));
			controladorEspecie.getFieldGhMaximo().setText(Float.toString(especie.getParametros().get(0).getMaximo()));
			controladorEspecie.getFieldGhMinimo().setText(Float.toString(especie.getParametros().get(0).getMinimo()));
			controladorEspecie.getFieldKhMaximo().setText(Float.toString(especie.getParametros().get(1).getMaximo()));
			controladorEspecie.getFieldKhMinimo().setText(Float.toString(especie.getParametros().get(1).getMinimo()));
			controladorEspecie.getFieldNombre().setText(especie.getNombre());
			controladorEspecie.getFieldPhMaximo().setText(Float.toString(especie.getParametros().get(2).getMaximo()));
			controladorEspecie.getFieldPhMinimo().setText(Float.toString(especie.getParametros().get(2).getMinimo()));
			controladorEspecie.getFieldTemperaturaMaxima().setText(
					Float.toString(especie.getParametros().get(3).getMaximo()));
			controladorEspecie.getFieldTemperaturaMinima().setText(
					Float.toString(especie.getParametros().get(3).getMinimo()));
			controladorEspecie.getComboTipoAgua().setValue(especie.getTipoAgua());
			controladorEspecie.getTextAreaDescripcion().setText(especie.getDescripcion());
			controladorEspecie.setIndiceSeleccionado(indiceSeleccionado);
			if (especie.getAlimentos().size() > 0) {
				for (int i = 0; i < especie.getAlimentos().size(); i++) {
					controladorEspecie.getListAlimentosEspecie().getItems()
							.add(especie.getAlimentos().get(i).getNombre());
				}
			}
			session.getTransaction().commit();
		}

		switch (tipoVentana.toString()) {
		case "NUEVO":
			controladorEspecie.getFieldCantidad().setDisable(true);
			controladorEspecie.getImagenEspecie().setImage(new Image("file:recursos/imagenes/NuevaEspecie.png"));
			controladorEspecie.getFieldCantidad().setText("0");
			escenarioEspecie.setTitle("Nueva especie");
			escenarioEspecie.getIcons().add(new Image("file:recursos/imagenes/NuevaEspecie.png"));

			break;

		case "EDITAR":
			if ((Integer.parseInt(controladorEspecie.getFieldCantidad().getText())) != 0) {
				controladorEspecie.getComboTipoAgua().setEditable(false);
				controladorEspecie.getComboTipoAgua().setDisable(true);

			}
			controladorEspecie.getComboTipoAgua().getItems().clear();
			controladorEspecie.getImagenEspecie().setImage(new Image("file:recursos/imagenes/EditarEspecie.png"));
			controladorEspecie.quitarAlimentosRepetidosDelSistema();
			escenarioEspecie.setTitle("Editar especie");
			escenarioEspecie.getIcons().add(new Image("file:recursos/imagenes/EditarEspecie.png"));
			break;

		case "VER":
			escenarioEspecie.setTitle("Ver especie");
			escenarioEspecie.getIcons().add(new Image("file:recursos/imagenes/VerEspecie.png"));
			controladorEspecie.getComboTipoAgua().setEditable(false);
			controladorEspecie.getImagenEspecie().setImage(new Image("file:recursos/imagenes/VerEspecie.png"));
			controladorEspecie.getFieldCantidad().setEditable(false);
			controladorEspecie.getFieldGhMaximo().setEditable(false);
			controladorEspecie.getFieldGhMinimo().setEditable(false);
			controladorEspecie.getFieldKhMaximo().setEditable(false);
			controladorEspecie.getFieldKhMinimo().setEditable(false);
			controladorEspecie.getFieldNombre().setEditable(false);
			controladorEspecie.getFieldPhMaximo().setEditable(false);
			controladorEspecie.getFieldPhMinimo().setEditable(false);
			controladorEspecie.getFieldTemperaturaMaxima().setEditable(false);
			controladorEspecie.getFieldTemperaturaMinima().setEditable(false);
			controladorEspecie.getBotonAgregarAlimento().setVisible(false);
			controladorEspecie.getBotonBorrarAlimento().setVisible(false);
			controladorEspecie.getBotonGuardar().setVisible(false);
			controladorEspecie.getListAlimentosSistema().getItems().clear();
			controladorEspecie.getBotonCancelar().setText("Salir");
			controladorEspecie.getTabAlimentos().setText("Alimentos y Acuarios");
			controladorEspecie.getLabelAlimentoSistema().setText("Acuarios donde se encuentra la especie");
			controladorEspecie.getComboTipoAgua().setEditable(false);
			controladorEspecie.getTextAreaDescripcion().setEditable(false);
			controladorEspecie.getImagenAgregarAlimento().setVisible(false);
			controladorEspecie.getImagenQuitarAlimento().setVisible(false);
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			for (int i = 0; i < Sistema.getSistema().getAcuarios().size(); i++) {
				Acuario acua = (Acuario) session.get(Acuario.class, Sistema.getSistema().getAcuarios().get(i)
						.getIdAcuario());
				System.out.println("En el acuario " + (i + 1) + " hay " + acua.getAgrupacionEspecie().size());
				if (acua.getAgrupacionEspecie().size() >= 1) {
					for (int j = 0; j < acua.getAgrupacionEspecie().size(); j++) {
						if (acua.getAgrupacionEspecie().get(j).getEspecie().getNombre()
								.equals(controladorEspecie.getFieldNombre().getText())) {
							if (acua.getAgrupacionEspecie().get(j).getCantidad() > 1) {
								controladorEspecie
										.getListAlimentosSistema()
										.getItems()
										.add(Integer.toString(acua.getAgrupacionEspecie().get(j).getCantidad())
												+ " especies en el acuario " + Integer.toString(acua.getIdAcuario()));
							} else {
								controladorEspecie
										.getListAlimentosSistema()
										.getItems()
										.add(Integer.toString(acua.getAgrupacionEspecie().get(j).getCantidad())
												+ " especie en el acuario " + Integer.toString(acua.getIdAcuario()));
							}

						}
					}

				}

			}
			session.getTransaction().commit();
			controladorEspecie.getComboTipoAgua().getItems().clear();

			break;

		}

		escenarioEspecie.showAndWait();

	}

	public void cargarAcuarioPrincipal() throws Exception {
		acuariosObservables
				.setAll(Sistema.getSistema().pasarArrayAcuariosAProperty(Sistema.getSistema().getAcuarios()));

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaAcuario.fxml"));
		AnchorPane anchoAcuario = (AnchorPane) loader.load();
		Stage escenarioAcuario = new Stage();
		escenarioAcuario.setResizable(false);
		escenarioAcuario.sizeToScene();
		Scene escena = new Scene(anchoAcuario);
		escenarioAcuario.setScene(escena);
		escenarioAcuario.setTitle("Acuarios del sistema");
		escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/acuario.png"));
		ControladorPantallaAcuario controladorAcuarioPrincipal = loader.getController();
		controladorAcuarioPrincipal.getImagenToolbarBorrar().setImage(
				new Image("file:recursos/imagenes/BorrarAcuario.png"));
		controladorAcuarioPrincipal.getImagenToolbarEditar().setImage(
				new Image("file:recursos/imagenes/EditarAcuario.png"));
		controladorAcuarioPrincipal.getImagenToolbarNuevo().setImage(
				new Image("file:recursos/imagenes/NuevoAcuario.png"));
		controladorAcuarioPrincipal.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));
		controladorAcuarioPrincipal.getImagenToolbarVer().setImage(new Image("file:recursos/imagenes/VerAcuario.png"));
		controladorAcuarioPrincipal.setEscenario(escenarioAcuario);
		controladorAcuarioPrincipal.setPrincipal(this);

		escenarioAcuario.showAndWait();
	}

	@SuppressWarnings("deprecation")
	public void cargarAcuario(TipoVentana tipoVentana, TableView<AcuarioProperty> tablaAcuario, int indiceSeleccionado)
			throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/acuario.fxml"));
		AnchorPane anchoAcuario = (AnchorPane) loader.load();
		Stage escenarioAcuario = new Stage();
		escenarioAcuario.setResizable(false);
		escenarioAcuario.sizeToScene();
		Scene escena = new Scene(anchoAcuario);
		escenarioAcuario.setScene(escena);
		ControladorAcuario controladorAcuario = loader.getController();
		controladorAcuario.getImagenToolbarBorrar().setImage(new Image("file:recursos/imagenes/BorrarEspecie.png"));
		controladorAcuario.getImagenToolbarEditar().setImage(new Image("file:recursos/imagenes/EditarEspecie.png"));
		controladorAcuario.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));
		controladorAcuario.getImagenToolbarVer().setImage(new Image("file:recursos/imagenes/VerEspecie.png"));
		controladorAcuario.getImagenToolbarTransferir().setImage(
				new Image("file:recursos/imagenes/TransferirEspecie.png"));
		controladorAcuario.getImagenToolbarAgregar().setImage(new Image("file:recursos/imagenes/NuevaEspecie.png"));
		controladorAcuario.setPrincipal(this);
		controladorAcuario.setEscenario(escenarioAcuario);
		controladorAcuario.setTipoVentana(tipoVentana);
		controladorAcuario.setTablaAcuario(tablaAcuario);

		if (tipoVentana != TipoVentana.NUEVO) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Acuario acuario = (Acuario) session.get(Acuario.class,
					Integer.parseInt(tablaAcuario.getItems().get(indiceSeleccionado).getNumero()));
			controladorAcuario.getFieldNumeroAcuario().setText(Integer.toString(acuario.getIdAcuario()));
			controladorAcuario.getFieldCapacidad().setText(Float.toString(acuario.getCapacidad()));
			controladorAcuario.getCheckboxDisponible().setSelected(acuario.isDisponible());
			controladorAcuario.setNumeroAcuario(acuario.getIdAcuario());
			controladorAcuario.getFieldNumeroAcuario().setEditable(false);
			controladorAcuario.getFieldCapacidad().setEditable(false);
			controladorAcuario.getFieldFechaCreacion().setEditable(false);
			controladorAcuario.getFieldGhMaximo().setEditable(false);
			controladorAcuario.getFieldGhMinimo().setEditable(false);
			controladorAcuario.getFieldKhMaximo().setEditable(false);
			controladorAcuario.getFieldKhMinimo().setEditable(false);
			controladorAcuario.getFieldPhMaximo().setEditable(false);
			controladorAcuario.getFieldPhMinimo().setEditable(false);
			controladorAcuario.getFieldTemperaturaMaxima().setEditable(false);
			controladorAcuario.getFieldTemperaturaMinima().setEditable(false);
			controladorAcuario.getFieldTemp().setText(
					acuario.getFechaCreacion().getDate() + "-" + (acuario.getFechaCreacion().getMonth() + 1) + "-"
							+ (acuario.getFechaCreacion().getYear() + 1900));
			controladorAcuario.getFieldTemp().setVisible(true);
			controladorAcuario.getFieldFechaCreacion().setVisible(false);
			controladorAcuario.getComboTipoAgua().setPromptText(acuario.getTipoAgua());
			controladorAcuario.getFieldGhMaximo().setText(Float.toString(acuario.getParametros().get(0).getMaximo()));
			controladorAcuario.getFieldGhMinimo().setText(Float.toString(acuario.getParametros().get(0).getMinimo()));
			controladorAcuario.getFieldKhMaximo().setText(Float.toString(acuario.getParametros().get(1).getMaximo()));
			controladorAcuario.getFieldKhMinimo().setText(Float.toString(acuario.getParametros().get(1).getMinimo()));
			controladorAcuario.getFieldPhMaximo().setText(Float.toString(acuario.getParametros().get(2).getMaximo()));
			controladorAcuario.getFieldPhMinimo().setText(Float.toString(acuario.getParametros().get(2).getMinimo()));
			controladorAcuario.getFieldTemperaturaMaxima().setText(
					Float.toString(acuario.getParametros().get(3).getMaximo()));
			controladorAcuario.getFieldTemperaturaMinima().setText(
					Float.toString(acuario.getParametros().get(3).getMinimo()));
			especiesObservables.clear();
			for (int i = 0; i < acuario.getAgrupacionEspecie().size(); i++) {
				especiesObservables.add(new EspecieProperty(acuario.getAgrupacionEspecie().get(i).getEspecie()
						.getNombre(), acuario.getAgrupacionEspecie().get(i).getEspecie().getDescripcion(), Integer
						.toString(acuario.getAgrupacionEspecie().get(i).getCantidad())));
				controladorAcuario.getListaEspecie().add(acuario.getAgrupacionEspecie().get(i));
			}

			session.getTransaction().commit();
		}
		switch (tipoVentana.toString()) {
		case "NUEVO":

			controladorAcuario.getComboTipoAgua().getItems().add(TipoAgua.DULCE.toString());
			controladorAcuario.getComboTipoAgua().getItems().add(TipoAgua.SALADA.toString());

			controladorAcuario.getToolBar().setDisable(true);
			controladorAcuario.getTablaEspecies().setDisable(true);
			controladorAcuario.getBotonVer().setVisible(false);
			controladorAcuario.getBotonBorrar().setVisible(false);
			controladorAcuario.getBotonTransferir().setVisible(false);
			controladorAcuario.getBotonAgregar().setVisible(false);
			controladorAcuario.getBotonEditar().setVisible(false);
			if (Sistema.getSistema().getAcuarios().size() >= 1) {
				controladorAcuario.getFieldNumeroAcuario().setText(
						Integer.toString(Sistema.getSistema().getAcuarios()
								.get(Sistema.getSistema().getAcuarios().size() - 1).getIdAcuario() + 1));

			} else {
				controladorAcuario.getFieldNumeroAcuario().setText("1");
			}
			controladorAcuario.getFieldFechaCreacion().setValue(
					LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.systemDefault())
							.toLocalDate());
			escenarioAcuario.setTitle("Nuevo Acuario");
			escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/NuevoAcuario.png"));
			break;

		case "VER":
			controladorAcuario.getToolBar().setDisable(true);
			controladorAcuario.getCheckboxDisponible().setDisable(true);
			controladorAcuario.getBotonAgregar().setVisible(false);
			controladorAcuario.getBotonBorrar().setVisible(false);
			controladorAcuario.getBotonEditar().setVisible(false);
			controladorAcuario.getBotonGuardar().setVisible(false);
			controladorAcuario.getBotonTransferir().setVisible(false);
			escenarioAcuario.setTitle("Ver acuario");
			escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/VerAcuario.png"));
			break;
		case "EDITAR":
			escenarioAcuario.setTitle("Editar acuario");
			escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/EditarAcuario.png"));
			boolean disponible = Sistema.getSistema()
					.buscarAcuario(Integer.parseInt(tablaAcuario.getItems().get(indiceSeleccionado).getNumero()))
					.isDisponible();

			if (!disponible || (Float.parseFloat(controladorAcuario.getFieldCapacidad().getText()) == 0)) {
				controladorAcuario.getBotonAgregar().setDisable(true);
			}
			break;

		}

		escenarioAcuario.showAndWait();
	}

	public void cargarAgrupacionEspecie(int numeroAcuario, List<AgrupacionEspecie> listaEspecie,
			TipoVentana tipoVentana, int idSeleccionado) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/agrupacionEspecie.fxml"));
		AnchorPane anchoAcuario = (AnchorPane) loader.load();
		Stage escenarioAcuario = new Stage();
		escenarioAcuario.setResizable(false);
		escenarioAcuario.sizeToScene();
		Scene escena = new Scene(anchoAcuario);
		escenarioAcuario.setScene(escena);
		ControladorAgrupacionEspecie controladorAgrEsp = loader.getController();
		controladorAgrEsp.getFieldNumeroAcuario().setText(Integer.toString(numeroAcuario));
		controladorAgrEsp.getFieldNumeroAcuario().setEditable(false);
		controladorAgrEsp.setListaEspecie(listaEspecie);
		controladorAgrEsp.setPrincipal(this);
		controladorAgrEsp.setEscenario(escenarioAcuario);
		controladorAgrEsp.setIdAcuario(numeroAcuario);
		controladorAgrEsp.setTipoVentana(tipoVentana);

		if (tipoVentana != TipoVentana.NUEVO) {
			controladorAgrEsp.getFieldCantidad().setText(
					Integer.toString(listaEspecie.get(idSeleccionado).getCantidad()));
			controladorAgrEsp.getComboEspecie()
					.setPromptText(listaEspecie.get(idSeleccionado).getEspecie().getNombre());
		}

		switch (tipoVentana.toString()) {
		case "NUEVO":
			for (int i = 0; i < Sistema.getSistema().getEspecies().size(); i++) {
				controladorAgrEsp.getComboEspecie().getItems()
						.add(Sistema.getSistema().getEspecies().get(i).getNombre());
				escenarioAcuario.setTitle("Agregar agrupación");
				escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/NuevoAcuario.png"));
			}
			break;

		case "VER":
			controladorAgrEsp.getFieldCantidad().setEditable(false);
			controladorAgrEsp.getBotonIngresar().setVisible(false);
			controladorAgrEsp.getBotonCancelar().setText("Salir");
			escenarioAcuario.setTitle("Ver agrupación");
			escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/VerAcuario.png"));
			break;

		case "EDITAR":
			controladorAgrEsp.getBotonIngresar().setText("Guardar");
			escenarioAcuario.setTitle("Editar agrupación");
			escenarioAcuario.getIcons().add(new Image("file:recursos/imagenes/EditarAcuario.png"));
			break;
		}

		escenarioAcuario.showAndWait();

	}

	public void trasnferirEspecie(int numeroAcuario, String nombreEspecie, int indiceSeleccionado,
			List<AgrupacionEspecie> listaAgrupacionEspecie) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/transferirEspecie.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioTransferirEspecie = new Stage();
		escenarioTransferirEspecie.setResizable(false);
		escenarioTransferirEspecie.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioTransferirEspecie.setScene(escena);
		ControladorTransferirEspecie controladorTransferirEspecie = loader.getController();
		controladorTransferirEspecie.setNroAcuarioorigen(numeroAcuario);
		controladorTransferirEspecie.setIndiceSeleccionado(indiceSeleccionado);
		controladorTransferirEspecie.setListaAgrupacionEspecie(listaAgrupacionEspecie);
		controladorTransferirEspecie.setEscenario(escenarioTransferirEspecie);
		controladorTransferirEspecie.getFieldAcuarioOrigen().setText(Integer.toString(numeroAcuario));
		controladorTransferirEspecie.getFieldEspecie().setText(nombreEspecie);
		controladorTransferirEspecie.setNombreEspecie(nombreEspecie);
		controladorTransferirEspecie.setPrincipal(this);
		controladorTransferirEspecie.getImagenTrasnferir().setImage(
				new Image("file:recursos/imagenes/TransferirEspecie.png"));
		escenarioTransferirEspecie.setTitle("Transferir especie");
		escenarioTransferirEspecie.getIcons().add(new Image("file:recursos/imagenes/TransferirEspecie.png"));
		for (int i = 0; i < Sistema.getSistema().getAcuarios().size(); i++) {
			if (Sistema.getSistema().getAcuarios().get(i).getIdAcuario() != numeroAcuario) {
				controladorTransferirEspecie.getComboBoxAcuarioDestino().getItems()
						.add(Integer.toString(Sistema.getSistema().getAcuarios().get(i).getIdAcuario()));
			}
		}
		escenarioTransferirEspecie.showAndWait();

	}

	public void cargarTareaPrincipal() throws Exception {

		tareasObservables.clear();
		tareasObservables.setAll(Sistema.getSistema().pasarArrayTareasProperty(Sistema.getSistema().getTareas()));
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaTarea.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioTarea = new Stage();
		escenarioTarea.setResizable(false);
		escenarioTarea.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioTarea.setScene(escena);
		ControladorPantallaTarea controladorTarea = loader.getController();
		controladorTarea.getImagenToolbarAsentar().setImage(new Image("file:recursos/imagenes/AsentarTarea.png"));
		controladorTarea.getImagenToolbarBorrar().setImage(new Image("file:recursos/imagenes/BorrarTarea.png"));
		controladorTarea.getImagenToolbarNueva().setImage(new Image("file:recursos/imagenes/NuevaTarea.png"));
		controladorTarea.getImagenToolbarSalir().setImage(new Image("file:recursos/imagenes/salir.png"));
		controladorTarea.getImagenToolbarVer().setImage(new Image("file:recursos/imagenes/VerTarea.png"));
		controladorTarea.setPrincipal(this);
		controladorTarea.setEscenario(escenarioTarea);
		controladorTarea.getComboEstadoTarea().getItems().add("Todas");
		controladorTarea.getComboEstadoTarea().getItems().add("Pendientes");
		controladorTarea.getComboEstadoTarea().getItems().add("Realizadas");
		controladorTarea.getComboEstadoTarea().setPromptText("Todas");
		escenarioTarea.setTitle("Tareas del sistema");
		escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/tarea.png"));

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Usuario us = (Usuario) session.get(Usuario.class, Sistema.getSistema().buscarUsuario(usuarioActivo.getNombre())
				.getIdUsuario());

		Boolean especialista = false;

		for (int i = 0; i < us.getPerfiles().size(); i++) {
			if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.ESPECIALISTA.toString())) {
				especialista = true;
			}
		}

		if (!especialista) {
			for (int i = 0; i < us.getPerfiles().size(); i++) {
				if (us.getPerfiles().get(i).getNombre().equals(TipoPerfil.MEDIDOR.toString())) {
					controladorTarea.getToolBar().getItems().remove(1);
					controladorTarea.getToolBar().getItems().remove(2);
					controladorTarea.getBotonAsentar().setLayoutX(controladorTarea.getBotonBorrar().getLayoutX());
					controladorTarea.getBotonBorrar().setVisible(false);
					controladorTarea.getBotonNueva().setVisible(false);
				}
			}
		}

		session.getTransaction().commit();

		//

		escenarioTarea.showAndWait();

	}

	public void cargarSelectorTarea() throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/selectorTarea.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioTarea = new Stage();
		escenarioTarea.setResizable(false);
		escenarioTarea.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioTarea.setScene(escena);
		ControladorSelectorTarea controladorSelector = loader.getController();
		controladorSelector.getImagenAlimentacion().setImage(new Image("file:recursos/imagenes/Alimento.png"));
		controladorSelector.getImagenLimpieza().setImage(new Image("file:recursos/imagenes/limpieza.png"));
		controladorSelector.getImagenMedicion().setImage(new Image("file:recursos/imagenes/medicion.png"));
		controladorSelector.setPrincipal(this);
		controladorSelector.setEscenario(escenarioTarea);
		escenarioTarea.setTitle("Seleccione el tipo de tarea");
		escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/tarea.png"));
		escenarioTarea.showAndWait();

	}

	public void cargarTarea(TipoRecurso tipoRecurso, TipoVentana tipoVentana, int idTarea) throws Exception {

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/tarea.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioTarea = new Stage();
		escenarioTarea.setResizable(false);
		escenarioTarea.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioTarea.setScene(escena);
		ControladorTarea controladorTarea = loader.getController();
		controladorTarea.setPrincipal(this);
		controladorTarea.setTipoRecurso(tipoRecurso);
		controladorTarea.setEscenario(escenarioTarea);
		controladorTarea.getFieldUsuario().setText(usuarioActivo.getNombre());
		controladorTarea.setTipoVentana(tipoVentana);
		controladorTarea.setIdTarea(idTarea);

		if (tipoVentana == TipoVentana.ASENTAR) {
			controladorTarea.getImagenRecurso().setImage(new Image("file:recursos/imagenes/AsentarTarea.png"));
			escenarioTarea.setTitle("Asentar tarea");
			escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/AsentarTarea.png"));
			controladorTarea.getBotonGuardar().setText("Asentar");
			controladorTarea.getFieldCantidad().setEditable(true);
		}

		if (tipoVentana == TipoVentana.VER) {
			controladorTarea.getBotonGuardar().setVisible(false);
			controladorTarea.getTextAreaObservaciones().setEditable(false);
			controladorTarea.getBotonCancelar().setText("Salir");
			escenarioTarea.setTitle("Ver tarea");
			escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/VerTarea.png"));
			controladorTarea.getFieldCantidad().setEditable(false);
			controladorTarea.getFieldUsuario().setEditable(false);
		}

		if (tipoVentana != TipoVentana.NUEVO) {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			controladorTarea.getFieldUsuario().setEditable(false);
			if ((tipoVentana != TipoVentana.ASENTAR)) {
				controladorTarea.getFieldCantidad().setEditable(false);
			}

			switch (tipoRecurso.toString()) {
			case "ALIMENTO":

				Alimentacion alimentacion = (Alimentacion) session.get(Alimentacion.class, idTarea);
				controladorTarea.getComboNumeroAcuario().setPromptText(
						Integer.toString(alimentacion.getNumeroAcuario()));
				controladorTarea.getComboNumeroAcuario().setEditable(false);
				controladorTarea.getComboRecurso().setPromptText(alimentacion.getAlimento().getNombre());
				controladorTarea.getComboRecurso().setEditable(false);
				controladorTarea.getLabelFechaFinalizacion().setValue(
						LocalDateTime.ofInstant(Instant.ofEpochMilli(alimentacion.getFechaCrecion().getTime()),
								ZoneId.systemDefault()).toLocalDate());
				controladorTarea.getLabelFechaFinalizacion().setEditable(false);
				controladorTarea.getTextAreaIndicaciones().setText(alimentacion.getIndicaciones());
				controladorTarea.getTextAreaIndicaciones().setEditable(false);
				controladorTarea.getTextAreaObservaciones().setText(alimentacion.getObservaciones());
				controladorTarea.getFieldCantidad().setText(Float.toString(alimentacion.getCantidad()));
				break;

			case "INSUMO":
				Limpieza limpieza = (Limpieza) session.get(Limpieza.class, idTarea);
				controladorTarea.getComboNumeroAcuario().setPromptText(Integer.toString(limpieza.getNumeroAcuario()));
				controladorTarea.getComboNumeroAcuario().setEditable(false);
				controladorTarea.getComboRecurso().setPromptText(limpieza.getInsumo().getNombre());
				controladorTarea.getComboRecurso().setEditable(false);
				controladorTarea.getLabelFechaFinalizacion().setValue(
						LocalDateTime.ofInstant(Instant.ofEpochMilli(limpieza.getFechaCrecion().getTime()),
								ZoneId.systemDefault()).toLocalDate());
				controladorTarea.getLabelFechaFinalizacion().setEditable(false);
				controladorTarea.getTextAreaIndicaciones().setText(limpieza.getIndicaciones());
				controladorTarea.getTextAreaIndicaciones().setEditable(false);
				controladorTarea.getTextAreaObservaciones().setText(limpieza.getObservaciones());
				controladorTarea.getFieldCantidad().setText(Float.toString(limpieza.getCantidad()));
				break;
			case "MEDICION":
				Medicion medicion = (Medicion) session.get(Medicion.class, idTarea);
				controladorTarea.getComboNumeroAcuario().setPromptText(Integer.toString(medicion.getNumeroAcuario()));
				controladorTarea.getComboNumeroAcuario().setEditable(false);
				controladorTarea.getComboRecurso().setPromptText(medicion.getParametro().getNombre());
				controladorTarea.getComboRecurso().setEditable(false);
				controladorTarea.getLabelFechaFinalizacion().setValue(
						LocalDateTime.ofInstant(Instant.ofEpochMilli(medicion.getFechaCrecion().getTime()),
								ZoneId.systemDefault()).toLocalDate());
				controladorTarea.getLabelFechaFinalizacion().setEditable(false);
				controladorTarea.getTextAreaIndicaciones().setText(medicion.getIndicaciones());
				controladorTarea.getTextAreaIndicaciones().setEditable(false);
				controladorTarea.getTextAreaObservaciones().setText(medicion.getObservaciones());
				controladorTarea.getFieldCantidad().setText(Float.toString(medicion.getCantidad()));
				break;

			}

			session.getTransaction().commit();

		}

		if (tipoVentana == TipoVentana.NUEVO) {
			for (int i = 0; i < Sistema.getSistema().getAcuarios().size(); i++) {
				controladorTarea.getComboNumeroAcuario().getItems()
						.add(Integer.toString(Sistema.getSistema().getAcuarios().get(i).getIdAcuario()));
			}
			controladorTarea.getComboNumeroAcuario().setPromptText(
					controladorTarea.getComboNumeroAcuario().getItems().get(0));

			controladorTarea.getFieldUsuario().setEditable(false);
			controladorTarea.getLabelFechaFinalizacion().setEditable(false);
			controladorTarea.getLabelFechaFinalizacion().setValue(
					LocalDateTime.ofInstant(Instant.ofEpochMilli(new Date().getTime()), ZoneId.systemDefault())
							.toLocalDate());
			controladorTarea.getTextAreaObservaciones().setDisable(true);
			switch (tipoRecurso.toString()) {
			case "ALIMENTO":
				for (int i = 0; i < Sistema.getSistema().getAlimentos().size(); i++) {
					controladorTarea.getComboRecurso().getItems()
							.add(Sistema.getSistema().getAlimentos().get(i).getNombre());
				}
				controladorTarea.getComboRecurso().setPromptText(controladorTarea.getComboRecurso().getItems().get(0));
				controladorTarea.getLabelUnidad().setText(
						Utilidades.miniUnidad
								.get(Sistema.getSistema()
										.buscarAlimento(controladorTarea.getComboRecurso().getItems().get(0))
										.getUnidadMedida()));
				controladorTarea.getLabelUnidad().setVisible(true);
				escenarioTarea.setTitle("Nueva Alimentación");
				escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/Alimento.png"));
				controladorTarea.getImagenRecurso().setImage(new Image("file:recursos/imagenes/Alimento.png"));

				break;

			case "INSUMO":
				for (int i = 0; i < Sistema.getSistema().getInsumos().size(); i++) {
					controladorTarea.getComboRecurso().getItems()
							.add(Sistema.getSistema().getInsumos().get(i).getNombre());
				}
				controladorTarea.getComboRecurso().setPromptText(controladorTarea.getComboRecurso().getItems().get(0));
				controladorTarea.getLabelUnidad().setText(
						Utilidades.miniUnidad.get(Sistema.getSistema()
								.buscarInsumo(controladorTarea.getComboRecurso().getItems().get(0)).getUnidadMedida()));
				controladorTarea.getLabelUnidad().setVisible(true);
				escenarioTarea.setTitle("Nueva Limpieza");
				escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/limpieza.png"));
				controladorTarea.getImagenRecurso().setImage(new Image("file:recursos/imagenes/limpieza.png"));
				break;

			case "MEDICION":
				for (int i = 0; i <= 3; i++) {
					controladorTarea.getComboRecurso().getItems()
							.add(Sistema.getSistema().getParametros().get(i).getNombre());
				}
				controladorTarea.getComboRecurso().setPromptText(controladorTarea.getComboRecurso().getItems().get(0));
				controladorTarea.getFieldCantidad().setEditable(false);
				escenarioTarea.setTitle("Nueva Medición");
				escenarioTarea.getIcons().add(new Image("file:recursos/imagenes/medicion.png"));
				controladorTarea.getImagenRecurso().setImage(new Image("file:recursos/imagenes/medicion.png"));
			}

		}
		escenarioTarea.showAndWait();
	}

	public void cargarPantallaReporte() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/pantallaReporte.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioReportes = new Stage();
		escenarioReportes.setResizable(false);
		escenarioReportes.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioReportes.setScene(escena);
		ControladorReporte controladorReporte = loader.getController();
		controladorReporte.setPrincipal(this);
		controladorReporte.setStage(escenarioReportes);
		controladorReporte.getImagenPdf().setImage(new Image("file:recursos/imagenes/reportes.png"));

		for (TipoReportes reporte : TipoReportes.values()) {
			controladorReporte.getComboTipoReporte().getItems().add(reporte.toString().toLowerCase());
		}

		escenarioReportes.setTitle("Reportes de SGA");
		escenarioReportes.getIcons().add(new Image("file:recursos/imagenes/informe.png"));
		escenarioReportes.showAndWait();

	}

	public void cargarAcercaDe() throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Utilidades.restToURL("/com/desarrollo/vistas/acercaDe.fxml"));
		AnchorPane anchorTransferir = (AnchorPane) loader.load();
		Stage escenarioReportes = new Stage();
		escenarioReportes.setResizable(false);
		escenarioReportes.sizeToScene();
		Scene escena = new Scene(anchorTransferir);
		escenarioReportes.setScene(escena);
		ControladorAcercaDe controladorAcercaDe = loader.getController();
		controladorAcercaDe.getImagen().setImage(new Image("file:recursos/imagenes/gifAcerca.gif"));
		escenarioReportes.setTitle("Acerca de SGA");
		escenarioReportes.getIcons().add(new Image("file:recursos/imagenes/especie.png"));
		escenarioReportes.show();
	}
}
