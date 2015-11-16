package com.desarrollo.controladores;

import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class Controladorloggin {

	@FXML
	private Label labelPassword;
	@FXML
	private Label labelAlerta;
	@FXML
	private Button buttonLimpiar;

	@FXML
	private PasswordField fieldPassword;

	@FXML
	private Button buttonIngresar;

	@FXML
	private TextField fieldUsuario;

	@FXML
	private Label labelUsuario;

	@FXML
	private ImageView imagenLogo;

	private Sistema sistema;
	private Principal principal;

	public Label getLabelAlerta() {
		return labelAlerta;
	}

	public void setLabelAlerta(Label labelAlerta) {
		this.labelAlerta = labelAlerta;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}

	public Label getLabelPassword() {
		return labelPassword;
	}

	public void setLabelPassword(Label labelPassword) {
		this.labelPassword = labelPassword;
	}

	public Button getButtonLimpiar() {
		return buttonLimpiar;
	}

	public void setButtonLimpiar(Button buttonLimpiar) {
		this.buttonLimpiar = buttonLimpiar;
	}

	public PasswordField getFieldPassword() {
		return fieldPassword;
	}

	public void setFieldPassword(PasswordField fieldPassword) {
		this.fieldPassword = fieldPassword;
	}

	public Button getButtonIngresar() {
		return buttonIngresar;
	}

	public void setButtonIngresar(Button buttonIngresar) {
		this.buttonIngresar = buttonIngresar;
	}

	public TextField getFieldUsuario() {
		return fieldUsuario;
	}

	public void setFieldUsuario(TextField fieldUsuario) {
		this.fieldUsuario = fieldUsuario;
	}

	public Label getLabelUsuario() {
		return labelUsuario;
	}

	public void setLabelUsuario(Label labelUsuario) {
		this.labelUsuario = labelUsuario;
	}

	public ImageView getImagenLogo() {
		return imagenLogo;
	}

	public void setImagenLogo(ImageView imagenLogo) {
		this.imagenLogo = imagenLogo;
	}

	@FXML
	public void limpiarTextField() {
		this.getFieldUsuario().setText(null);
		this.getFieldPassword().setText(null);
		this.getLabelAlerta().setVisible(false);
	}

	@FXML
	void ingresar() throws Exception {
		labelAlerta.setVisible(false);
		Sistema.getSistema().setUsuarios((HibernateUtil.obtenerListaUsuarios()));
		if (Sistema.getSistema().logearse(fieldUsuario.getText(), fieldPassword.getText())) {
			if (Sistema.getSistema().buscarUsuario(fieldUsuario.getText()).getEstado()) {
				Utilidades.guardarEvento(fieldUsuario.getText(),
						Utilidades.eventosUsuario.get(TipoEventoUsuario.LOGEAR));
				principal.setUsuarioActivo(Sistema.getSistema().buscarUsuario(fieldUsuario.getText()));
				principal.cargarPantallaPrincial();
			} else {

				Utilidades.guardarEvento(fieldUsuario.getText(),
						Utilidades.eventosUsuario.get(TipoEventoUsuario.NO_HABLITIADO));

				fieldPassword.clear();
				fieldUsuario.clear();
				Dialogs.create().style(DialogStyle.NATIVE).title("Usuario deshabilitado")
						.masthead("El usuario se encuentra deshabilitado")
						.message("Comuniquese con un administrador para conocer el motivo").showWarning();
				fieldUsuario.requestFocus();
			}

		} else {
			labelAlerta.setVisible(true);
		}

	}

	@FXML
	public void salir() {
		HibernateUtil.getSessionFactory().close();
		principal.getPrimaryStage().close();
	}

	@FXML
	void acercaDe() {
		try {
			principal.cargarAcercaDe();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	void ayuda() {
		Utilidades.abrirLinkWeb("ayuda/Pantallaprincipal.html");
	}

}
