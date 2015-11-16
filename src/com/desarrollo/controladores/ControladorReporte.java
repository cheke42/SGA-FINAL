package com.desarrollo.controladores;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.reportes.AbstractJasperReports;
import com.desarrollo.util.Utilidades;

public class ControladorReporte {

	@FXML
	private ComboBox<String> comboTipoReporte;

	@FXML
	private Button botonVer;

	@FXML
	private ImageView imagenPdf;

	@FXML
	private Button botonGuardar;

	private Principal principal;

	private Stage stage;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public ComboBox<String> getComboTipoReporte() {
		return comboTipoReporte;
	}

	public void setComboTipoReporte(ComboBox<String> comboTipoReporte) {
		this.comboTipoReporte = comboTipoReporte;
	}

	public Button getBotonVer() {
		return botonVer;
	}

	public void setBotonVer(Button botonVer) {
		this.botonVer = botonVer;
	}

	public ImageView getImagenPdf() {
		return imagenPdf;
	}

	public void setImagenPdf(ImageView imagenPdf) {
		this.imagenPdf = imagenPdf;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	private boolean verValido() {
		boolean validada = true;

		if (comboTipoReporte.getSelectionModel().getSelectedIndex() == -1) {
			validada = false;
			Utilidades.MensajeError("Error de selección", "Por favor seleccione un tipo de reporte del desplegable");
			comboTipoReporte.requestFocus();
		}

		return validada;
	}

	@FXML
	void ver() {
		if (verValido()) {

			Sistema.getSistema().cargarReporte(
					comboTipoReporte.getItems().get(comboTipoReporte.getSelectionModel().getSelectedIndex())
							+ ".jasper");
			AbstractJasperReports.showViewer();
		}
	}

	@FXML
	void guardar() {
		if (verValido()) {

			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivo PDF", "*.pdf");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showSaveDialog(stage);
			if (file != null) {
				Sistema.getSistema().cargarReporte(
						comboTipoReporte.getItems().get(comboTipoReporte.getSelectionModel().getSelectedIndex())
								+ ".jasper");
				AbstractJasperReports.exportToPDF(file.toString().substring(0, file.toString().length() - 4), "pdf");
				stage.close();
			} else {
				System.out.println("nada");
			}

		}

	}
}
