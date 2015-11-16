package com.desarrollo.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.EspecieProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorPantallaEspecie {

	@FXML
	private TableColumn<EspecieProperty, String> columnaCantidad;

	@FXML
	private TableColumn<EspecieProperty, String> columnaDescripcion;

	@FXML
	private ImageView imagenToolbarNueva;

	@FXML
	private TableView<EspecieProperty> tablaEspecie;

	@FXML
	private ImageView imagenToolbarVer;

	@FXML
	private ImageView imagenToolbarQuitar;

	@FXML
	private ImageView imagenToolbarModificar;

	@FXML
	private Button botonModificar;

	@FXML
	private Button botonVer;

	@FXML
	private Button botonQuitar;

	@FXML
	private Button botonSalir;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private TableColumn<EspecieProperty, String> columnaNombre;

	@FXML
	private Button botonNueva;

	private Principal principal;

	private Stage escenario;

	@FXML
	private void initialize() {
		columnaCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
	}

	public TableColumn<EspecieProperty, String> getColumnaCantidad() {
		return columnaCantidad;
	}

	public void setColumnaCantidad(TableColumn<EspecieProperty, String> columnaCantidad) {
		this.columnaCantidad = columnaCantidad;
	}

	public TableColumn<EspecieProperty, String> getColumnaDescripcion() {
		return columnaDescripcion;
	}

	public void setColumnaDescripcion(TableColumn<EspecieProperty, String> columnaDescripcion) {
		this.columnaDescripcion = columnaDescripcion;
	}

	public ImageView getImagenToolbarNueva() {
		return imagenToolbarNueva;
	}

	public void setImagenToolbarNueva(ImageView imagenToolbarNueva) {
		this.imagenToolbarNueva = imagenToolbarNueva;
	}

	public TableView<EspecieProperty> getTablaEspecie() {
		return tablaEspecie;
	}

	public void setTablaEspecie(TableView<EspecieProperty> tablaEspecie) {
		this.tablaEspecie = tablaEspecie;
	}

	public ImageView getImagenToolbarVer() {
		return imagenToolbarVer;
	}

	public void setImagenToolbarVer(ImageView imagenToolbarVer) {
		this.imagenToolbarVer = imagenToolbarVer;
	}

	public ImageView getImagenToolbarQuitar() {
		return imagenToolbarQuitar;
	}

	public void setImagenToolbarQuitar(ImageView imagenToolbarQuitar) {
		this.imagenToolbarQuitar = imagenToolbarQuitar;
	}

	public ImageView getImagenToolbarModificar() {
		return imagenToolbarModificar;
	}

	public void setImagenToolbarModificar(ImageView imagenToolbarModificar) {
		this.imagenToolbarModificar = imagenToolbarModificar;
	}

	public Button getBotonModificar() {
		return botonModificar;
	}

	public void setBotonModificar(Button botonModificar) {
		this.botonModificar = botonModificar;
	}

	public Button getBotonVer() {
		return botonVer;
	}

	public void setBotonVer(Button botonVer) {
		this.botonVer = botonVer;
	}

	public Button getBotonQuitar() {
		return botonQuitar;
	}

	public void setBotonQuitar(Button botonQuitar) {
		this.botonQuitar = botonQuitar;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public ImageView getImagenToolbarSalir() {
		return imagenToolbarSalir;
	}

	public void setImagenToolbarSalir(ImageView imagenToolbarSalir) {
		this.imagenToolbarSalir = imagenToolbarSalir;
	}

	public TableColumn<EspecieProperty, String> getColumnaNombre() {
		return columnaNombre;
	}

	public void setColumnaNombre(TableColumn<EspecieProperty, String> columnaNombre) {
		this.columnaNombre = columnaNombre;
	}

	public Button getBotonNueva() {
		return botonNueva;
	}

	public void setBotonNueva(Button botonNueva) {
		this.botonNueva = botonNueva;
		tablaEspecie.setItems(principal.getEspeciesObservables());
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaEspecie.setItems(principal.getEspeciesObservables());
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	@FXML
	void salir() {
		principal.getEspeciesObservables().clear();
		escenario.close();
	}

	@FXML
	void ver() throws Exception {
		int indiceSeleccionado = tablaEspecie.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			principal.cargarEspecie(TipoVentana.VER, tablaEspecie, indiceSeleccionado);

		} else if (tablaEspecie.getItems().size() < 1) {

			Dialogs dialogo = Dialogs.create();
			dialogo.title("Tabla Vacia");
			dialogo.masthead("La tabla no contiene datos");
			dialogo.message("Para poder ver los datos de una especie primero debe crear alguna. Pulse sobre el boton <Nueva>");
			dialogo.style(DialogStyle.NATIVE);
			dialogo.showWarning();

		} else {
			Dialogs dialogo = Dialogs.create();
			dialogo.title("Seleccion erronea");
			dialogo.masthead("No se detecta un item de la tabla seleccionado");
			dialogo.message("Por favor seleccione alguna especie en la tabla");
			dialogo.style(DialogStyle.NATIVE);
			dialogo.showWarning();

		}

	}

	@FXML
	void nuevaEspecie() throws Exception {
		principal.cargarEspecie(TipoVentana.NUEVO, tablaEspecie, -1);
	}

	@FXML
	void editarEspecie() throws Exception {
		int indiceSeleccionado = tablaEspecie.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			principal.cargarEspecie(TipoVentana.EDITAR, tablaEspecie, indiceSeleccionado);

		} else if (tablaEspecie.getItems().size() < 1) {

			Dialogs dialogo = Dialogs.create();
			dialogo.title("Tabla Vacia");
			dialogo.masthead("La tabla no contiene datos");
			dialogo.message("Para poder editar los datos de una especie primero debe crear alguna. Pulse sobre el boton <Nueva>");
			dialogo.style(DialogStyle.NATIVE);
			dialogo.showWarning();

		} else {
			Dialogs dialogo = Dialogs.create();
			dialogo.title("Seleccion erronea");
			dialogo.masthead("No se detecta un item de la tabla seleccionado");
			dialogo.message("Por favor seleccione alguna especie en la tabla");
			dialogo.style(DialogStyle.NATIVE);
			dialogo.showWarning();

		}

	}

	@FXML
	void quitarEspecie() {

		int indiceSeleccionado = tablaEspecie.getSelectionModel().getSelectedIndex();
		if (Sistema.getSistema().buscarEspecie(tablaEspecie.getItems().get(indiceSeleccionado).getNombre())
				.getCantidad() == 0) {

			if (indiceSeleccionado >= 0) {

				Action response = Dialogs
						.create()
						.style(DialogStyle.NATIVE)
						.title("Esta a punto de borrar una especie del Sistema")
						.message(
								"Tenga en cuenta de que si elimina la especie se borrará permanentemente. ¿Desea continuar?")
						.showConfirm()

				;
				if (response == Dialog.Actions.YES) {
					Especie especie = Sistema.getSistema().buscarEspecie(
							tablaEspecie.getItems().get(indiceSeleccionado).getNombre());
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Especie espBorrar = (Especie) session.get(Especie.class, especie.getIdEspecie());
					session.delete(espBorrar);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.BORRAR_ESPECIE) + espBorrar.getNombre());
					
					tablaEspecie.getItems().remove(indiceSeleccionado);

				}

			} else if (tablaEspecie.getItems().size() < 1) {

				Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
						.message("Para poder operar se debe contener algun valor en la tabla").showWarning();
			} else {
				Dialogs.create().style(DialogStyle.NATIVE).title("Seleccion erronea")
						.masthead("No se detecta un item de la tabla seleccionado")
						.message("Por favor seleccione la especie a borrar").showWarning();
			}

		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Acción no permitida")
					.message("La especie que desea borrar está siendo actualmente utilizada en otras actividades")
					.showError();
		}

	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondeEspecies.html");
	}

}
