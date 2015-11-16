package com.desarrollo.controladores;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.modelo.Usuario;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.UsuarioProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorUsuarioPrincipal {

	@FXML
	private Button itemToolbarNuevo;

	@FXML
	private TableColumn<UsuarioProperty, String> columnaEstado;

	@FXML
	private TableColumn<UsuarioProperty, String> columnaPassword;

	@FXML
	private TableColumn<UsuarioProperty, String> columnaNombre;

	@FXML
	private TableView<UsuarioProperty> tablaUsuario;
	@FXML
	private Button botonEditar;

	@FXML
	private Button itemToolbarEditar;

	@FXML
	private ImageView itemToolbarNuevoImagen;

	@FXML
	private ImageView itemToolbarEditarImagen;

	@FXML
	private Button botonBorrar;

	@FXML
	private Button itemToolbarBorrar;

	@FXML
	private Button botonNuevo;

	@FXML
	private ImageView itemToolbarBorrarImagen;

	@FXML
	private Button itemToolbarSalir;

	@FXML
	private ImageView itemToolbarSalirImagen;

	@FXML
	private Button botonSalir;

	private Stage escenarioUsuario;

	private Principal principal;

	@FXML
	private void initialize() {

		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaPassword.setCellValueFactory(cellData -> cellData.getValue().asteriscoProperty());
		columnaEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
		columnaPassword.setStyle("-fx-alignment: CENTER;");

	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaUsuario.setItems(principal.getUsuariosObservables());
	}

	public Button getItemToolbarNuevo() {
		return itemToolbarNuevo;
	}

	public void setItemToolbarNuevo(Button itemToolbarNuevo) {
		this.itemToolbarNuevo = itemToolbarNuevo;
	}

	public TableColumn<UsuarioProperty, String> getColumnaEstado() {
		return columnaEstado;
	}

	public void setColumnaEstado(TableColumn<UsuarioProperty, String> columnaEstado) {
		this.columnaEstado = columnaEstado;
	}

	public TableColumn<UsuarioProperty, String> getColumnaPassword() {
		return columnaPassword;
	}

	public void setColumnaPassword(TableColumn<UsuarioProperty, String> columnaPassword) {
		this.columnaPassword = columnaPassword;
	}

	public TableColumn<UsuarioProperty, String> getColumnaNombre() {
		return columnaNombre;
	}

	public void setColumnaNombre(TableColumn<UsuarioProperty, String> columnaNombre) {
		this.columnaNombre = columnaNombre;
	}

	public TableView<UsuarioProperty> getTablaUsuario() {
		return tablaUsuario;
	}

	public void setTablaUsuario(TableView<UsuarioProperty> tablaUsuario) {
		this.tablaUsuario = tablaUsuario;
	}

	public Button getBotonEditar() {
		return botonEditar;
	}

	public void setBotonEditar(Button botonEditar) {
		this.botonEditar = botonEditar;
	}

	public Button getItemToolbarEditar() {
		return itemToolbarEditar;
	}

	public void setItemToolbarEditar(Button itemToolbarEditar) {
		this.itemToolbarEditar = itemToolbarEditar;
	}

	public ImageView getItemToolbarNuevoImagen() {
		return itemToolbarNuevoImagen;
	}

	public void setItemToolbarNuevoImagen(ImageView itemToolbarNuevoImagen) {
		this.itemToolbarNuevoImagen = itemToolbarNuevoImagen;
	}

	public ImageView getItemToolbarEditarImagen() {
		return itemToolbarEditarImagen;
	}

	public void setItemToolbarEditarImagen(ImageView itemToolbarEditarImagen) {
		this.itemToolbarEditarImagen = itemToolbarEditarImagen;
	}

	public Button getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(Button botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public Button getItemToolbarBorrar() {
		return itemToolbarBorrar;
	}

	public void setItemToolbarBorrar(Button itemToolbarBorrar) {
		this.itemToolbarBorrar = itemToolbarBorrar;
	}

	public Button getBotonNuevo() {
		return botonNuevo;
	}

	public void setBotonNuevo(Button botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	public ImageView getItemToolbarBorrarImagen() {
		return itemToolbarBorrarImagen;
	}

	public void setItemToolbarBorrarImagen(ImageView itemToolbarBorrarImagen) {
		this.itemToolbarBorrarImagen = itemToolbarBorrarImagen;
	}

	public Button getItemToolbarSalir() {
		return itemToolbarSalir;
	}

	public void setItemToolbarSalir(Button itemToolbarSalir) {
		this.itemToolbarSalir = itemToolbarSalir;
	}

	public ImageView getItemToolbarSalirImagen() {
		return itemToolbarSalirImagen;
	}

	public void setItemToolbarSalirImagen(ImageView itemToolbarSalirImagen) {
		this.itemToolbarSalirImagen = itemToolbarSalirImagen;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public Stage getEscenarioUsuario() {
		return escenarioUsuario;
	}

	public void setEscenarioUsuario(Stage escenarioUsuario) {
		this.escenarioUsuario = escenarioUsuario;
	}

	@FXML
	private void salir() {
		escenarioUsuario.close();
	}

	@FXML
	private void nuevoUsuario() throws Exception {
		principal.cargarUsuario(TipoVentana.NUEVO, tablaUsuario, -1);

	}

	@FXML
	private void editarUsuario() throws Exception {
		int indiceUsuarioSeleccionado = tablaUsuario.getSelectionModel().getSelectedIndex();
		if (indiceUsuarioSeleccionado >= 0) {
			String nombre = tablaUsuario.getItems().get(indiceUsuarioSeleccionado).getNombre();
			Usuario usuario = Sistema.getSistema().buscarUsuario(nombre);
			principal.cargarUsuario(TipoVentana.EDITAR, tablaUsuario, usuario.getIdUsuario());
		} else {
			Dialogs.create().title("No ha seleccionado un usuario").masthead("No hay usuario seleccionada")
					.message("Por favor selecione un usuario en la tabla").showWarning();
		}

	}

	@FXML
	private void borrarUsuario() {
		int indiceUsuarioBorrar = tablaUsuario.getSelectionModel().getSelectedIndex();
		if (indiceUsuarioBorrar >= 0) {

			Action response = Dialogs.create().style(DialogStyle.NATIVE)
					.title("Esta a punto de borrar un usuario del sistema")
					.message("Tenga en cuenta de que se borrará permanentemente del sistema").showConfirm();

			if (response == Dialog.Actions.YES) {

				if (principal
						.getUsuarioActivo()
						.getNombre()
						.equals(tablaUsuario.getItems().get(tablaUsuario.getSelectionModel().getSelectedIndex())
								.getNombre())) {

					Dialogs.create().style(DialogStyle.NATIVE).title("Imposible borrar usuario")
							.masthead("El usuario seleccionado está actualmente activo").showError();

				} else {
					String nombre = tablaUsuario.getItems().get(indiceUsuarioBorrar).getNombre();
					Usuario tusuario = Sistema.getSistema().buscarUsuario(nombre);
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Usuario us = (Usuario) session.get(Usuario.class, tusuario.getIdUsuario());
					session.delete(us);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.BORRAR_USUARIO) + us.getNombre());
					tablaUsuario.getItems().remove(indiceUsuarioBorrar);
					HibernateUtil.traerDatosBase();
					principal.getUsuariosObservables().clear();
					principal.setUsuariosObservables(Sistema.getSistema().pasarArrayUsuarioAProperty(
							Sistema.getSistema().getUsuarios()));
					tablaUsuario.getItems().clear();
					tablaUsuario.getItems().setAll(principal.getUsuariosObservables());

				}

			}

		} else {
			Dialogs.create().title("No Seleccionado").masthead("No hay usuario seleccionada")
					.message("Por favor selecione un usuario en la tabla").showWarning();
		}

	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondeUsuarios.html");
	}

}
