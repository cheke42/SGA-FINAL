package com.desarrollo.controladores;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoMedidaUnidad;
import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Insumo;
import com.desarrollo.modelo.PaqueteAlimento;
import com.desarrollo.modelo.PaqueteInsumo;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.AlimentoInsumoProperty;
import com.desarrollo.property.PaqueteProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ControladorAlimentoInsumo {

	@FXML
	private TableColumn<PaqueteProperty, String> columnaCantidad;

	@FXML
	private TableColumn<PaqueteProperty, String> columnaFechaVencimiento;

	@FXML
	private Button botonBorrarPaquete;

	@FXML
	private ComboBox<String> comboEstadoMaterial;

	@FXML
	private ComboBox<String> comboTipoUnidad;

	@FXML
	private Label labelUnidadCantidadMaxima;

	@FXML
	private Label labelUnidadCantidadMinima;

	@FXML
	private TextField fieldCantidadMaxima;

	@FXML
	private Button botonVerPaquete;

	@FXML
	private TableView<PaqueteProperty> tablaPaquete;

	@FXML
	private Button botonEditarPaquete;

	@FXML
	private ImageView imagenToolbarBorrarPaquete;

	@FXML
	private TableColumn<PaqueteProperty, String> columnaFechaAdquisicion;

	@FXML
	private Button botonNuevoPaquete;

	@FXML
	private ToolBar toolbar;

	@FXML
	private TableColumn<PaqueteProperty, String> columnaNumeroPaquete;

	@FXML
	private ImageView imagenInsumoPaquete;

	@FXML
	private TextField fieldNombre;

	@FXML
	private ImageView imagenToolbarNuevoPaquete;

	@FXML
	private TextField fieldCantidadDisponible;

	@FXML
	private TextField fieldCantidadMinima;

	@FXML
	private Button botonSalir;

	@FXML
	private TableColumn<PaqueteProperty, String> columnaEstado;

	@FXML
	private ImageView imagenToolbarEditarPaquete;

	@FXML
	private ImageView imagenToolbarSalir;

	@FXML
	private ImageView imagenToolbarVerPaquete;

	@FXML
	private Button botonGuardar;

	@FXML
	private Label textoWarning;

	@FXML
	private ImageView imagenWarning;

	@FXML
	private void initialize() {
		columnaNumeroPaquete.setCellValueFactory(cellData -> cellData.getValue().idPaquete());
		columnaFechaAdquisicion.setCellValueFactory(cellData -> cellData.getValue().fechaAdquisicion());
		columnaFechaVencimiento.setCellValueFactory(cellData -> cellData.getValue().fechaVencimiento());
		columnaCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidad());
		columnaEstado.setCellValueFactory(cellData -> cellData.getValue().disponible());

		columnaNumeroPaquete.setStyle("-fx-alignment: CENTER;");
		columnaFechaAdquisicion.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaFechaVencimiento.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaCantidad.setStyle("-fx-alignment: CENTER-RIGHT;");
		columnaEstado.setStyle("-fx-alignment: CENTER;");

	}

	private Principal principal;
	private Stage escenario;
	private TipoVentana tipoVentana;
	private TipoRecurso tipoRecurso;
	private int id;
	private TableView<AlimentoInsumoProperty> tablaAlimentoInsumo;

	private List<PaqueteAlimento> listaPaqueteAlimento = new ArrayList<PaqueteAlimento>();
	private List<PaqueteInsumo> listaPaqueteInsumo = new ArrayList<PaqueteInsumo>();

	public Label getLabelUnidadCantidadMaxima() {
		return labelUnidadCantidadMaxima;
	}

	public void setLabelUnidadCantidadMaxima(Label labelUnidadCantidadMaxima) {
		this.labelUnidadCantidadMaxima = labelUnidadCantidadMaxima;
	}

	public Label getLabelUnidadCantidadMinima() {
		return labelUnidadCantidadMinima;
	}

	public void setLabelUnidadCantidadMinima(Label labelUnidadCantidadMinima) {
		this.labelUnidadCantidadMinima = labelUnidadCantidadMinima;
	}

	public Label getTextoWarning() {
		return textoWarning;
	}

	public void setTextoWarning(Label textoWarning) {
		this.textoWarning = textoWarning;
	}

	public ImageView getImagenWarning() {
		return imagenWarning;
	}

	public void setImagenWarning(ImageView imagenWarning) {
		this.imagenWarning = imagenWarning;
	}

	public List<PaqueteAlimento> getListaPaqueteAlimento() {
		return listaPaqueteAlimento;
	}

	public void setListaPaqueteAlimento(List<PaqueteAlimento> listaPaqueteAlimento) {
		this.listaPaqueteAlimento = listaPaqueteAlimento;
	}

	public List<PaqueteInsumo> getListaPaqueteInsumo() {
		return listaPaqueteInsumo;
	}

	public void setListaPaqueteInsumo(List<PaqueteInsumo> listaPaqueteInsumo) {
		this.listaPaqueteInsumo = listaPaqueteInsumo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TableColumn<PaqueteProperty, String> getColumnaCantidad() {
		return columnaCantidad;
	}

	public void setColumnaCantidad(TableColumn<PaqueteProperty, String> columnaCantidad) {
		this.columnaCantidad = columnaCantidad;
	}

	public TableColumn<PaqueteProperty, String> getColumnaFechaVencimiento() {
		return columnaFechaVencimiento;
	}

	public void setColumnaFechaVencimiento(TableColumn<PaqueteProperty, String> columnaFechaVencimiento) {
		this.columnaFechaVencimiento = columnaFechaVencimiento;
	}

	public Button getBotonBorrarPaquete() {
		return botonBorrarPaquete;
	}

	public void setBotonBorrarPaquete(Button botonBorrarPaquete) {
		this.botonBorrarPaquete = botonBorrarPaquete;
	}

	public TextField getFieldCantidadMaxima() {
		return fieldCantidadMaxima;
	}

	public void setFieldCantidadMaxima(TextField fieldCantidadMaxima) {
		this.fieldCantidadMaxima = fieldCantidadMaxima;
	}

	public Button getBotonVerPaquete() {
		return botonVerPaquete;
	}

	public void setBotonVerPaquete(Button botonVerPaquete) {
		this.botonVerPaquete = botonVerPaquete;
	}

	public TableView<PaqueteProperty> getTablaPaquete() {
		return tablaPaquete;
	}

	public void setTablaPaquete(TableView<PaqueteProperty> tablaPaquete) {
		this.tablaPaquete = tablaPaquete;
	}

	public Button getBotonEditarPaquete() {
		return botonEditarPaquete;
	}

	public void setBotonEditarPaquete(Button botonEditarPaquete) {
		this.botonEditarPaquete = botonEditarPaquete;
	}

	public ImageView getImagenToolbarBorrarPaquete() {
		return imagenToolbarBorrarPaquete;
	}

	public void setImagenToolbarBorrarPaquete(ImageView imagenToolbarBorrarPaquete) {
		this.imagenToolbarBorrarPaquete = imagenToolbarBorrarPaquete;
	}

	public TableColumn<PaqueteProperty, String> getColumnaFechaAdquisicion() {
		return columnaFechaAdquisicion;
	}

	public void setColumnaFechaAdquisicion(TableColumn<PaqueteProperty, String> columnaFechaAdquisicion) {
		this.columnaFechaAdquisicion = columnaFechaAdquisicion;
	}

	public Button getBotonNuevoPaquete() {
		return botonNuevoPaquete;
	}

	public void setBotonNuevoPaquete(Button botonNuevoPaquete) {
		this.botonNuevoPaquete = botonNuevoPaquete;
	}

	public ToolBar getToolbar() {
		return toolbar;
	}

	public void setToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	public TableColumn<PaqueteProperty, String> getColumnaNumeroPaquete() {
		return columnaNumeroPaquete;
	}

	public void setColumnaNumeroPaquete(TableColumn<PaqueteProperty, String> columnaNumeroPaquete) {
		this.columnaNumeroPaquete = columnaNumeroPaquete;
	}

	public ImageView getImagenInsumoPaquete() {
		return imagenInsumoPaquete;
	}

	public void setImagenInsumoPaquete(ImageView imagenInsumoPaquete) {
		this.imagenInsumoPaquete = imagenInsumoPaquete;
	}

	public TextField getFieldNombre() {
		return fieldNombre;
	}

	public void setFieldNombre(TextField fieldNombre) {
		this.fieldNombre = fieldNombre;
	}

	public ImageView getImagenToolbarNuevoPaquete() {
		return imagenToolbarNuevoPaquete;
	}

	public void setImagenToolbarNuevoPaquete(ImageView imagenToolbarNuevoPaquete) {
		this.imagenToolbarNuevoPaquete = imagenToolbarNuevoPaquete;
	}

	public TextField getFieldCantidadDisponible() {
		return fieldCantidadDisponible;
	}

	public void setFieldCantidadDisponible(TextField fieldCantidadDisponible) {
		this.fieldCantidadDisponible = fieldCantidadDisponible;
	}

	public TextField getFieldCantidadMinima() {
		return fieldCantidadMinima;
	}

	public void setFieldCantidadMinima(TextField fieldCantidadMinima) {
		this.fieldCantidadMinima = fieldCantidadMinima;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public TableColumn<PaqueteProperty, String> getColumnaEstado() {
		return columnaEstado;
	}

	public void setColumnaEstado(TableColumn<PaqueteProperty, String> columnaEstado) {
		this.columnaEstado = columnaEstado;
	}

	public ImageView getImagenToolbarEditarPaquete() {
		return imagenToolbarEditarPaquete;
	}

	public void setImagenToolbarEditarPaquete(ImageView imagenToolbarEditarPaquete) {
		this.imagenToolbarEditarPaquete = imagenToolbarEditarPaquete;
	}

	public ImageView getImagenToolbarSalir() {
		return imagenToolbarSalir;
	}

	public void setImagenToolbarSalir(ImageView imagenToolbarSalir) {
		this.imagenToolbarSalir = imagenToolbarSalir;
	}

	public ImageView getImagenToolbarVerPaquete() {
		return imagenToolbarVerPaquete;
	}

	public void setImagenToolbarVerPaquete(ImageView imagenToolbarVerPaquete) {
		this.imagenToolbarVerPaquete = imagenToolbarVerPaquete;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
		tablaPaquete.setItems(principal.getPaqueteObservables());
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public ComboBox<String> getComboEstadoMaterial() {
		return comboEstadoMaterial;
	}

	public void setComboEstadoMaterial(ComboBox<String> comboEstadoMaterial) {
		this.comboEstadoMaterial = comboEstadoMaterial;
	}

	public ComboBox<String> getComboTipoUnidad() {
		return comboTipoUnidad;
	}

	public void setComboTipoUnidad(ComboBox<String> comboTipoUnidad) {
		this.comboTipoUnidad = comboTipoUnidad;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public TableView<AlimentoInsumoProperty> getTablaAlimentoInsumo() {
		return tablaAlimentoInsumo;
	}

	public void setTablaAlimentoInsumo(TableView<AlimentoInsumoProperty> tabllaAlimentoInsumo) {
		this.tablaAlimentoInsumo = tabllaAlimentoInsumo;
	}

	@SuppressWarnings("rawtypes")
	@FXML
	void cambiarUnidad(ActionEvent event) {
		ComboBox combo = (ComboBox) event.getSource();

		if (combo.getSelectionModel().getSelectedIndex() > -1) {

			labelUnidadCantidadMaxima.setVisible(true);
			labelUnidadCantidadMinima.setVisible(true);

			labelUnidadCantidadMaxima.setText(Utilidades.miniUnidad.get(combo.getItems().get(
					combo.getSelectionModel().getSelectedIndex())));

			labelUnidadCantidadMinima.setText(Utilidades.miniUnidad.get(combo.getItems().get(
					combo.getSelectionModel().getSelectedIndex())));

		}
	}

	@FXML
	void salir() {
		escenario.close();
		principal.getPaqueteObservables().clear();
	}

	private void warningVisible(boolean visible) {
		imagenWarning.setVisible(visible);
		textoWarning.setVisible(visible);
	}

	private boolean validarVentana() {
		boolean validada = true;
		warningVisible(false);

		if (fieldNombre.getText().equals("")) {
			// Esta en blanco
			warningVisible(true);
			imagenWarning.setLayoutY(fieldNombre.getLayoutY());
			textoWarning.setLayoutY(fieldNombre.getLayoutY() + 6f);
			imagenWarning.setLayoutX(fieldNombre.getWidth() + fieldNombre.getLayoutX() + 4f);
			textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
			textoWarning.setText("Campo obligatorio");
			validada = false;
			fieldNombre.requestFocus();
		}

		if (validada & fieldCantidadMinima.getText().equals("")) {
			warningVisible(true);
			imagenWarning.setLayoutY(fieldCantidadMinima.getLayoutY());
			textoWarning.setLayoutY(fieldCantidadMinima.getLayoutY() + 6f);
			imagenWarning.setLayoutX(fieldCantidadMinima.getWidth() + fieldCantidadMinima.getLayoutX() + 4f);
			textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
			textoWarning.setText("Campo obligatorio");
			validada = false;
			fieldCantidadMinima.requestFocus();
		}

		if (validada & fieldCantidadMaxima.getText().equals("")) {
			warningVisible(true);
			imagenWarning.setLayoutY(fieldCantidadMaxima.getLayoutY());
			textoWarning.setLayoutY(fieldCantidadMaxima.getLayoutY() + 6f);
			imagenWarning.setLayoutX(fieldCantidadMaxima.getWidth() + fieldCantidadMaxima.getLayoutX() + 4f);
			textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
			textoWarning.setText("Campo obligatorio");
			validada = false;
			fieldCantidadMaxima.requestFocus();
		}

		if (validada & !Utilidades.validarFlotante(fieldCantidadMinima.getText())) {
			Utilidades.MensajeError("Valor incorrecto!", "El valor ingresado en cantidad mínima debe ser un numero");
			fieldCantidadMinima.requestFocus();
			imagenWarning.setVisible(true);
			imagenWarning.setLayoutY(fieldCantidadMinima.getLayoutY());
			imagenWarning.setLayoutX(fieldCantidadMinima.getWidth() + fieldCantidadMinima.getLayoutX() + 4f);
			validada = false;
		}

		if (validada & !Utilidades.validarFlotante(fieldCantidadMaxima.getText())) {

			Utilidades.MensajeError("Valor incorrecto!", "El valor ingresado en cantidad máxima debe ser un numero");
			fieldCantidadMaxima.requestFocus();
			imagenWarning.setVisible(true);
			imagenWarning.setLayoutY(fieldCantidadMaxima.getLayoutY());
			imagenWarning.setLayoutX(fieldCantidadMaxima.getWidth() + fieldCantidadMaxima.getLayoutX() + 4f);

			validada = false;
		}

		if (validada) {
			if ((Float.parseFloat(fieldCantidadMinima.getText()) > Float.parseFloat(fieldCantidadMaxima.getText()))) {
				Utilidades.MensajeError("Error en la cantidad",
						"Los valores ingresados en cantidad mínima son mayor que la cantidad máxima");
				fieldCantidadMinima.requestFocus();
				validada = false;
			}

		}

		if (validada & tipoVentana == TipoVentana.NUEVO
				& (comboEstadoMaterial.getSelectionModel().getSelectedIndex() < 0)) {
			Utilidades.MensajeWarning("Seleccción errónea", "Por favor seleccione un estado");
			comboEstadoMaterial.requestFocus();
			validada = false;
		}

		if (validada & tipoVentana == TipoVentana.NUEVO & (comboTipoUnidad.getSelectionModel().getSelectedIndex() < 0)) {
			Utilidades.MensajeWarning("Seleccción errónea", "Por favor seleccione una unidad de medida");
			comboTipoUnidad.requestFocus();
			validada = false;
		}

		if (tipoVentana == TipoVentana.NUEVO) {
			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				if (Sistema.getSistema().alimentoExiste(fieldNombre.getText())) {
					warningVisible(true);
					imagenWarning.setLayoutY(fieldNombre.getLayoutY());
					textoWarning.setLayoutY(fieldNombre.getLayoutY() + 6f);
					imagenWarning.setLayoutX(fieldNombre.getLayoutX() + fieldNombre.getWidth() + 4f);
					textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
					textoWarning.setText("Nombre ya utilizado");
					validada = false;
					Utilidades.MensajeError("Nombre inválido", "El nombre del alimento ya se encuentra utilizado");
					fieldNombre.requestFocus();

				}
			} else if (tipoRecurso == TipoRecurso.INSUMO) {
				if (Sistema.getSistema().insumoExiste(fieldNombre.getText())) {
					imagenWarning.setLayoutY(fieldNombre.getLayoutY());
					textoWarning.setLayoutY(fieldNombre.getLayoutY() + 6f);
					imagenWarning.setLayoutX(fieldNombre.getLayoutX() + fieldNombre.getWidth() + 4f);
					textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
					textoWarning.setText("Nombre ya utilizado");
					validada = false;
					Utilidades.MensajeError("Nombre inválido", "El nombre del alimento ya se encuentra utilizado");
					fieldNombre.requestFocus();
				}

			}
		}

		return validada;
	}

	@FXML
	void cambiarTipoUnidad(ActionEvent event) {
		@SuppressWarnings("rawtypes")
		ComboBox combo = (ComboBox) event.getSource();

		switch ((String) combo.getItems().get(combo.getSelectionModel().getSelectedIndex())) {

		case "LIQUIDO":
			comboTipoUnidad.getItems().clear();
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.MILILITROS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.CENTILITROS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.LITROS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.DECALITROS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.HECTOLITROS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.KILOLITROS.toString());

			break;

		case "SOLIDO":
			comboTipoUnidad.getItems().clear();
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.MILIGRAMOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.GRRAMOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.KILOGRAMOS.toString());

			break;

		case "GASEOSO":
			comboTipoUnidad.getItems().clear();
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.MILIMETROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.DECIMETROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.CENTIMETROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.METROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.DECAMETROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.HECTOMETROSCUBICOS.toString());
			comboTipoUnidad.getItems().add(TipoMedidaUnidad.KILOMETROSCUBICOS.toString());

			break;

		}

	}

	@FXML
	void guardar() {
		if (validarVentana()) {
			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				if (tipoVentana == TipoVentana.NUEVO) {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Alimento alim = new Alimento();
					alim.setNombre(fieldNombre.getText());
					alim.setCantidadDisponible(Float.parseFloat(fieldCantidadDisponible.getText()));
					alim.setCantidadMaxima(Float.parseFloat(fieldCantidadMaxima.getText()));
					alim.setCantidadMinima(Float.parseFloat(fieldCantidadMinima.getText()));
					alim.setEstadoMaterial(comboEstadoMaterial.getItems()
							.get(comboEstadoMaterial.getSelectionModel().getSelectedIndex()).toString());
					alim.setUnidadMedida(comboTipoUnidad.getItems()
							.get(comboTipoUnidad.getSelectionModel().getSelectedIndex()).toString());
					session.save(alim);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_ALIMENTO) + alim.getNombre());

					principal.getAlimentoInsumoObservables().clear();
					HibernateUtil.traerDatosBase();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayAlimentosAProperty(
							Sistema.getSistema().getAlimentos()));

				} else if (tipoVentana == TipoVentana.EDITAR) {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Alimento alimento = (Alimento) session.get(Alimento.class, this.id);

					if (listaPaqueteAlimento.size() > 0) {

						for (int i = 0; i < listaPaqueteAlimento.size(); i++) {
							if (listaPaqueteAlimento.get(i).getIdPaqueteAlimento() == 0) {
								PaqueteAlimento pa = new PaqueteAlimento();
								pa.setCantidad(listaPaqueteAlimento.get(i).getCantidad());
								pa.setFechaAdquisicion(listaPaqueteAlimento.get(i).getFechaAdquisicion());
								pa.setFechaVencimiento(listaPaqueteAlimento.get(i).getFechaVencimiento());
								pa.setDisponible(true);
								session.save(pa);
								alimento.getStockAlimento().add(pa);
							} else {
								PaqueteAlimento pae = (PaqueteAlimento) session.get(PaqueteAlimento.class,
										listaPaqueteAlimento.get(i).getIdPaqueteAlimento());
								pae.setCantidad(listaPaqueteAlimento.get(i).getCantidad());
								pae.setFechaAdquisicion(listaPaqueteAlimento.get(i).getFechaAdquisicion());
								pae.setFechaVencimiento(listaPaqueteAlimento.get(i).getFechaVencimiento());
								session.update(pae);
							}
						}

					}

					alimento.setNombre(fieldNombre.getText());
					alimento.setCantidadDisponible(Float.parseFloat(fieldCantidadDisponible.getText()));
					alimento.setCantidadMinima(Float.parseFloat(fieldCantidadMinima.getText()));
					alimento.setCantidadMaxima(Float.parseFloat(fieldCantidadMaxima.getText()));
					alimento.actualizarStock();

					session.update(alimento);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.MODIFICAR_ALIMENTO) + alimento.getNombre());

					principal.getAlimentoInsumoObservables().clear();
					HibernateUtil.traerDatosBase();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayAlimentosAProperty(
							Sistema.getSistema().getAlimentos()));

				}
			} else if (tipoRecurso == TipoRecurso.INSUMO) {
				if (tipoVentana == TipoVentana.NUEVO) {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Insumo ins = new Insumo();
					ins.setNombre(fieldNombre.getText());
					ins.setCantidadDisponible(Float.parseFloat(fieldCantidadDisponible.getText()));
					ins.setCantidadMaxima(Float.parseFloat(fieldCantidadMaxima.getText()));
					ins.setCantidadMinima(Float.parseFloat(fieldCantidadMinima.getText()));
					ins.setEstadoMaterial(comboEstadoMaterial.getItems().get(
							comboEstadoMaterial.getSelectionModel().getSelectedIndex()));
					ins.setUnidadMedida(comboTipoUnidad.getItems().get(
							comboTipoUnidad.getSelectionModel().getSelectedIndex()));
					session.save(ins);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_INSUMO) + ins.getNombre());
					principal.getAlimentoInsumoObservables().clear();
					HibernateUtil.traerDatosBase();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayInsumosAProperty(
							Sistema.getSistema().getInsumos()));
				} else if (tipoVentana == TipoVentana.EDITAR) {
					// codigo editar insumo
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					Insumo insumo = (Insumo) session.get(Insumo.class, this.id);
					if (listaPaqueteInsumo.size() > 0) {
						for (int i = 0; i < listaPaqueteInsumo.size(); i++) {
							if (listaPaqueteInsumo.get(i).getIdPaqueteInsumo() == 0) {
								PaqueteInsumo pa = new PaqueteInsumo();
								pa.setCantidad(listaPaqueteInsumo.get(i).getCantidad());
								pa.setDisponible(true);
								pa.setFechaAdquisicion(listaPaqueteInsumo.get(i).getFechaAdquisicion());
								pa.setFechaCaducacion(listaPaqueteInsumo.get(i).getFechaCaducacion());
								session.save(pa);
								insumo.getStockInsumos().add(pa);
							} else {
								PaqueteInsumo pie = (PaqueteInsumo) session.get(PaqueteInsumo.class, listaPaqueteInsumo
										.get(i).getIdPaqueteInsumo());
								pie.setCantidad(listaPaqueteInsumo.get(i).getCantidad());
								pie.setFechaAdquisicion(listaPaqueteInsumo.get(i).getFechaAdquisicion());
								pie.setFechaCaducacion(listaPaqueteInsumo.get(i).getFechaCaducacion());
								session.update(pie);
							}

						}
					}

					insumo.setNombre(fieldNombre.getText());
					insumo.setCantidadDisponible(Float.parseFloat(fieldCantidadDisponible.getText()));
					insumo.setCantidadMinima(Float.parseFloat(fieldCantidadMinima.getText()));
					insumo.setCantidadMaxima(Float.parseFloat(fieldCantidadMaxima.getText()));
					insumo.actualizarStock();
					session.update(insumo);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.MODIFICAR_INSUMO) + insumo.getNombre());
					principal.getAlimentoInsumoObservables().clear();
					HibernateUtil.traerDatosBase();
					principal.setAlimentoInsumoObservables(Sistema.getSistema().pasarArrayInsumosAProperty(
							Sistema.getSistema().getInsumos()));
				}
			}

			tablaAlimentoInsumo.getItems().clear();
			tablaAlimentoInsumo.setItems(principal.getAlimentoInsumoObservables());
			salir();

		}
	}

	@FXML
	void nuevoPaquete() throws Exception {
		principal.cargarPaquete(tablaPaquete, tipoRecurso, TipoVentana.NUEVO, listaPaqueteAlimento, listaPaqueteInsumo,
				-1, Float.parseFloat(fieldCantidadMaxima.getText()), labelUnidadCantidadMaxima.getText());

		if (tipoRecurso == TipoRecurso.ALIMENTO) {
			Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
					Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_PAQUETE_ALIMENTO) + fieldNombre.getText());

		} else {
			Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
					Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_PAQUETE_INSUMO) + fieldNombre.getText());
		}
	}

	@FXML
	void borrarPaquete() throws Exception {

		int indiceSeleccionado = tablaPaquete.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			if (Integer.parseInt(tablaPaquete.getItems().get(indiceSeleccionado).getIdPaquete()) == 0) {
				if (tipoRecurso == TipoRecurso.ALIMENTO) {
					listaPaqueteAlimento.remove(indiceSeleccionado);
				} else if (tipoRecurso == TipoRecurso.INSUMO) {
					listaPaqueteInsumo.remove(indiceSeleccionado);
				}
				tablaPaquete.getItems().remove(indiceSeleccionado);
			} else {

				int idPaquete = Integer.parseInt(tablaPaquete.getItems().get(indiceSeleccionado).getIdPaquete());
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				if (tipoRecurso == TipoRecurso.ALIMENTO) {
					PaqueteAlimento PaqueteAlimento = (PaqueteAlimento) session.get(PaqueteAlimento.class, idPaquete);
					session.delete(PaqueteAlimento);
					listaPaqueteAlimento.remove(indiceSeleccionado);
				} else {
					PaqueteInsumo PaqueteInsumo = (PaqueteInsumo) session.get(PaqueteInsumo.class, idPaquete);
					session.delete(PaqueteInsumo);
					listaPaqueteInsumo.remove(indiceSeleccionado);
				}
				session.getTransaction().commit();
				tablaPaquete.getItems().remove(indiceSeleccionado);
			}

		} else if (tablaPaquete.getItems().size() < 1) {
			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();
		}

	}

	@FXML
	void modificarPaquete() throws Exception {
		int indiceSeleccionado = tablaPaquete.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			principal.cargarPaquete(tablaPaquete, tipoRecurso, TipoVentana.EDITAR, listaPaqueteAlimento,
					listaPaqueteInsumo, indiceSeleccionado, Float.parseFloat(fieldCantidadMaxima.getText()),
					labelUnidadCantidadMaxima.getText());

		} else if (tablaPaquete.getItems().size() < 1) {
			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();
		}
	}

	@FXML
	void verPaquete() throws Exception {

		int indiceSeleccionado = tablaPaquete.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {
			principal.cargarPaquete(tablaPaquete, tipoRecurso, TipoVentana.VER, listaPaqueteAlimento,
					listaPaqueteInsumo, indiceSeleccionado, Float.parseFloat(fieldCantidadMaxima.getText()),
					labelUnidadCantidadMaxima.getText());

		} else if (tablaPaquete.getItems().size() < 1) {
			mensajeTablaVacia();
		} else {
			mensajeItemNoSeleccionado();
		}

	}

	private void mensajeTablaVacia() {
		Utilidades.MensajeWarning("Tabla Vacia",
				"La tabla no contiene datos. Para poder operar debe contener algun valor en la tabla");
	}

	private void mensajeItemNoSeleccionado() {
		Utilidades
				.MensajeWarning(
						"Seleccion errónea",
						"No se detecta un item de la tabla seleccionado. Por favor seleccione un item de la tabla para poder realizar operacion sobre él");
	}

}
