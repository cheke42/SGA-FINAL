package com.desarrollo.controladores;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Perfil;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.modelo.Usuario;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.UsuarioProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorUsuario {

	@FXML
	private CheckBox checkboxAdministrador;

	@FXML
	private TextField fieldNombre;

	@FXML
	private CheckBox checkboxMedidor;

	@FXML
	private Button buttonCancelar;

	@FXML
	private CheckBox checkBoxEspecialista;

	@FXML
	private Button buttonGuardar;

	@FXML
	private TextField fieldpassword;

	@FXML
	private CheckBox checkboxEstadoUsuario;

	private Stage escenarioUsuario;

	private TipoVentana tipoVentana;

	private Principal principal;

	private TableView<UsuarioProperty> tablaUsuario;

	private int idUsuario;

	public TableView<UsuarioProperty> getTablaUsuario() {
		return tablaUsuario;
	}

	public void setTablaUsuario(TableView<UsuarioProperty> tablaUsuario) {
		this.tablaUsuario = tablaUsuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public CheckBox getCheckboxEstadoUsuario() {
		return checkboxEstadoUsuario;
	}

	public void setCheckboxEstadoUsuario(CheckBox checkboxEstadoUsuario) {
		this.checkboxEstadoUsuario = checkboxEstadoUsuario;
	}

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	public void setEstadoVentana(TipoVentana estadoVentana) {
		this.tipoVentana = estadoVentana;
	}

	public CheckBox getCheckboxAdministrador() {
		return checkboxAdministrador;
	}

	public void setCheckboxAdministrador(CheckBox checkboxAdministrador) {
		this.checkboxAdministrador = checkboxAdministrador;
	}

	public TextField getFieldNombre() {
		return fieldNombre;
	}

	public void setFieldNombre(TextField fieldNombre) {
		this.fieldNombre = fieldNombre;
	}

	public CheckBox getCheckboxMedidor() {
		return checkboxMedidor;
	}

	public void setCheckboxMedidor(CheckBox checkboxMedidor) {
		this.checkboxMedidor = checkboxMedidor;
	}

	public Button getButtonCancelar() {
		return buttonCancelar;
	}

	public void setButtonCancelar(Button buttonCancelar) {
		this.buttonCancelar = buttonCancelar;
	}

	public CheckBox getCheckBoxEspecialista() {
		return checkBoxEspecialista;
	}

	public void setCheckBoxEspecialista(CheckBox checkBoxEspecialista) {
		this.checkBoxEspecialista = checkBoxEspecialista;
	}

	public Button getButtonGuardar() {
		return buttonGuardar;
	}

	public void setButtonGuardar(Button buttonGuardar) {
		this.buttonGuardar = buttonGuardar;
	}

	public TextField getFieldpassword() {
		return fieldpassword;
	}

	public void setFieldpassword(TextField fieldpassword) {
		this.fieldpassword = fieldpassword;
	}

	public Stage getEscenarioUsuario() {
		return escenarioUsuario;
	}

	public void setEscenarioUsuario(Stage escenarioUsuario) {
		this.escenarioUsuario = escenarioUsuario;
	}

	@FXML
	void salir() {
		escenarioUsuario.close();
	}

	private boolean validarVentana() {
		boolean validada = true;

		if (fieldNombre.getText().equals("")) {
			Utilidades.MensajeError("Nombre obligatorio", "Por favor ingrese un nombre de usuario");
			validada = false;
			fieldNombre.requestFocus();
		}

		if (validada & tipoVentana == TipoVentana.NUEVO & usuarioExiste(fieldNombre.getText())) {
			Utilidades.MensajeError("Nombre de usuario no disponible",
					"El nombre de usuario que especificó ya esta siendo utilizado por otro usuario");
			validada = false;
			fieldNombre.requestFocus();

		}
		if (validada & fieldpassword.getText().equals("")) {
			Utilidades.MensajeError("Contraseña obligatoria", "Por favor ingrese una contraseña para el usuario");
			validada = false;
			fieldpassword.requestFocus();
		}

		return validada;

	}

	@FXML
	void guardarUsuario() {

		if (validarVentana()) {

			List<Perfil> tperfiles = new ArrayList<Perfil>();
			if (tipoVentana == TipoVentana.NUEVO) {

				if (!usuarioExiste(fieldNombre.getText())) {
					Usuario usuario = new Usuario();
					usuario.setNombre(fieldNombre.getText());
					usuario.setPassword(fieldpassword.getText());
					usuario.setEstado(checkboxEstadoUsuario.isSelected());

					if (checkboxAdministrador.isSelected()) {
						tperfiles.add(Sistema.getSistema().getPerfiles().get(0));
					}
					if (checkBoxEspecialista.isSelected()) {
						tperfiles.add(Sistema.getSistema().getPerfiles().get(1));
					}
					if (checkboxMedidor.isSelected()) {
						tperfiles.add(Sistema.getSistema().getPerfiles().get(2));
					}
					usuario.setPerfiles(tperfiles);
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();
					session.save(usuario);
					session.getTransaction().commit();
					Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
							Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_USUARIO) + fieldNombre.getText());
					
				}
			} else if (tipoVentana == TipoVentana.EDITAR) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Usuario us = (Usuario) session.get(Usuario.class, idUsuario);
				us.setNombre(fieldNombre.getText());
				us.setPassword(fieldNombre.getText());
				us.setEstado(checkboxEstadoUsuario.isSelected());
				us.getPerfiles().clear();
				if (checkboxAdministrador.isSelected()) {
					us.getPerfiles().add(Sistema.getSistema().getPerfiles().get(0));
				}
				if (checkBoxEspecialista.isSelected()) {
					us.getPerfiles().add(Sistema.getSistema().getPerfiles().get(1));
				}
				if (checkboxMedidor.isSelected()) {
					us.getPerfiles().add(Sistema.getSistema().getPerfiles().get(2));
				}
				session.update(us);
				session.getTransaction().commit();
				Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
						Utilidades.eventosUsuario.get(TipoEventoUsuario.MODIFICAR_USUARIO) + fieldNombre.getText());

			}
			HibernateUtil.traerDatosBase();
			principal.getUsuariosObservables().clear();
			principal.setUsuariosObservables(Sistema.getSistema().pasarArrayUsuarioAProperty(
					Sistema.getSistema().getUsuarios()));
			tablaUsuario.getItems().clear();
			tablaUsuario.getItems().setAll(principal.getUsuariosObservables());
			salir();

		}

	}

	public boolean usuarioExiste(String nombre) {
		return Sistema.getSistema().usuarioExiste(nombre);

	}

}