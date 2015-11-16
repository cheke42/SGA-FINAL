package com.desarrollo.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import com.desarrollo.enumerados.TipoRecurso;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.PaqueteAlimento;
import com.desarrollo.modelo.PaqueteInsumo;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.PaqueteProperty;
import com.desarrollo.util.Utilidades;

public class ControladorPaquete {

	@FXML
	private DatePicker fieldFechaVencimiento;

	@FXML
	private Label labelUnidad;

	@FXML
	private Button botonSalir;

	@FXML
	private TextField fieldCantidad;

	@FXML
	private DatePicker fieldFechaAdquisicion;

	@FXML
	private TextField fieldNumeroPaquete;

	@FXML
	private Button botonGuardar;

	private Principal principal;
	private Stage escenario;

	private TipoRecurso tipoRecurso;

	private TipoVentana tipoVentana;

	private List<PaqueteAlimento> listaPaqueteAlimento = new ArrayList<PaqueteAlimento>();
	private List<PaqueteInsumo> listaPaqueteInsumo = new ArrayList<PaqueteInsumo>();

	private TableView<PaqueteProperty> tablaPaquete;

	private int indiceSeleccionado;

	private float cantidadMaxima;

	public float getCantidadMaxima() {
		return cantidadMaxima;
	}

	public void setCantidadMaxima(float cantidadMaxima) {
		this.cantidadMaxima = cantidadMaxima;
	}

	public int getIndiceSeleccionado() {
		return indiceSeleccionado;
	}

	public void setIndiceSeleccionado(int indiceSeleccionado) {
		this.indiceSeleccionado = indiceSeleccionado;
	}

	public TableView<PaqueteProperty> getTablaPaquete() {
		return tablaPaquete;
	}

	public void setTablaPaquete(TableView<PaqueteProperty> tablaPaquete) {
		this.tablaPaquete = tablaPaquete;
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

	public TipoRecurso getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(TipoRecurso tipoRecurso) {
		this.tipoRecurso = tipoRecurso;
	}

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public Label getLabelUnidad() {
		return labelUnidad;
	}

	public void setLabelUnidad(Label labelUnidad) {
		this.labelUnidad = labelUnidad;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public DatePicker getFieldFechaVencimiento() {
		return fieldFechaVencimiento;
	}

	public void setFieldFechaVencimiento(DatePicker fieldFechaVencimiento) {
		this.fieldFechaVencimiento = fieldFechaVencimiento;
	}

	public Button getBotonSalir() {
		return botonSalir;
	}

	public void setBotonSalir(Button botonSalir) {
		this.botonSalir = botonSalir;
	}

	public TextField getFieldCantidad() {
		return fieldCantidad;
	}

	public void setFieldCantidad(TextField fieldCantidad) {
		this.fieldCantidad = fieldCantidad;
	}

	public DatePicker getFieldFechaAdquisicion() {
		return fieldFechaAdquisicion;
	}

	public void setFieldFechaAdquisicion(DatePicker fieldFechaAdquisicion) {
		this.fieldFechaAdquisicion = fieldFechaAdquisicion;
	}

	public TextField getFieldNumeroPaquete() {
		return fieldNumeroPaquete;
	}

	public void setFieldNumeroPaquete(TextField fieldNumeroPaquete) {
		this.fieldNumeroPaquete = fieldNumeroPaquete;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	@SuppressWarnings("deprecation")
	private boolean validarVentana() {
		boolean validada = true;

		if (Utilidades.fechaValida(fieldFechaAdquisicion)) {
			Utilidades.MensajeError("Fecha inválida", "Indique una fecha de adquisición");
			validada = false;
			fieldFechaAdquisicion.requestFocus();
		}

		if (validada) {

			if (Utilidades.datePickerToDate(fieldFechaAdquisicion).before(Utilidades.blanquearHora(new Date()))) {
				Utilidades.MensajeError("Fecha inválida", "La fecha debe ser posterior a la fecha actual ("
						+ (new Date()).getDate() + "-" + ((new Date()).getMonth() + 1) + "-"
						+ ((new Date()).getYear() + 1900) + ")");
				validada = false;
				fieldFechaAdquisicion.requestFocus();
			}

			if (validada
					& (Utilidades.datePickerToDate(fieldFechaAdquisicion).after(Utilidades.siguienteDia(new Date())))) {
				Utilidades.MensajeError("Fecha inválida", "La fecha de adquisición no puede ser posterior a la actual");
				validada = false;
				fieldFechaAdquisicion.requestFocus();
			}

		}

		if (validada & (Utilidades.fechaValida(fieldFechaVencimiento))) {
			Utilidades.MensajeError("Fecha inválida", "Indique una fecha de vencimiento");
			validada = false;
			fieldFechaVencimiento.requestFocus();
		}

		if (validada) {
			if (Utilidades.datePickerToDate(fieldFechaVencimiento).before(
					Utilidades.datePickerToDate(fieldFechaAdquisicion))) {
				Utilidades.MensajeError("Fecha inválida",
						"La fecha de vencimiento no puede ser anterior a la fecha de adquisición");
				validada = false;
				fieldFechaVencimiento.requestFocus();
			}
		}

		if (validada & (fieldCantidad.getText().equals(""))) {
			Utilidades.MensajeError("Valor inválido", "Indique la cantidad del paquete");
			validada = false;
			fieldCantidad.requestFocus();
		}

		if (validada & (!Utilidades.validarFlotante(fieldCantidad.getText()))) {
			Utilidades.MensajeError("Valor inválido", "El valor ingresado en cantidad debe ser un número");
			validada = false;
			fieldCantidad.requestFocus();
		}

		if (validada) {
			float totalActual = 0;
			if (tipoRecurso == TipoRecurso.INSUMO) {

				if (listaPaqueteInsumo.size() > 0) {
					for (int i = 0; i < listaPaqueteInsumo.size(); i++) {
						totalActual += listaPaqueteInsumo.get(i).getCantidad();
					}

				}

				if (totalActual + (Float.parseFloat(fieldCantidad.getText())) > cantidadMaxima) {
					Utilidades.MensajeError("Cantidad máxima superada",
							"No se puede ingresar la cantidad que está indicando porque supera el limite");
					validada = false;
					fieldCantidad.requestFocus();
				}

			} else if (tipoRecurso == TipoRecurso.ALIMENTO) {
				if (listaPaqueteAlimento.size() > 0) {
					for (int i = 0; i < listaPaqueteAlimento.size(); i++) {
						totalActual += listaPaqueteAlimento.get(i).getCantidad();
					}

				}

				if (totalActual + (Float.parseFloat(fieldCantidad.getText())) > cantidadMaxima) {
					Utilidades.MensajeError("Cantidad máxima superada",
							"No se puede ingresar la cantidad que está indicando porque supera el limite");
					validada = false;
					fieldCantidad.requestFocus();
				}
			}

			//
			// if (cantidadActualTemporal +
			// (Float.parseFloat(fieldCantidad.getText())) > cantidadMaxima) {
			// Utilidades.MensajeError("Cantidad máxima superada",
			// "No se puede ingresar la cantidad que está indicando porque supera el limite");
			// validada = false;
			// fieldCantidad.requestFocus();
			// }

		}

		return validada;
	}

	@SuppressWarnings({ "deprecation" })
	@FXML
	void guardar() {

		if (validarVentana()) {

			if (tipoRecurso == TipoRecurso.ALIMENTO) {
				if (tipoVentana == TipoVentana.NUEVO) {
					Date fechaAdquisicion = new Date();
					fechaAdquisicion.setYear(fieldFechaAdquisicion.getValue().getYear() - 1900);
					fechaAdquisicion.setMonth(fieldFechaAdquisicion.getValue().getMonthValue() - 1);
					fechaAdquisicion.setDate(fieldFechaAdquisicion.getValue().getDayOfMonth());

					Date fechaVencimiento = new Date();
					fechaVencimiento.setYear(fieldFechaVencimiento.getValue().getYear() - 1900);
					fechaVencimiento.setMonth(fieldFechaVencimiento.getValue().getMonthValue() - 1);
					fechaVencimiento.setDate(fieldFechaVencimiento.getValue().getDayOfMonth());

					PaqueteAlimento pa = new PaqueteAlimento();
					pa.setIdPaqueteAlimento(Integer.parseInt(fieldNumeroPaquete.getText()));
					pa.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					pa.setFechaAdquisicion(fechaAdquisicion);
					pa.setFechaVencimiento(fechaVencimiento);
					listaPaqueteAlimento.add(pa);
					tablaPaquete.getItems().add(Sistema.getSistema().pasarPaqueteAlimentoAProperty(pa));

				} else if (tipoVentana == TipoVentana.EDITAR) {

					Date fechaAdquisicion = new Date();
					fechaAdquisicion.setYear(fieldFechaAdquisicion.getValue().getYear() - 1900);
					fechaAdquisicion.setMonth(fieldFechaAdquisicion.getValue().getMonthValue() - 1);
					fechaAdquisicion.setDate(fieldFechaAdquisicion.getValue().getDayOfMonth());

					Date fechaVencimiento = new Date();
					fechaVencimiento.setYear(fieldFechaVencimiento.getValue().getYear() - 1900);
					fechaVencimiento.setMonth(fieldFechaVencimiento.getValue().getMonthValue() - 1);
					fechaVencimiento.setDate(fieldFechaVencimiento.getValue().getDayOfMonth());

					listaPaqueteAlimento.get(indiceSeleccionado).setCantidad(Float.parseFloat(fieldCantidad.getText()));
					listaPaqueteAlimento.get(indiceSeleccionado).setFechaAdquisicion(fechaAdquisicion);
					listaPaqueteAlimento.get(indiceSeleccionado).setFechaVencimiento(fechaVencimiento);

					tablaPaquete.getItems().clear();
					principal.setPaqueteObservables(Sistema.getSistema()
							.pasarArrayPaqueteAlimento(listaPaqueteAlimento));
					tablaPaquete.setItems(principal.getPaqueteObservables());
				}
			} else if (tipoRecurso == TipoRecurso.INSUMO) {
				if (tipoVentana == TipoVentana.NUEVO) {
					Date fechaAdquisicion = new Date();
					fechaAdquisicion.setYear(fieldFechaAdquisicion.getValue().getYear() - 1900);
					fechaAdquisicion.setMonth(fieldFechaAdquisicion.getValue().getMonthValue() - 1);
					fechaAdquisicion.setDate(fieldFechaAdquisicion.getValue().getDayOfMonth());

					Date fechaVencimiento = new Date();
					fechaVencimiento.setYear(fieldFechaVencimiento.getValue().getYear() - 1900);
					fechaVencimiento.setMonth(fieldFechaVencimiento.getValue().getMonthValue() - 1);
					fechaVencimiento.setDate(fieldFechaVencimiento.getValue().getDayOfMonth());

					PaqueteInsumo paqIns = new PaqueteInsumo();
					paqIns.setIdPaqueteInsumo(Integer.parseInt(fieldNumeroPaquete.getText()));
					paqIns.setCantidad(Float.parseFloat(fieldCantidad.getText()));
					paqIns.setFechaAdquisicion(fechaAdquisicion);
					paqIns.setFechaCaducacion(fechaVencimiento);
					listaPaqueteInsumo.add(paqIns);
					tablaPaquete.getItems().add(Sistema.getSistema().pasarPaqueteInsumoAProperty(paqIns));
				} else if (tipoVentana == TipoVentana.EDITAR) {

					Date fechaAdquisicion = new Date();
					fechaAdquisicion.setYear(fieldFechaAdquisicion.getValue().getYear() - 1900);
					fechaAdquisicion.setMonth(fieldFechaAdquisicion.getValue().getMonthValue() - 1);
					fechaAdquisicion.setDate(fieldFechaAdquisicion.getValue().getDayOfMonth());

					Date fechaVencimiento = new Date();
					fechaVencimiento.setYear(fieldFechaVencimiento.getValue().getYear() - 1900);
					fechaVencimiento.setMonth(fieldFechaVencimiento.getValue().getMonthValue() - 1);
					fechaVencimiento.setDate(fieldFechaVencimiento.getValue().getDayOfMonth());
					listaPaqueteInsumo.get(indiceSeleccionado).setCantidad(Float.parseFloat(fieldCantidad.getText()));
					listaPaqueteInsumo.get(indiceSeleccionado).setFechaAdquisicion(fechaAdquisicion);
					listaPaqueteInsumo.get(indiceSeleccionado).setFechaCaducacion(fechaVencimiento);

					tablaPaquete.getItems().clear();
					principal.setPaqueteObservables(Sistema.getSistema().pasarArrayPaqueteInsumo(listaPaqueteInsumo));
					tablaPaquete.setItems(principal.getPaqueteObservables());
				}
			}
			salir();

		}

	}
}
