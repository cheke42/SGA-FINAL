package com.desarrollo.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.modelo.Tarea;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.TareaProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorPantallaTarea {
	@FXML
	private ToolBar toolBar;

	@FXML
	private Button botonBorrarTarea;

	@FXML
	private Button botonAsentarTarea;

	@FXML
	private Button botonSalirTarea;

	@FXML
	private Button botonNuevaTarea;

	@FXML
	private TableColumn<TareaProperty, String> columnaCreador;

	@FXML
	private TableColumn<TareaProperty, String> columnaFechaCreacion;

	@FXML
	private ImageView imagenToolbarNueva;

	@FXML
	private TableView<TareaProperty> tablaTarea;

	@FXML
	private Button botonBorrar;

	@FXML
	private ImageView imagenToolbarVer;

	@FXML
	private Button botonAsentar;

	@FXML
	private Button botonVer;

	@FXML
	private ImageView imagenToolbarBorrar;

	@FXML
	private Button botonSalir;

	@FXML
	private TableColumn<TareaProperty, String> columnaEstado;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private ImageView imagenToolbarAsentar;

	@FXML
	private TableColumn<TareaProperty, String> columnaTipoTarea;

	@FXML
	private TableColumn<TareaProperty, String> columnaNumeroTarea;

	@FXML
	private Button botonNueva;

	private Principal principal;

	private Stage escenario;

	@FXML
	private ComboBox<String> comboEstadoTarea;

	@FXML
	private void initialize() {
		columnaCreador.setCellValueFactory(cellData -> cellData.getValue().creadorProperty());
		columnaEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
		columnaFechaCreacion.setCellValueFactory(cellData -> cellData.getValue().fechaCreacionProperty());
		columnaNumeroTarea.setCellValueFactory(cellData -> cellData.getValue().numeroTareaProperty());
		columnaTipoTarea.setCellValueFactory(cellData -> cellData.getValue().tipoTareaProperty());

		columnaCreador.setStyle("-fx-alignment: CENTER;");
		columnaEstado.setStyle("-fx-alignment: CENTER;");
		columnaFechaCreacion.setStyle("-fx-alignment: CENTER;");
		columnaNumeroTarea.setStyle("-fx-alignment: CENTER;");
		columnaTipoTarea.setStyle("-fx-alignment: CENTER;");
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public Button getBotonAsentarTarea() {
		return botonAsentarTarea;
	}

	public void setBotonAsentarTarea(Button botonAsentarTarea) {
		this.botonAsentarTarea = botonAsentarTarea;
	}

	public Button getBotonSalirTarea() {
		return botonSalirTarea;
	}

	public void setBotonSalirTarea(Button botonSalirTarea) {
		this.botonSalirTarea = botonSalirTarea;
	}

	public Button getBotonBorrarTarea() {
		return botonBorrarTarea;
	}

	public void setBotonBorrarTarea(Button botonBorrarTarea) {
		this.botonBorrarTarea = botonBorrarTarea;
	}

	public Button getBotonNuevaTarea() {
		return botonNuevaTarea;
	}

	public void setBotonNuevaTarea(Button botonNuevaTarea) {
		this.botonNuevaTarea = botonNuevaTarea;
	}

	public ComboBox<String> getComboEstadoTarea() {
		return comboEstadoTarea;
	}

	public void setComboEstadoTarea(ComboBox<String> comboEstadoTarea) {
		this.comboEstadoTarea = comboEstadoTarea;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaTarea.setItems(principal.getTareasObservables());
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public TableColumn<TareaProperty, String> getColumnaCreador() {
		return columnaCreador;
	}

	public void setColumnaCreador(TableColumn<TareaProperty, String> columnaCreador) {
		this.columnaCreador = columnaCreador;
	}

	public TableColumn<TareaProperty, String> getColumnaFechaCreacion() {
		return columnaFechaCreacion;
	}

	public void setColumnaFechaCreacion(TableColumn<TareaProperty, String> columnaFechaCreacion) {
		this.columnaFechaCreacion = columnaFechaCreacion;
	}

	public ImageView getImagenToolbarNueva() {
		return imagenToolbarNueva;
	}

	public void setImagenToolbarNueva(ImageView imagenToolbarNueva) {
		this.imagenToolbarNueva = imagenToolbarNueva;
	}

	public TableView<TareaProperty> getTablaTarea() {
		return tablaTarea;
	}

	public void setTablaTarea(TableView<TareaProperty> tablaTarea) {
		this.tablaTarea = tablaTarea;
	}

	public Button getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(Button botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public ImageView getImagenToolbarVer() {
		return imagenToolbarVer;
	}

	public void setImagenToolbarVer(ImageView imagenToolbarVer) {
		this.imagenToolbarVer = imagenToolbarVer;
	}

	public Button getBotonAsentar() {
		return botonAsentar;
	}

	public void setBotonAsentar(Button botonAsentar) {
		this.botonAsentar = botonAsentar;
	}

	public Button getBotonVer() {
		return botonVer;
	}

	public void setBotonVer(Button botonVer) {
		this.botonVer = botonVer;
	}

	public ImageView getImagenToolbarBorrar() {
		return imagenToolbarBorrar;
	}

	public void setImagenToolbarBorrar(ImageView imagenToolbarBorrar) {
		this.imagenToolbarBorrar = imagenToolbarBorrar;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public TableColumn<TareaProperty, String> getColumnaEstado() {
		return columnaEstado;
	}

	public void setColumnaEstado(TableColumn<TareaProperty, String> columnaEstado) {
		this.columnaEstado = columnaEstado;
	}

	public ImageView getImagenToolbarSalir() {
		return imagenToolbarSalir;
	}

	public void setImagenToolbarSalir(ImageView imagenToolbarSalir) {
		this.imagenToolbarSalir = imagenToolbarSalir;
	}

	public ImageView getImagenToolbarAsentar() {
		return imagenToolbarAsentar;
	}

	public void setImagenToolbarAsentar(ImageView imagenToolbarAsentar) {
		this.imagenToolbarAsentar = imagenToolbarAsentar;
	}

	public TableColumn<TareaProperty, String> getColumnaTipoTarea() {
		return columnaTipoTarea;
	}

	public void setColumnaTipoTarea(TableColumn<TareaProperty, String> columnaTipoTarea) {
		this.columnaTipoTarea = columnaTipoTarea;
	}

	public TableColumn<TareaProperty, String> getColumnaNumeroTarea() {
		return columnaNumeroTarea;
	}

	public void setColumnaNumeroTarea(TableColumn<TareaProperty, String> columnaNumeroTarea) {
		this.columnaNumeroTarea = columnaNumeroTarea;
	}

	public Button getBotonNueva() {
		return botonNueva;
	}

	public void setBotonNueva(Button botonNueva) {
		this.botonNueva = botonNueva;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	@FXML
	void nuevaTarea() throws Exception {
		principal.cargarSelectorTarea();
		ActualizarTarea();
	}

	@FXML
	void borrarTarea() {
		int indiceSeleccionado = tablaTarea.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			Action response = Dialogs.create().style(DialogStyle.NATIVE)
					.title("Esta a punto de borrar una tarea del Sistema")
					.message("Tenga en cuenta de que si  la elimina se borrará permanentemente. ¿Desea continuar?")
					.showConfirm();

			if (response == Dialog.Actions.YES) {

				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();

				Tarea tarea = (Tarea) session.get(Tarea.class,
						Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));

				session.delete(tarea);
				session.getTransaction().commit();
				ActualizarTarea();
			}

		} else if (tablaTarea.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder operar se debe contener algun valor en la tabla").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Seleccion erronea")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione la tarea a borrar").showWarning();
		}
	}

	@FXML
	void verTarea() throws Exception {
		int indiceSeleccionado = tablaTarea.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			switch (tablaTarea.getItems().get(indiceSeleccionado).getTipoTarea()) {
			case "ALIMENTACION":
				principal.cargarTarea(TipoRecurso.ALIMENTO, TipoVentana.VER,
						Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
				break;

			case "LIMPIEZA":
				principal.cargarTarea(TipoRecurso.INSUMO, TipoVentana.VER,
						Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
				break;

			case "MEDICION":
				principal.cargarTarea(TipoRecurso.MEDICION, TipoVentana.VER,
						Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
				break;
			}

			ActualizarTarea();

		} else if (tablaTarea.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder operar se debe contener algun valor en la tabla").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Seleccion erronea")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione la tarea a borrar").showWarning();
		}

	}

	@FXML
	void asentarTarea() throws Exception {
		int indiceSeleccionado = tablaTarea.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			if (tablaTarea.getItems().get(tablaTarea.getSelectionModel().getSelectedIndex()).getEstado() == "Pendiente") {
				switch (tablaTarea.getItems().get(indiceSeleccionado).getTipoTarea()) {
				case "ALIMENTACION":
					principal.cargarTarea(TipoRecurso.ALIMENTO, TipoVentana.ASENTAR,
							Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
					break;

				case "LIMPIEZA":
					principal.cargarTarea(TipoRecurso.INSUMO, TipoVentana.ASENTAR,
							Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
					break;

				case "MEDICION":
					principal.cargarTarea(TipoRecurso.MEDICION, TipoVentana.ASENTAR,
							Integer.parseInt(tablaTarea.getItems().get(indiceSeleccionado).getNumeroTarea()));
					break;
				}

				ActualizarTarea();

			} else {
				Dialogs.create().style(DialogStyle.NATIVE).title("Actividad ya asentada")
						.masthead("La actividad fue ascentada")
						.message("La actividad fue anteriormente asentada. Para ver el detalle seleccione <Ver>")
						.showWarning();
			}

		} else if (tablaTarea.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder operar se debe contener algun valor en la tabla").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Seleccion erronea")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione la tarea a borrar").showWarning();
		}
	}

	private void ActualizarTarea() {
		HibernateUtil.traerDatosBase();
		principal.getTareasObservables().clear();
		principal.setTareasObservables(Sistema.getSistema().pasarArrayTareasProperty(Sistema.getSistema().getTareas()));
		tablaTarea.getItems().clear();
		tablaTarea.setItems(principal.getTareasObservables());
	}

	@SuppressWarnings("rawtypes")
	@FXML
	void cambiarTipoTarea(ActionEvent event) {
		ComboBox combo = (ComboBox) event.getSource();

		switch ((String) combo.getItems().get(combo.getSelectionModel().getSelectedIndex())) {
		case "Todas":
			principal.getTareasObservables().clear();
			principal.setTareasObservables(Sistema.getSistema().pasarArrayTareasProperty(
					Sistema.getSistema().getTareas()));
			tablaTarea.getItems().clear();
			tablaTarea.setItems(principal.getTareasObservables());
			break;

		case "Pendientes":
			principal.getTareasObservables().clear();
			principal.setTareasObservables(Sistema.getSistema().pasarArrayTareasPendientesProperty(
					Sistema.getSistema().getTareas()));
			tablaTarea.getItems().clear();
			tablaTarea.setItems(principal.getTareasObservables());
			break;

		case "Realizadas":
			principal.getTareasObservables().clear();
			principal.setTareasObservables(Sistema.getSistema().pasarArrayTareasRealizadasProperty(
					Sistema.getSistema().getTareas()));
			tablaTarea.getItems().clear();
			tablaTarea.setItems(principal.getTareasObservables());
			break;
		}

	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondePlanificacion.html");
	}
}
