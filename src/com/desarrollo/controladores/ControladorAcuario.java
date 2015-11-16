package com.desarrollo.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Acuario;
import com.desarrollo.modelo.AgrupacionEspecie;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Parametro;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.AcuarioProperty;
import com.desarrollo.property.EspecieProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorAcuario {

	@FXML
	private TableColumn<EspecieProperty, String> columnaCantidad;

	@FXML
	private TextField fieldTemp;

	@FXML
	private ImageView imagenToolbarTransferir;

	@FXML
	private TextField fieldCapacidad;

	@FXML
	private Button botonEditar;

	@FXML
	private TableColumn<EspecieProperty, String> columnaDescripcion;

	@FXML
	private DatePicker fieldFechaCreacion;

	@FXML
	private Button botonBorrar;

	@FXML
	private ImageView imagenToolbarVer;

	@FXML
	private CheckBox checkboxDisponible;

	@FXML
	private ImageView imagenToolbarEditar;

	@FXML
	private ImageView imagenToolbarAgregar;

	@FXML
	private TextField fieldNumeroAcuario;

	@FXML
	private Button botonVer;

	@FXML
	private Button botonTransferir;

	@FXML
	private Button botonAgregar;

	@FXML
	private ImageView imagenToolbarBorrar;

	@FXML
	private Button botonSalir;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private TableColumn<EspecieProperty, String> columnaNombre;

	@FXML
	private Button botonGuardar;

	@FXML
	private ToolBar toolBar;

	@FXML
	private TableView<EspecieProperty> tablaEspecies;

	@FXML
	private Tab tabEspecie;

	@FXML
	private TextField fieldTemperaturaMinima;

	@FXML
	private TextField fieldTemperaturaMaxima;

	@FXML
	private TextField fieldGhMinimo;

	@FXML
	private TextField fieldGhMaximo;

	@FXML
	private TextField fieldKhMinimo;

	@FXML
	private TextField fieldKhMaximo;

	@FXML
	private TextField fieldPhMinimo;

	@FXML
	private TextField fieldPhMaximo;

	@FXML
	private ComboBox<String> comboTipoAgua;

	private Stage escenario;

	private Principal principal;

	private TipoVentana tipoVentana;

	private TableView<AcuarioProperty> tablaAcuario;

	private int numeroAcuario;

	private List<AgrupacionEspecie> listaAgrupacionEspecie = new ArrayList<AgrupacionEspecie>();

	@FXML
	private void initialize() {
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
		columnaDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());
		columnaCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty());

		columnaNombre.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaDescripcion.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaCantidad.setStyle("-fx-alignment: CENTER-RIGHT;");
	}

	public TextField getFieldTemp() {
		return fieldTemp;
	}

	public void setFieldTemp(TextField fieldTemp) {
		this.fieldTemp = fieldTemp;
	}

	public ComboBox<String> getComboTipoAgua() {
		return comboTipoAgua;
	}

	public void setComboTipoAgua(ComboBox<String> comboTipoAgua) {
		this.comboTipoAgua = comboTipoAgua;
	}

	public TextField getFieldTemperaturaMinima() {
		return fieldTemperaturaMinima;
	}

	public void setFieldTemperaturaMinima(TextField fieldTemperaturaMinima) {
		this.fieldTemperaturaMinima = fieldTemperaturaMinima;
	}

	public TextField getFieldTemperaturaMaxima() {
		return fieldTemperaturaMaxima;
	}

	public void setFieldTemperaturaMaxima(TextField fieldTemperaturaMaxima) {
		this.fieldTemperaturaMaxima = fieldTemperaturaMaxima;
	}

	public TextField getFieldGhMinimo() {
		return fieldGhMinimo;
	}

	public void setFieldGhMinimo(TextField fieldGhMinimo) {
		this.fieldGhMinimo = fieldGhMinimo;
	}

	public TextField getFieldGhMaximo() {
		return fieldGhMaximo;
	}

	public void setFieldGhMaximo(TextField fieldGhMaximo) {
		this.fieldGhMaximo = fieldGhMaximo;
	}

	public TextField getFieldKhMinimo() {
		return fieldKhMinimo;
	}

	public void setFieldKhMinimo(TextField fieldKhMinimo) {
		this.fieldKhMinimo = fieldKhMinimo;
	}

	public TextField getFieldKhMaximo() {
		return fieldKhMaximo;
	}

	public void setFieldKhMaximo(TextField fieldKhMaximo) {
		this.fieldKhMaximo = fieldKhMaximo;
	}

	public TextField getFieldPhMinimo() {
		return fieldPhMinimo;
	}

	public void setFieldPhMinimo(TextField fieldPhMinimo) {
		this.fieldPhMinimo = fieldPhMinimo;
	}

	public TextField getFieldPhMaximo() {
		return fieldPhMaximo;
	}

	public void setFieldPhMaximo(TextField fieldPhMaximo) {
		this.fieldPhMaximo = fieldPhMaximo;
	}

	public Tab getTabEspecie() {
		return tabEspecie;
	}

	public void setTabEspecie(Tab tabEspecie) {
		this.tabEspecie = tabEspecie;
	}

	public List<AgrupacionEspecie> getListaEspecie() {
		return listaAgrupacionEspecie;
	}

	public void setListaEspecie(List<AgrupacionEspecie> listaEspecie) {
		this.listaAgrupacionEspecie = listaEspecie;
	}

	public int getNumeroAcuario() {
		return numeroAcuario;
	}

	public void setNumeroAcuario(int numeroAcuario) {
		this.numeroAcuario = numeroAcuario;
	}

	public TableView<AcuarioProperty> getTablaAcuario() {
		return tablaAcuario;
	}

	public void setTablaAcuario(TableView<AcuarioProperty> tablaAcuario) {
		this.tablaAcuario = tablaAcuario;
	}

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public ToolBar getToolBar() {
		return toolBar;
	}

	public void setToolBar(ToolBar toolBar) {
		this.toolBar = toolBar;
	}

	public ImageView getImagenToolbarAgregar() {
		return imagenToolbarAgregar;
	}

	public void setImagenToolbarAgregar(ImageView imagenToolbarAgregar) {
		this.imagenToolbarAgregar = imagenToolbarAgregar;
	}

	public Button getBotonAgregar() {
		return botonAgregar;
	}

	public void setBotonAgregar(Button botonAgregar) {
		this.botonAgregar = botonAgregar;
	}

	public TableColumn<EspecieProperty, String> getColumnaCantidad() {
		return columnaCantidad;
	}

	public void setColumnaCantidad(TableColumn<EspecieProperty, String> columnaCantidad) {
		this.columnaCantidad = columnaCantidad;
	}

	public ImageView getImagenToolbarTransferir() {
		return imagenToolbarTransferir;
	}

	public void setImagenToolbarTransferir(ImageView imagenToolbarTransferir) {
		this.imagenToolbarTransferir = imagenToolbarTransferir;
	}

	public TextField getFieldCapacidad() {
		return fieldCapacidad;
	}

	public void setFieldCapacidad(TextField fieldCapacidad) {
		this.fieldCapacidad = fieldCapacidad;
	}

	public Button getBotonEditar() {
		return botonEditar;
	}

	public void setBotonEditar(Button botonEditar) {
		this.botonEditar = botonEditar;
	}

	public TableColumn<EspecieProperty, String> getColumnaDescripcion() {
		return columnaDescripcion;
	}

	public void setColumnaDescripcion(TableColumn<EspecieProperty, String> columnaDescripcion) {
		this.columnaDescripcion = columnaDescripcion;
	}

	public DatePicker getFieldFechaCreacion() {
		return fieldFechaCreacion;
	}

	public void setFieldFechaCreacion(DatePicker fieldFechaCreacion) {
		this.fieldFechaCreacion = fieldFechaCreacion;
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

	public CheckBox getCheckboxDisponible() {
		return checkboxDisponible;
	}

	public void setCheckboxDisponible(CheckBox checkboxDisponible) {
		this.checkboxDisponible = checkboxDisponible;
	}

	public ImageView getImagenToolbarEditar() {
		return imagenToolbarEditar;
	}

	public void setImagenToolbarEditar(ImageView imagenToolbarEditar) {
		this.imagenToolbarEditar = imagenToolbarEditar;
	}

	public TextField getFieldNumeroAcuario() {
		return fieldNumeroAcuario;
	}

	public void setFieldNumeroAcuario(TextField fieldNumeroAcuario) {
		this.fieldNumeroAcuario = fieldNumeroAcuario;
	}

	public Button getBotonVer() {
		return botonVer;
	}

	public void setBotonVer(Button botonVer) {
		this.botonVer = botonVer;
	}

	public Button getBotonTransferir() {
		return botonTransferir;
	}

	public void setBotonTransferir(Button botonTransferir) {
		this.botonTransferir = botonTransferir;
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

	public TableColumn<EspecieProperty, String> getColumnaNombre() {
		return columnaNombre;
	}

	public void setColumnaNombre(TableColumn<EspecieProperty, String> columnaNombre) {
		this.columnaNombre = columnaNombre;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public TableView<EspecieProperty> getTablaEspecies() {
		return tablaEspecies;
	}

	public void setTablaEspecies(TableView<EspecieProperty> tablaEspecies) {
		this.tablaEspecies = tablaEspecies;
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaEspecies.setItems(principal.getEspeciesObservables());
	}

	@FXML
	void salir() {
		principal.getEspeciesObservables().clear();
		escenario.close();

	}

	@SuppressWarnings("deprecation")
	private boolean validarVentana() {
		boolean validada = true;

		if (fieldNumeroAcuario.getText().equals("")) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "Se necesita especificar el número de acuario");
			fieldNumeroAcuario.requestFocus();
		}

		if (validada & !Utilidades.validarEntero(fieldNumeroAcuario.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El valor ingresado debe ser un número entero");
			fieldNumeroAcuario.requestFocus();
		}

		if (validada) {

			if (tipoVentana == TipoVentana.NUEVO) {
				if (Utilidades.datePickerToDate(fieldFechaCreacion).after(
						Utilidades.siguienteDia(Utilidades.blanquearHora(new Date())))) {
					Utilidades.MensajeError("Fecha inválida",
							"La fecha no puede ser posterior a la fecha actual (" + (new Date()).getDate() + "-"
									+ ((new Date()).getMonth() + 1) + "-" + ((new Date()).getYear() + 1900) + ")");
					validada = false;
					fieldFechaCreacion.requestFocus();
				}
			}

		}

		if (validada & fieldCapacidad.getText().equals("")) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "Se necesita especificar la cantidad de agua (en litros).");
			fieldCapacidad.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldCapacidad.getText())) {
			Utilidades.MensajeError("Valor inválido", "La cantidad de agua del acuario debe ser un número");
			validada = false;
			fieldCapacidad.requestFocus();
		}

		if (validada) {
			if (Float.parseFloat(fieldCapacidad.getText()) <= 0) {
				Utilidades.MensajeError("Valor inválido",
						"Por favor especifique una cantidad de agua mayor a cero (en litros)");
				validada = false;
				fieldCapacidad.requestFocus();
			}
		}

		if (validada & tipoVentana == TipoVentana.NUEVO & comboTipoAgua.getSelectionModel().getSelectedIndex() == -1) {
			Utilidades.MensajeError("Tipo de agua vacío", "Seleccione el tipo de agua del acuario");
			validada = false;
			comboTipoAgua.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldTemperaturaMinima.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "La temperatura mínima debe ser un número");
			fieldTemperaturaMinima.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldTemperaturaMaxima.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "La temperatura máxima debe ser un número ");
			fieldTemperaturaMaxima.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldGhMinimo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Gh mínimo debe ser un número");
			fieldGhMinimo.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldGhMaximo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Gh máximo debe ser un número ");
			fieldGhMaximo.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldKhMinimo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Kh mínimo debe ser un número");
			fieldKhMinimo.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldKhMaximo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Kh máximo debe ser un número ");
			fieldKhMaximo.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldPhMinimo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Ph mínimo debe ser un número");
			fieldPhMinimo.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldPhMaximo.getText())) {
			validada = false;
			Utilidades.MensajeError("Valor inválido", "El parametro Ph máximo debe ser un número ");
			fieldPhMaximo.requestFocus();
		}

		if (validada) {
			if (Float.parseFloat(fieldTemperaturaMinima.getText()) > Float.parseFloat(fieldTemperaturaMaxima.getText())) {
				validada = false;
				Utilidades.MensajeError("Valores inválido", "La temperatura mínima no puede ser mayor a la máxima");
				fieldTemperaturaMinima.requestFocus();
			}

			if (validada & (Float.parseFloat(fieldGhMinimo.getText()) > Float.parseFloat(fieldGhMaximo.getText()))) {
				validada = false;
				Utilidades.MensajeError("Valores inválidos", "El Gh mínimo no puede ser menor al máximo");
				fieldGhMinimo.requestFocus();
			}

			if (validada & (Float.parseFloat(fieldKhMinimo.getText()) > Float.parseFloat((fieldKhMaximo.getText())))) {
				validada = false;
				Utilidades.MensajeError("Valores inválidos", "El Kh mínimo no puede ser menor al máximo");
				fieldKhMinimo.requestFocus();
			}

			if (validada & (Float.parseFloat(fieldPhMinimo.getText()) > Float.parseFloat(fieldPhMaximo.getText()))) {
				validada = false;
				Utilidades.MensajeError("Valores inválidos", "El Ph mínimo no puede ser menor al máximo");
				fieldPhMinimo.requestFocus();
			}

		}

		return validada;
	}

	@SuppressWarnings("unused")
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

	@SuppressWarnings("deprecation")
	@FXML
	void guardar() {

		if (validarVentana()) {

			boolean estaOk = true;
			if (tipoVentana == TipoVentana.NUEVO) {

				if (!Sistema.getSistema().acuarioExiste(Integer.parseInt(fieldNumeroAcuario.getText()))) {
					Acuario acuario = new Acuario();
					acuario.setIdAcuario(Integer.parseInt(fieldNumeroAcuario.getText()));
					acuario.setCapacidad(Float.parseFloat(fieldCapacidad.getText()));
					Date fecha = new Date(fieldFechaCreacion.getValue().getYear() - 1900, fieldFechaCreacion.getValue()
							.getMonthValue() - 1, fieldFechaCreacion.getValue().getDayOfMonth());
					acuario.setFechaCreacion(fecha);
					acuario.setDisponible(checkboxDisponible.isSelected());
					acuario.setTipoAgua(comboTipoAgua.getItems().get(
							comboTipoAgua.getSelectionModel().getSelectedIndex()));

					Parametro temperatura = new Parametro();
					temperatura.setNombre("Temperatura");
					temperatura.setMaximo(Float.parseFloat(fieldTemperaturaMaxima.getText()));
					temperatura.setMinimo(Float.parseFloat(fieldTemperaturaMinima.getText()));

					Parametro gh = new Parametro();
					gh.setNombre("Gh");
					gh.setMaximo(Float.parseFloat(fieldGhMaximo.getText()));
					gh.setMinimo(Float.parseFloat(fieldGhMinimo.getText()));

					Parametro kh = new Parametro();
					kh.setNombre("Kh");
					kh.setMaximo(Float.parseFloat(fieldKhMaximo.getText()));
					kh.setMinimo(Float.parseFloat(fieldKhMinimo.getText()));

					Parametro ph = new Parametro();
					ph.setNombre("Ph");
					ph.setMaximo(Float.parseFloat(fieldPhMaximo.getText()));
					ph.setMinimo(Float.parseFloat(fieldPhMinimo.getText()));

					acuario.getParametros().add(gh);
					acuario.getParametros().add(kh);
					acuario.getParametros().add(ph);
					acuario.getParametros().add(temperatura);

					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					session.save(gh);
					session.save(kh);
					session.save(ph);
					session.save(temperatura);
					session.save(acuario);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_ACUARIO) + acuario.getIdAcuario());
					estaOk = true;
				} else {
					Dialogs.create().title("Acuario existente").style(DialogStyle.NATIVE)
							.masthead("El número de acuario que indicó esta siendo actualmente usado")
							.message("Ingrese otro número de acuario").showError();
					fieldNumeroAcuario.focusedProperty();
					estaOk = false;
					fieldNumeroAcuario.requestFocus();
				}

			} else if (tipoVentana == TipoVentana.EDITAR) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();

				Acuario acua = (Acuario) session.get(Acuario.class, Integer.parseInt(fieldNumeroAcuario.getText()));

				acua.setDisponible(checkboxDisponible.isSelected());
				acua.setCapacidad(Float.parseFloat(fieldCapacidad.getText()));
				for (int i = 0; i < listaAgrupacionEspecie.size(); i++) {
					if (listaAgrupacionEspecie.get(i).getIdAgrupacion() == 0) {
						AgrupacionEspecie agrup = new AgrupacionEspecie();
						agrup.setCantidad(listaAgrupacionEspecie.get(i).getCantidad());
						agrup.setEspecie(listaAgrupacionEspecie.get(i).getEspecie());
						session.save(agrup);
						acua.getAgrupacionEspecie().add(agrup);
					} else {
						AgrupacionEspecie agru = (AgrupacionEspecie) session.get(AgrupacionEspecie.class,
								listaAgrupacionEspecie.get(i).getIdAgrupacion());
						agru.setCantidad(listaAgrupacionEspecie.get(i).getCantidad());
						session.update(agru);
					}
					Especie especie = (Especie) session.get(Especie.class, listaAgrupacionEspecie.get(i).getEspecie()
							.getIdEspecie());
					especie.calcularTotal();
					session.update(especie);
				}

				session.update(acua);
				session.getTransaction().commit();
			}

			HibernateUtil.traerDatosBase();
			principal.getAcuariosObservables().clear();
			principal.setAcuariosObservables(Sistema.getSistema().pasarArrayAcuariosAProperty(
					Sistema.getSistema().getAcuarios()));
			tablaAcuario.getItems().setAll(principal.getAcuariosObservables());
			if (estaOk) {
				salir();
			}

		}

	}

	@FXML
	void agregarEspecie() throws Exception {
		principal.cargarAgrupacionEspecie(numeroAcuario, listaAgrupacionEspecie, TipoVentana.NUEVO, -1);
		actualizarEspecie();
	}

	@FXML
	void verEspecie() throws Exception {
		int indiceSeleccionado = tablaEspecies.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			principal.cargarAgrupacionEspecie(numeroAcuario, listaAgrupacionEspecie, TipoVentana.VER,
					indiceSeleccionado);

		} else if (tablaEspecies.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder ver una especie la tabla debe contener datos").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Error de Selección")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione una especie").showWarning();
		}
	}

	@FXML
	void borrarEspecie() throws Exception {
		int indiceSeleccionado = tablaEspecies.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			Action response = Dialogs.create().style(DialogStyle.NATIVE)
					.title("Esta a punto de borrar la especie del acuario")
					.message("Tenga en cuenta de que si elimina la especie se borrará del acuario. ¿Desea continuar?")
					.showConfirm();

			if (response == Dialog.Actions.YES) {
				int id = -1;
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				AgrupacionEspecie agrEsp = (AgrupacionEspecie) session.get(AgrupacionEspecie.class,
						listaAgrupacionEspecie.get(indiceSeleccionado).getIdAgrupacion());
				id = agrEsp.getEspecie().getIdEspecie();
				session.delete(agrEsp);

				listaAgrupacionEspecie.remove(indiceSeleccionado);
				tablaEspecies.getItems().remove(indiceSeleccionado);
				session.getTransaction().commit();

				Sistema.getSistema().actualizarCantidadEspecie(id);
				HibernateUtil.traerDatosBase();
				principal.getEspeciesObservables().clear();
				principal.setEspeciesObservables(Sistema.getSistema().pasarArrayEspecieAProperty(
						Sistema.getSistema().getEspecies()));

			}

		} else if (tablaEspecies.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder borrar una especie la tabla debe contener datos").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Error de Selección")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione una especie").showWarning();
		}
	}

	@FXML
	void transferirEspecie() throws Exception {
		int indiceSeleccionado = tablaEspecies.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			principal.trasnferirEspecie(this.numeroAcuario, tablaEspecies.getItems().get(indiceSeleccionado)
					.getNombre(), indiceSeleccionado, listaAgrupacionEspecie);
			actualizarEspecie();

		} else if (tablaEspecies.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder transferir una especie la tabla debe contener datos").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Error de Selección")
					.masthead("No se a detectado una especie seleccionada")
					.message("Por favor seleccionela y intente de nuevo").showWarning();
		}
	}

	@FXML
	void editarEspecie() throws Exception {
		int indiceSeleccionado = tablaEspecies.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			principal.cargarAgrupacionEspecie(numeroAcuario, listaAgrupacionEspecie, TipoVentana.EDITAR,
					indiceSeleccionado);
			actualizarEspecie();
		} else if (tablaEspecies.getItems().size() < 1) {

			Dialogs.create().style(DialogStyle.NATIVE).title("Tabla Vacia").masthead("La tabla no contiene datos")
					.message("Para poder editar una especie la tabla debe contener datos").showWarning();
		} else {
			Dialogs.create().style(DialogStyle.NATIVE).title("Error de Selección")
					.masthead("No se detecta un item de la tabla seleccionado")
					.message("Por favor seleccione una especie").showWarning();
		}
	}

	private void actualizarEspecie() {
		tablaEspecies.getItems().clear();
		for (int i = 0; i < listaAgrupacionEspecie.size(); i++) {
			tablaEspecies.getItems().add(
					new EspecieProperty(listaAgrupacionEspecie.get(i).getEspecie().getNombre(), listaAgrupacionEspecie
							.get(i).getEspecie().getDescripcion(), Integer.toString(listaAgrupacionEspecie.get(i)
							.getCantidad())));
		}
	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/SecciondeAcuarios.html");
	}

}
