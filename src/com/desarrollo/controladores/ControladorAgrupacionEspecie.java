package com.desarrollo.controladores;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;

import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.AgrupacionEspecie;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.util.Utilidades;

public class ControladorAgrupacionEspecie {

	@FXML
	private Button botonCancelar;

	@FXML
	private ComboBox<String> comboEspecie;

	@FXML
	private TextField fieldNumeroAcuario;

	@FXML
	private Button botonIngresar;

	@FXML
	private TextField fieldCantidad;

	private Principal principal;

	private Stage escenario;

	private List<AgrupacionEspecie> listaEspecie;

	private int idAcuario;

	private TipoVentana tipoVentana;

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public int getIdAcuario() {
		return idAcuario;
	}

	public void setIdAcuario(int idAcuario) {
		this.idAcuario = idAcuario;
	}

	public List<AgrupacionEspecie> getListaEspecie() {
		return listaEspecie;
	}

	public void setListaEspecie(List<AgrupacionEspecie> listaEspecie) {
		this.listaEspecie = listaEspecie;
	}

	public ComboBox<String> getComboEspecie() {
		return comboEspecie;
	}

	public void setComboEspecie(ComboBox<String> comboEspecie) {
		this.comboEspecie = comboEspecie;
	}

	public TextField getFieldNumeroAcuario() {
		return fieldNumeroAcuario;
	}

	public void setFieldNumeroAcuario(TextField fieldNumeroAcuario) {
		this.fieldNumeroAcuario = fieldNumeroAcuario;
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

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public Button getBotonIngresar() {
		return botonIngresar;
	}

	public void setBotonIngresar(Button botonIngresar) {
		this.botonIngresar = botonIngresar;
	}

	public TextField getFieldCantidad() {
		return fieldCantidad;
	}

	public void setFieldCantidad(TextField fieldCantidad) {
		this.fieldCantidad = fieldCantidad;
	}

	private boolean ValidarVentana() {
		boolean validada = true;

		if (comboEspecie.getSelectionModel().getSelectedIndex() == -1) {
			validada = false;
			Utilidades.MensajeError("Especie no especificada", "Seleccione una especie del listado");
			comboEspecie.requestFocus();
		}

		if (validada & !Utilidades.validarEntero(fieldCantidad.getText())) {
			Utilidades.MensajeError("Valor inválido", "Debe ingresar un numero entero");
			validada = false;
			fieldCantidad.requestFocus();
		}

		if (validada) {
			if (Integer.parseInt(fieldCantidad.getText()) <= 0) {
				Utilidades.MensajeError("Valor inválido", "La cantidad debe ser mayor a uno");
				validada = false;
				fieldCantidad.requestFocus();
			}
		}

		return validada;
	}

	@FXML
	void guardar() {
		if (ValidarVentana()) {
			if (tipoVentana == TipoVentana.NUEVO) {
				String nombre = comboEspecie.getItems().get(comboEspecie.getSelectionModel().getSelectedIndex());

				if (Sistema.getSistema().especieCompatibleAcuario(Sistema.getSistema().buscarEspecie(nombre),
						Sistema.getSistema().buscarAcuario(idAcuario))) {

					if (especieExiste(nombre) < 0) {
						AgrupacionEspecie agrupacion = new AgrupacionEspecie();
						agrupacion.setCantidad(Integer.parseInt(fieldCantidad.getText()));
						agrupacion.setEspecie(Sistema.getSistema().buscarEspecie(nombre));
						agrupacion.setIdAgrupacion(0);
						listaEspecie.add(agrupacion);
					} else {
						int cantidad = listaEspecie.get(especieExiste(nombre)).getCantidad();
						listaEspecie.get(especieExiste(nombre)).setCantidad(
								cantidad += Integer.parseInt(fieldCantidad.getText()));
					}

					salir();

				} else {
					Dialogs.create()
							.title("Especie Incompatible")
							.style(DialogStyle.NATIVE)
							.masthead(
									"El acuario donde esta intentando ingresar la especie no cumple con los parametros de la especie")
							.message(
									"Por favor seleccione una especie que cumpla con los parametros del actual acuario")
							.showError();
				}

			} else if (tipoVentana == TipoVentana.EDITAR) {
				String nombre = comboEspecie.getPromptText();
				int posicionEspecie = especieExiste(nombre);
				listaEspecie.get(posicionEspecie).setCantidad(Integer.parseInt(fieldCantidad.getText()));
				salir();
			}

		}

	}

	private int especieExiste(String nombre) {
		int indice = -1;

		for (int i = 0; i < listaEspecie.size(); i++) {
			if (listaEspecie.get(i).getEspecie().getNombre().equals(nombre)) {
				indice = i;
			}
		}
		return indice;
	}

	@FXML
	void salir() {
		escenario.close();
	}

}
