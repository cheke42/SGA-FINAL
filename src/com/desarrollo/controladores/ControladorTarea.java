package com.desarrollo.controladores;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.hibernate.Session;

import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Alimentacion;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Insumo;
import com.desarrollo.modelo.Limpieza;
import com.desarrollo.modelo.Medicion;
import com.desarrollo.modelo.Parametro;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorTarea {

	@FXML
	private ComboBox<String> comboRecurso;

	@FXML
	private ComboBox<String> comboNumeroAcuario;

	@FXML
	private TextArea textAreaIndicaciones;

	@FXML
	private TextArea textAreaObservaciones;

	@FXML
	private Button botonCancelar;

	@FXML
	private Label labelUnidad;

	@FXML
	private TextField fieldUsuario;

	@FXML
	private DatePicker labelFechaFinalizacion;

	@FXML
	private TextField fieldCantidad;

	@FXML
	private Button botonGuardar;

	@FXML
	private ImageView imagenRecurso;

	private Stage escenario;

	private Principal principal;

	private TipoRecurso tipoRecurso;

	private TipoVentana tipoVentana;

	private int idTarea;

	public int getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public Label getLabelUnidad() {
		return labelUnidad;
	}

	public void setLabelUnidad(Label labelUnidad) {
		this.labelUnidad = labelUnidad;
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
	}

	public ComboBox<String> getComboRecurso() {
		return comboRecurso;
	}

	public void setComboRecurso(ComboBox<String> comboRecurso) {
		this.comboRecurso = comboRecurso;
	}

	public TextArea getTextAreaIndicaciones() {
		return textAreaIndicaciones;
	}

	public void setTextAreaIndicaciones(TextArea textAreaIndicaciones) {
		this.textAreaIndicaciones = textAreaIndicaciones;
	}

	public TextArea getTextAreaObservaciones() {
		return textAreaObservaciones;
	}

	public void setTextAreaObservaciones(TextArea textAreaObservaciones) {
		this.textAreaObservaciones = textAreaObservaciones;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public TextField getFieldUsuario() {
		return fieldUsuario;
	}

	public void setFieldUsuario(TextField fieldUsuario) {
		this.fieldUsuario = fieldUsuario;
	}

	public DatePicker getLabelFechaFinalizacion() {
		return labelFechaFinalizacion;
	}

	public void setLabelFechaFinalizacion(DatePicker labelFechaFinalizacion) {
		this.labelFechaFinalizacion = labelFechaFinalizacion;
	}

	public TextField getFieldCantidad() {
		return fieldCantidad;
	}

	public void setFieldCantidad(TextField fieldCantidad) {
		this.fieldCantidad = fieldCantidad;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public ImageView getImagenRecurso() {
		return imagenRecurso;
	}

	public void setImagenRecurso(ImageView imagenRecurso) {
		this.imagenRecurso = imagenRecurso;
	}

	public ComboBox<String> getComboNumeroAcuario() {
		return comboNumeroAcuario;
	}

	public void setComboNumeroAcuario(ComboBox<String> comboNumeroAcuario) {
		this.comboNumeroAcuario = comboNumeroAcuario;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	@SuppressWarnings("rawtypes")
	@FXML
	void cambiarUnidad(ActionEvent event) {
		ComboBox combo = (ComboBox) event.getSource();

		if (tipoRecurso == TipoRecurso.ALIMENTO) {
			Alimento alim = Sistema.getSistema().buscarAlimento(
					(String) combo.getItems().get(combo.getSelectionModel().getSelectedIndex()));
			labelUnidad.setVisible(true);
			labelUnidad.setText(Utilidades.miniUnidad.get(alim.getUnidadMedida()));
		} else if (tipoRecurso == TipoRecurso.INSUMO) {
			Insumo ins = Sistema.getSistema().buscarInsumo(
					(String) combo.getItems().get(combo.getSelectionModel().getSelectedIndex()));
			labelUnidad.setVisible(true);
			labelUnidad.setText(Utilidades.miniUnidad.get(ins.getUnidadMedida()));
		}

	}

	private boolean ValidarVentana() {
		boolean validada = true;

		if (tipoVentana == TipoVentana.NUEVO
				& (Utilidades.datePickerToDate(labelFechaFinalizacion)).before(Utilidades.blanquearHora(new Date()))) {
			Utilidades.MensajeError("Fecha inválida", "La fecha debe ser posterior a la fecha actual");
			labelFechaFinalizacion.requestFocus();
			validada = false;
		}

		if (validada & !Utilidades.validarFlotante(fieldCantidad.getText()) & (tipoRecurso != TipoRecurso.MEDICION)) {
			Utilidades.MensajeError("Valor inválido", "La cantidad especificada debe ser un número");
			validada = false;
			fieldCantidad.requestFocus();
		}

		if (validada) {
			if (tipoRecurso == TipoRecurso.ALIMENTO & tipoVentana == TipoVentana.NUEVO) {
				if ((Float.parseFloat(fieldCantidad.getText())) > cantidadAlimentoDisponible(comboRecurso.getItems()
						.get(comboRecurso.getSelectionModel().getSelectedIndex()))) {
					Utilidades.MensajeError(
							"Valor inválido",
							"La cantidad especificada supera la cantidad actual disponible en el stock. ("
									+ Sistema
											.getSistema()
											.buscarAlimento(
													comboRecurso.getItems().get(
															comboRecurso.getSelectionModel().getSelectedIndex()))
											.getCantidadDisponible()
									+ " "
									+ Utilidades.miniUnidad.get(Sistema
											.getSistema()
											.buscarAlimento(
													comboRecurso.getItems().get(
															comboRecurso.getSelectionModel().getSelectedIndex()))
											.getUnidadMedida()) + " de "
									+ comboRecurso.getItems().get(comboRecurso.getSelectionModel().getSelectedIndex())
									+ ").");
					validada = false;
					fieldCantidad.requestFocus();
				}
			} else if (tipoRecurso == TipoRecurso.INSUMO & tipoVentana == TipoVentana.NUEVO) {
				if ((Float.parseFloat(fieldCantidad.getText())) > cantidadInsumoDisponible(comboRecurso.getItems().get(
						comboRecurso.getSelectionModel().getSelectedIndex()))) {

					Utilidades.MensajeError(
							"Valor inválido",
							"La cantidad especificada para la actividad supera la cantidad disponible en stock ("
									+ Sistema
											.getSistema()
											.buscarInsumo(
													comboRecurso.getItems().get(
															comboRecurso.getSelectionModel().getSelectedIndex()))
											.getCantidadDisponible()
									+ " "
									+ Utilidades.miniUnidad.get(Sistema
											.getSistema()
											.buscarInsumo(
													comboRecurso.getItems().get(
															comboRecurso.getSelectionModel().getSelectedIndex()))
											.getUnidadMedida()) + " de "
									+ comboRecurso.getItems().get(comboRecurso.getSelectionModel().getSelectedIndex())
									+ ").");
					validada = false;
					fieldCantidad.requestFocus();

				}

			}
		}

		if (validada & tipoVentana == TipoVentana.ASENTAR) {
			if (!Utilidades.validarFlotante(fieldCantidad.getText())) {
				Utilidades.MensajeError("Valor inválido", "El valor debe ser un número");
				fieldCantidad.requestFocus();
				validada = false;
			}
		}

		return validada;
	}

	private float cantidadInsumoDisponible(String nombreInsumo) {
		return Sistema.getSistema().buscarInsumo(nombreInsumo).getCantidadDisponible();
	}

	private float cantidadAlimentoDisponible(String nombreAlimento) {
		return Sistema.getSistema().buscarAlimento(nombreAlimento).getCantidadDisponible();
	}

	@SuppressWarnings("deprecation")
	@FXML
	void guardar() {

		if (ValidarVentana()) {
			if (tipoVentana == TipoVentana.NUEVO) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Date fechaCreacion = new Date();
				fechaCreacion.setYear(labelFechaFinalizacion.getValue().getYear() - 1900);
				fechaCreacion.setMonth(labelFechaFinalizacion.getValue().getMonthValue() - 1);
				fechaCreacion.setDate(labelFechaFinalizacion.getValue().getDayOfMonth());

				switch (tipoRecurso.toString()) {
				case "ALIMENTO":

					Alimentacion alimentacion = new Alimentacion();
					alimentacion.setAlimento(Sistema.getSistema().buscarAlimento(
							comboRecurso.getItems().get(comboRecurso.getSelectionModel().getSelectedIndex())));

					alimentacion.setFechaCrecion(fechaCreacion);
					alimentacion.setIndicaciones(textAreaIndicaciones.getText());
					alimentacion.setRealizada(false);
					alimentacion.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					alimentacion.setUsuario(Sistema.getSistema().buscarUsuario(fieldUsuario.getText()));
					alimentacion.setTipo("ALIMENTACION");
					if (comboNumeroAcuario.getSelectionModel().getSelectedIndex() == -1) {
						alimentacion.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getPromptText()));

					} else {
						alimentacion.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getItems().get(
								comboNumeroAcuario.getSelectionModel().getSelectedIndex())));

					}
					session.save(alimentacion);

					break;

				case "INSUMO":
					Limpieza limpieza = new Limpieza();
					limpieza.setInsumo(Sistema.getSistema().buscarInsumo(
							comboRecurso.getItems().get(comboRecurso.getSelectionModel().getSelectedIndex())));
					limpieza.setFechaCrecion(fechaCreacion);
					limpieza.setRealizada(false);
					limpieza.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					limpieza.setIndicaciones(textAreaIndicaciones.getText());
					limpieza.setUsuario(Sistema.getSistema().buscarUsuario(fieldUsuario.getText()));
					limpieza.setTipo("LIMPIEZA");

					if (comboNumeroAcuario.getSelectionModel().getSelectedIndex() == -1) {
						limpieza.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getPromptText()));

					} else {
						limpieza.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getItems().get(
								comboNumeroAcuario.getSelectionModel().getSelectedIndex())));

					}

					session.save(limpieza);
					break;
				case "MEDICION":
					Medicion medicion = new Medicion();
					Parametro parametro = new Parametro();
					parametro.setNombre(comboRecurso.getPromptText());
					session.save(parametro);
					medicion.setParametro(parametro);
					if (comboNumeroAcuario.getSelectionModel().getSelectedIndex() == -1) {
						medicion.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getPromptText()));
					} else {
						medicion.setNumeroAcuario(Integer.parseInt(comboNumeroAcuario.getItems().get(
								comboNumeroAcuario.getSelectionModel().getSelectedIndex())));
					}
					medicion.setRealizada(false);
					medicion.setFechaCrecion(fechaCreacion);
					medicion.setTipo("MEDICION");
					medicion.setIndicaciones(textAreaIndicaciones.getText());
					medicion.setUsuario(Sistema.getSistema().buscarUsuario(fieldUsuario.getText()));

					session.save(medicion);
					break;

				}
				session.getTransaction().commit();
				salir();
			} else if (tipoVentana == TipoVentana.ASENTAR) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				switch (tipoRecurso.toString()) {
				case "ALIMENTO":
					Alimentacion alimentacion = (Alimentacion) session.get(Alimentacion.class, idTarea);
					alimentacion.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					alimentacion.setObservaciones(textAreaObservaciones.getText());
					alimentacion.setRealizada(true);

					Alimento alimento = (Alimento) session.get(Alimento.class,
							Sistema.getSistema().buscarAlimento(comboRecurso.getPromptText()).getIdAlimento());

					if (!alimento.decrementarAlimento(Float.parseFloat(fieldCantidad.getText()))) {
						Utilidades
								.MensajeError("Error en Stock",
										"No se puede asentar la tarea porque no se cuenta con la cantidad disponible del recurso en stock");
						alimentacion.setRealizada(false);
					}
					;
					alimento.actualizarStock();
					session.update(alimento);

					session.update(alimentacion);
					break;

				case "INSUMO":
					System.out.println("Entro a insumo");
					Limpieza limpieza = (Limpieza) session.get(Limpieza.class, idTarea);
					limpieza.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					limpieza.setObservaciones(textAreaObservaciones.getText());
					limpieza.setRealizada(true);
					Insumo insumo = (Insumo) session.get(Insumo.class,
							Sistema.getSistema().buscarInsumo(comboRecurso.getPromptText()).getIdInsumo());
					System.out.println("Llego al if");
					if (!insumo.decrementarInsumo(Float.parseFloat(fieldCantidad.getText()))) {
						Utilidades
								.MensajeError("Error en Stock",
										"No se puede asentar la tarea porque no se cuenta con la cantidad disponible del recurso en stock");
						limpieza.setRealizada(false);
						System.out.println("Entró a insumo no disponible");
					}

					insumo.actualizarStock();
					session.update(insumo);
					session.update(limpieza);
					break;
				case "MEDICION":
					Medicion medicion = (Medicion) session.get(Medicion.class, idTarea);
					medicion.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					medicion.setObservaciones(textAreaObservaciones.getText());
					medicion.setRealizada(true);
					session.update(medicion);

					break;

				}
				session.getTransaction().commit();
				salir();
			}

		}

	}
}