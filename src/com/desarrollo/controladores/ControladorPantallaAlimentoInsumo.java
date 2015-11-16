package com.desarrollo.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Insumo;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.AlimentoInsumoProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorPantallaAlimentoInsumo {

	@FXML
	private ToolBar toolBar;

	@FXML
	private Button botonEditar;

	@FXML
	private ImageView imagenToolbarNuevo;

	@FXML
	private Button botonBorrar;

	@FXML
	private TableColumn<AlimentoInsumoProperty, String> columnaCantidadDisponible;

	@FXML
	private Button botonNuevo;

	@FXML
	private TableColumn<AlimentoInsumoProperty, String> columnaCantidadMinima;

	@FXML
	private ImageView imagenToolbarVer;

	@FXML
	private TableView<AlimentoInsumoProperty> tablaAlimentoInsumo;

	@FXML
	private ImageView imagenToolbarEditar;

	@FXML
	private Button botonVer;

	@FXML
	private ImageView imagenToolbarBorrar;

	@FXML
	private Button botonSalir;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private TableColumn<AlimentoInsumoProperty, String> columnaNombre;

	@FXML
	private TableColumn<AlimentoInsumoProperty, String> columnaCantidadMaxima;

	private Principal principal;

	private Stage escenario;

	private TipoRecurso tipoRecurso;

	@FXML
	private void initialize() {
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaCantidadDisponible.setCellValueFactory(cellData -> cellData.getValue().cantidadDisponible());
		columnaCantidadMaxima.setCellValueFactory(cellData -> cellData.getValue().cantidadMaxima());
		columnaCantidadMinima.setCellValueFactory(cellData -> cellData.getValue().cantidadMinima());

		columnaCantidadDisponible.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaCantidadMaxima.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaCantidadMinima.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaNombre.setStyle("-fx-alignment: CENTER;");

	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public TableView<AlimentoInsumoProperty> getTablaAlimentoInsumo() {
		return tablaAlimentoInsumo;
	}

	public void setTablaAlimentoInsumo(TableView<AlimentoInsumoProperty> tablaAlimentoInsumo) {
		this.tablaAlimentoInsumo = tablaAlimentoInsumo;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
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

	public TableColumn<AlimentoInsumoProperty, String> getColumnaCantidadDisponible() {
		return columnaCantidadDisponible;
	}

	public void setColumnaCantidadDisponible(TableColumn<AlimentoInsumoProperty, String> columnaCantidadDisponible) {
		this.columnaCantidadDisponible = columnaCantidadDisponible;
	}

	public Button getBotonNuevo() {
		return botonNuevo;
	}

	public void setBotonNuevo(Button botonNuevo) {
		this.botonNuevo = botonNuevo;
	}

	public TableColumn<AlimentoInsumoProperty, String> getColumnaCantidadMinima() {
		return columnaCantidadMinima;
	}

	public void setColumnaCantidadMinima(TableColumn<AlimentoInsumoProperty, String> columnaCantidadMinima) {
		this.columnaCantidadMinima = columnaCantidadMinima;
	}

	public ImageView getImagenToolbarVer() {
		return imagenToolbarVer;
	}

	public void setImagenToolbarVer(ImageView imagenToolbarVer) {
		this.imagenToolbarVer = imagenToolbarVer;
	}

	public TableView<AlimentoInsumoProperty> getTabllaAlimentoInsumo() {
		return tablaAlimentoInsumo;
	}

	public void setTabllaAlimentoInsumo(TableView<AlimentoInsumoProperty> tabllaAlimentoInsumo) {
		this.tablaAlimentoInsumo = tabllaAlimentoInsumo;
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

	public TableColumn<AlimentoInsumoProperty, String> getColumnaNombre() {
		return columnaNombre;
	}

	public void setColumnaNombre(TableColumn<AlimentoInsumoProperty, String> columnaNombre) {
		this.columnaNombre = columnaNombre;
	}

	public TableColumn<AlimentoInsumoProperty, String> getColumnaCantidadMaxima() {
		return columnaCantidadMaxima;
	}

	public void setColumnaCantidadMaxima(TableColumn<AlimentoInsumoProperty, String> columnaCantidadMaxima) {
		this.columnaCantidadMaxima = columnaCantidadMaxima;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaAlimentoInsumo.setItems(principal.getAlimentoInsumoObservables());
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	@FXML
	void nuevo() throws Exception {
		principal.cargarAlimentoInsumo(this.tipoRecurso, TipoVentana.NUEVO, tablaAlimentoInsumo, -1);
	}

	@FXML
	void ver() throws Exception {

		int indiceSeleccionado = tablaAlimentoInsumo.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			String nombre = tablaAlimentoInsumo.getItems().get(indiceSeleccionado).getNombre();
			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				Alimento al = Sistema.getSistema().buscarAlimento(nombre);
				principal.cargarAlimentoInsumo(this.tipoRecurso, TipoVentana.VER, tablaAlimentoInsumo,
						al.getIdAlimento());
			} else {
				Insumo ins = Sistema.getSistema().buscarInsumo(nombre);
				principal.cargarAlimentoInsumo(this.tipoRecurso, TipoVentana.VER, tablaAlimentoInsumo,
						ins.getIdInsumo());
			}

		} else if (tablaAlimentoInsumo.getItems().size() < 1) {

			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();

		}

	}

	@FXML
	void editar() throws Exception {
		int indiceSeleccionado = tablaAlimentoInsumo.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			String nombre = tablaAlimentoInsumo.getItems().get(indiceSeleccionado).getNombre();
			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				Alimento al = Sistema.getSistema().buscarAlimento(nombre);
				principal.cargarAlimentoInsumo(this.tipoRecurso, TipoVentana.EDITAR, tablaAlimentoInsumo,
						al.getIdAlimento());
			} else {
				Insumo ins = Sistema.getSistema().buscarInsumo(nombre);
				principal.cargarAlimentoInsumo(this.tipoRecurso, TipoVentana.EDITAR, tablaAlimentoInsumo,
						ins.getIdInsumo());
			}

		} else if (tablaAlimentoInsumo.getItems().size() < 1) {
			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();

		}

	}

	@FXML
	void borrar() {
		int indiceSeleccionado = tablaAlimentoInsumo.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			// tengo un items seleccionado y listo para borrar -> preguntar
			// confirmacion

			Action response = Dialogs.create().style(DialogStyle.NATIVE).title("Atención!")
					.message("Está a punto de borrar un " + tipoRecurso.toString() + " del sistema. Desea Continuar?")
					.showConfirm();

			if (response == Dialog.Actions.YES) {

				String nombre = tablaAlimentoInsumo.getItems().get(indiceSeleccionado).getNombre();
				if (tipoRecurso == TipoRecurso.ALIMENTO) {
					Alimento al = Sistema.getSistema().buscarAlimento(nombre);
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Alimento alim = (Alimento) session.get(Alimento.class, al.getIdAlimento());
					session.delete(alim);
					
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.BORRAR_ALIMENTO) + alim.getNombre());
					tablaAlimentoInsumo.getItems().remove(indiceSeleccionado);
					HibernateUtil.traerDatosBase();
					principal.getAlimentoInsumoObservables().clear();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayAlimentosAProperty(
							Sistema.getSistema().getAlimentos()));

				} else {
					Insumo ins = Sistema.getSistema().buscarInsumo(nombre);
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Insumo insumo = (Insumo) session.get(Insumo.class, ins.getIdInsumo());
					session.delete(insumo);
					
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.BORRAR_INSUMO) + insumo.getNombre());

					tablaAlimentoInsumo.getItems().remove(indiceSeleccionado);
					HibernateUtil.traerDatosBase();
					principal.getAlimentoInsumoObservables().clear();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayInsumosAProperty(
							Sistema.getSistema().getInsumos()));
				}

			}

		} else if (tablaAlimentoInsumo.getItems().size() < 1) {
			// mensaje de que no hay datos en la tabla
			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();
		}

		tablaAlimentoInsumo.setItems(principal.getAlimentoInsumoObservables());
	}

	private void mensajeTablaVacia() {
		Utilidades.MensajeWarning("Tabla Vacia",
				"La tabla no contiene datos. Para poder operar se debe contener algun valor en la tabla");
	}

	private void mensajeItemNoSeleccionado() {
		Utilidades
				.MensajeWarning(
						"Seleccion errónea",
						"No se detecta un item de la tabla seleccionado. Por favor seleccione un item de la tabla para poder realizar una operación sobre él");
	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondeRecursos.html");
	}

}
