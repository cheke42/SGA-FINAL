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

import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Acuario;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.AcuarioProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorPantallaAcuario {

	@FXML
	private TableColumn<AcuarioProperty, String> columnaFechaCreacion;

	@FXML
	private TableColumn<AcuarioProperty, String> columnaNumeroAcuario;

	@FXML
	private Button botonEditar;

	@FXML
	private ImageView imagenToolbarNuevo;

	@FXML
	private Button botonBorrar;

	@FXML
	private Button botonNuevo;

	@FXML
	private ImageView imagenToolbarVer;

	@FXML
	private ImageView imagenToolbarEditar;

	@FXML
	private Button botonVer;

	@FXML
	private ImageView imagenToolbarBorrar;

	@FXML
	private TableView<AcuarioProperty> tablaAcuario;

	@FXML
	private Button botonSalir;

	@FXML
	private TableColumn<AcuarioProperty, String> columnaEstado;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private TableColumn<AcuarioProperty, String> columnaCapacidad;

	private Principal principal;

	private Stage escenario;

	@FXML
	private void initialize() {
		columnaCapacidad.setCellValueFactory(cellData -> cellData.getValue().capacidadProperty());
		columnaEstado.setCellValueFactory(cellData -> cellData.getValue().estadoProperty());
		columnaFechaCreacion.setCellValueFactory(cellData -> cellData.getValue().fechaCreacionProperty());
		columnaNumeroAcuario.setCellValueFactory(cellData -> cellData.getValue().numeroProperty());

		columnaCapacidad.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaEstado.setStyle("-fx-alignment: CENTER;");
		columnaFechaCreacion.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaNumeroAcuario.setStyle("-fx-alignment: CENTER;");

	}

	public TableColumn<AcuarioProperty, String> getColumnaFechaCreacion() {
		return columnaFechaCreacion;
	}

	public void setColumnaFechaCreacion(TableColumn<AcuarioProperty, String> columnaFechaCreacion) {
		this.columnaFechaCreacion = columnaFechaCreacion;
	}

	public TableColumn<AcuarioProperty, String> getColumnaNumeroAcuario() {
		return columnaNumeroAcuario;
	}

	public void setColumnaNumeroAcuario(TableColumn<AcuarioProperty, String> columnaNumeroAcuario) {
		this.columnaNumeroAcuario = columnaNumeroAcuario;
	}

	public Button getBotonEditar() {
		return botonEditar;
	}

	public void setBotonEditar(Button botonEditar) {
		this.botonEditar = botonEditar;
	}

	public ImageView getImagenToolbarNuevo() {
		return imagenToolbarNuevo;
	}

	public void setImagenToolbarNuevo(ImageView imagenToolbarNuevo) {
		this.imagenToolbarNuevo = imagenToolbarNuevo;
	}

	public Button getBotonBorrar() {
		return botonBorrar;
	}

	public void setBotonBorrar(Button botonBorrar) {
		this.botonBorrar = botonBorrar;
	}

	public Button getBotonNuevo() {
		return botonNuevo;
	}

	public void setBotonNuevo(Button botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	public ImageView getImagenToolbarVer() {
		return imagenToolbarVer;
	}

	public void setImagenToolbarVer(ImageView imagenToolbarVer) {
		this.imagenToolbarVer = imagenToolbarVer;
	}

	public ImageView getImagenToolbarEditar() {
		return imagenToolbarEditar;
	}

	public void setImagenToolbarEditar(ImageView imagenToolbarEditar) {
		this.imagenToolbarEditar = imagenToolbarEditar;
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

	public TableView<AcuarioProperty> getTablaAcuario() {
		return tablaAcuario;
	}

	public void setTablaAcuario(TableView<AcuarioProperty> tablaAcuario) {
		this.tablaAcuario = tablaAcuario;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public TableColumn<AcuarioProperty, String> getColumnaEstado() {
		return columnaEstado;
	}

	public void setColumnaEstado(TableColumn<AcuarioProperty, String> columnaEstado) {
		this.columnaEstado = columnaEstado;
	}

	public ImageView getImagenToolbarSalir() {
		return imagenToolbarSalir;
	}

	public void setImagenToolbarSalir(ImageView imagenToolbarSalir) {
		this.imagenToolbarSalir = imagenToolbarSalir;
	}

	public TableColumn<AcuarioProperty, String> getColumnaCapacidad() {
		return columnaCapacidad;
	}

	public void setColumnaCapacidad(TableColumn<AcuarioProperty, String> columnaCapacidad) {
		this.columnaCapacidad = columnaCapacidad;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaAcuario.setItems(principal.getAcuariosObservables());
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	@FXML
	void Salir() {
		escenario.close();
	}

	@FXML
	void nuevo() throws Exception {
		principal.cargarAcuario(TipoVentana.NUEVO, tablaAcuario, -1);
	}

	@FXML
	void ver() throws Exception {
		int indiceSeleccionado = tablaAcuario.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			principal.cargarAcuario(TipoVentana.VER, tablaAcuario, indiceSeleccionado);
		} else if (tablaAcuario.getItems().size() < 1) {

			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();

		}
	}

	@FXML
	void editar() throws Exception {
		int indiceSeleccionado = tablaAcuario.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			principal.cargarAcuario(TipoVentana.EDITAR, tablaAcuario, indiceSeleccionado);
		} else if (tablaAcuario.getItems().size() < 1) {

			mensajeTablaVacia();

		} else {
			mensajeItemNoSeleccionado();
		}
	}

	private boolean hayEspecie(int numeroAcuario) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Acuario acu = (Acuario) session.get(Acuario.class, numeroAcuario);

		if (acu.getAgrupacionEspecie().size() > 0) {
			session.getTransaction().commit();
			return true;
		} else {
			session.getTransaction().commit();
			return false;
		}

	}

	@FXML
	void borrar() {

		if (!hayEspecie(Integer.parseInt(tablaAcuario.getItems()
				.get(tablaAcuario.getSelectionModel().getSelectedIndex()).getNumero()))) {
			int indiceSeleccionado = tablaAcuario.getSelectionModel().getSelectedIndex();
			if (indiceSeleccionado >= 0) {
				Action response = Dialogs
						.create()
						.style(DialogStyle.NATIVE)
						.title("Esta a punto de borrar una acuario del Sistema")
						.message(
								"Tenga en cuenta de que si elimina el acuario se borrará permanentemente. ¿Desea continuar?")
						.showConfirm()

				;
				if (response == Dialog.Actions.YES) {

					Acuario acuario = Sistema.getSistema().buscarAcuario(
							Integer.parseInt(tablaAcuario.getItems().get(indiceSeleccionado).getNumero()));

					Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
					session2.beginTransaction();
					Acuario acua = (Acuario) session2.get(Acuario.class, acuario.getIdAcuario());

					session2.delete(acua);
					session2.getTransaction().commit();
					tablaAcuario.getItems().remove(indiceSeleccionado);
					HibernateUtil.traerDatosBase();
				}

			} else if (tablaAcuario.getItems().size() < 1) {
				mensajeTablaVacia();

			} else {
				mensajeItemNoSeleccionado();
			}

		} else {
			Utilidades.MensajeError("No se puede borrar el acuario",
					"El acuario contiene especies, para borrarlo translade las especies a otro acuario");
		}

	}

	private void mensajeTablaVacia() {
		Utilidades.MensajeWarning("Tabla Vacia",
				"La tabla no contiene datos. Para poder operar se debe contener algun valor en la tabla");
	}

	private void mensajeItemNoSeleccionado() {
		Utilidades
				.MensajeWarning(
						"Seleccion errónea",
						"No se detecta un item de la tabla seleccionado. Por favor seleccione un item de la tabla para poder realizar una  operación sobre él");
	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondeAcuarios.html");
	}

}
