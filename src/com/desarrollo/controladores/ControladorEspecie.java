package com.desarrollo.controladores;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoVentana;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Parametro;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.property.EspecieProperty;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorEspecie {

	@FXML
	private ImageView imagenWarning;

	@FXML
	private Label textoWarning;

	@FXML
	private Tab tabAlimentos;

	@FXML
	private TextField fieldPhMinimo;

	@FXML
	private Button botonCancelar;

	@FXML
	private TextField fieldPhMaximo;

	@FXML
	private ListView<String> listAlimentosEspecie;

	@FXML
	private TextField fieldKhMaximo;

	@FXML
	private TextField fieldCantidad;

	@FXML
	private TextField fieldGhMaximo;

	@FXML
	private ComboBox<String> comboTipoAgua;

	@FXML
	private ImageView imagenEspecie;

	@FXML
	private ImageView imagenAgregarAlimento;

	@FXML
	private ImageView imagenQuitarAlimento;

	@FXML
	private TextField fieldKhMinimo;

	@FXML
	private Button botonAgregarAlimento;

	@FXML
	private Button botonBorrarAlimento;

	@FXML
	private TextField fieldNombre;

	@FXML
	private ListView<String> listAlimentosSistema;

	@FXML
	private TextField fieldTemperaturaMaxima;

	@FXML
	private Button botonGuardar;

	@FXML
	private TextField fieldGhMinimo;

	@FXML
	private TextArea textAreaDescripcion;

	@FXML
	private TextField fieldTemperaturaMinima;

	@FXML
	private Label labelAlimentoSistema;

	private Principal principal;

	private Stage escenario;

	private TipoVentana tipoVentana;

	private TableView<EspecieProperty> tablaEspecie;

	private int indiceSeleccionado;

	public Tab getTabAlimentos() {
		return tabAlimentos;
	}

	public void setTabAlimentos(Tab tabAlimentos) {
		this.tabAlimentos = tabAlimentos;
	}

	public int getIndiceSeleccionado() {
		return indiceSeleccionado;
	}

	public void setIndiceSeleccionado(int indiceSeleccionado) {
		this.indiceSeleccionado = indiceSeleccionado;
	}

	public Label getLabelAlimentoSistema() {
		return labelAlimentoSistema;
	}

	public void setLabelAlimentoSistema(Label labelAlimentoSistema) {
		this.labelAlimentoSistema = labelAlimentoSistema;
	}

	public TableView<EspecieProperty> getTablaEspecie() {
		return tablaEspecie;
	}

	public void setTablaEspecie(TableView<EspecieProperty> tablaEspecie) {
		this.tablaEspecie = tablaEspecie;
	}

	public ImageView getImagenAgregarAlimento() {
		return imagenAgregarAlimento;
	}

	public void setImagenAgregarAlimento(ImageView imagenAgregarAlimento) {
		this.imagenAgregarAlimento = imagenAgregarAlimento;
	}

	public ImageView getImagenQuitarAlimento() {
		return imagenQuitarAlimento;
	}

	public ImageView getImagenWarning() {
		return imagenWarning;
	}

	public void setImagenWarning(ImageView imagenWarning) {
		this.imagenWarning = imagenWarning;
	}

	public Label getTextoWarning() {
		return textoWarning;
	}

	public void setTextoWarning(Label textoWarning) {
		this.textoWarning = textoWarning;
	}

	public void setImagenQuitarAlimento(ImageView imagenQuitarAlimento) {
		this.imagenQuitarAlimento = imagenQuitarAlimento;
	}

	public TextField getFieldPhMinimo() {
		return fieldPhMinimo;
	}

	public void setFieldPhMinimo(TextField fieldPhMinimo) {
		this.fieldPhMinimo = fieldPhMinimo;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public TextField getFieldPhMaximo() {
		return fieldPhMaximo;
	}

	public void setFieldPhMaximo(TextField fieldPhMaximo) {
		this.fieldPhMaximo = fieldPhMaximo;
	}

	public ListView<String> getListAlimentosEspecie() {
		return listAlimentosEspecie;
	}

	public void setListAlimentosEspecie(ListView<String> listAlimentosEspecie) {
		this.listAlimentosEspecie = listAlimentosEspecie;
	}

	public TextField getFieldKhMaximo() {
		return fieldKhMaximo;
	}

	public void setFieldKhMaximo(TextField fieldKhMaximo) {
		this.fieldKhMaximo = fieldKhMaximo;
	}

	public TextField getFieldCantidad() {
		return fieldCantidad;
	}

	public void setFieldCantidad(TextField fieldCantidad) {
		this.fieldCantidad = fieldCantidad;
	}

	public TextField getFieldGhMaximo() {
		return fieldGhMaximo;
	}

	public void setFieldGhMaximo(TextField fieldGhMaximo) {
		this.fieldGhMaximo = fieldGhMaximo;
	}

	public ComboBox<String> getComboTipoAgua() {
		return comboTipoAgua;
	}

	public void setComboTipoAgua(ComboBox<String> comboTipoAgua) {
		this.comboTipoAgua = comboTipoAgua;
	}

	public ImageView getImagenEspecie() {
		return imagenEspecie;
	}

	public void setImagenEspecie(ImageView imagenEspecie) {
		this.imagenEspecie = imagenEspecie;
	}

	public TextField getFieldKhMinimo() {
		return fieldKhMinimo;
	}

	public void setFieldKhMinimo(TextField fieldKhMinimo) {
		this.fieldKhMinimo = fieldKhMinimo;
	}

	public Button getBotonAgregarAlimento() {
		return botonAgregarAlimento;
	}

	public void setBotonAgregarAlimento(Button botonAgregarAlimento) {
		this.botonAgregarAlimento = botonAgregarAlimento;
	}

	public Button getBotonBorrarAlimento() {
		return botonBorrarAlimento;
	}

	public void setBotonBorrarAlimento(Button botonBorrarAlimento) {
		this.botonBorrarAlimento = botonBorrarAlimento;
	}

	public TextField getFieldNombre() {
		return fieldNombre;
	}

	public void setFieldNombre(TextField fieldNombre) {
		this.fieldNombre = fieldNombre;
	}

	public ListView<String> getListAlimentosSistema() {
		return listAlimentosSistema;
	}

	public void setListAlimentosSistema(ListView<String> listAlimentosSistema) {
		this.listAlimentosSistema = listAlimentosSistema;
	}

	public TextField getFieldTemperaturaMaxima() {
		return fieldTemperaturaMaxima;
	}

	public void setFieldTemperaturaMaxima(TextField fieldTemperaturaMaxima) {
		this.fieldTemperaturaMaxima = fieldTemperaturaMaxima;
	}

	public Button getBotonGuardar() {
		return botonGuardar;
	}

	public void setBotonGuardar(Button botonGuardar) {
		this.botonGuardar = botonGuardar;
	}

	public TextField getFieldGhMinimo() {
		return fieldGhMinimo;
	}

	public void setFieldGhMinimo(TextField fieldGhMinmo) {
		this.fieldGhMinimo = fieldGhMinmo;
	}

	public TextArea getTextAreaDescripcion() {
		return textAreaDescripcion;
	}

	public void setTextAreaDescripcion(TextArea textAreaDescripcion) {
		this.textAreaDescripcion = textAreaDescripcion;
	}

	public TextField getFieldTemperaturaMinima() {
		return fieldTemperaturaMinima;
	}

	public void setFieldTemperaturaMinima(TextField fieldTemperaturaMinima) {
		this.fieldTemperaturaMinima = fieldTemperaturaMinima;
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

	public TipoVentana getTipoVentana() {
		return tipoVentana;
	}

	public void setTipoVentana(TipoVentana tipoVentana) {
		this.tipoVentana = tipoVentana;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	private void cambiarWarning(boolean esVisible) {
		textoWarning.setVisible(esVisible);
		imagenWarning.setVisible(esVisible);
	}

	private boolean validarVentana() {
		boolean validada = true;

		cambiarWarning(false);

		if (fieldNombre.getText().equals("")) {
			cambiarWarning(true);
			imagenWarning.setLayoutY(fieldNombre.getLayoutY());
			textoWarning.setLayoutY(fieldNombre.getLayoutY() + 6f);
			imagenWarning.setLayoutX(fieldNombre.getLayoutX() + fieldNombre.getPrefWidth() + 4f);
			textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
			textoWarning.setText("Campo obligatorio");
			validada = false;
			fieldNombre.requestFocus();
		}
		if ((validada == true) & (comboTipoAgua.getSelectionModel().getSelectedIndex() == -1)
				& (tipoVentana == TipoVentana.NUEVO)) {
			cambiarWarning(true);
			imagenWarning.setLayoutY(comboTipoAgua.getLayoutY());
			textoWarning.setLayoutY(comboTipoAgua.getLayoutY() + 6f);
			imagenWarning.setLayoutX(comboTipoAgua.getLayoutX() + comboTipoAgua.getPrefWidth() + 4f);
			textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
			textoWarning.setText("Seleccione un tipo de agua");
			validada = false;
			comboTipoAgua.requestFocus();
		}

		if (validada == true) {
			if (Utilidades.validarFlotante(fieldTemperaturaMinima.getText())) {
				if (Utilidades.validarFlotante(fieldTemperaturaMaxima.getText())) {
					if (Float.parseFloat(fieldTemperaturaMinima.getText()) > Float.parseFloat(fieldTemperaturaMaxima
							.getText())) {

						Utilidades.MensajeError("Error de Temperatura",
								"La temperatura máxima ingresada es menor a la mínima.");
						fieldTemperaturaMinima.requestFocus();
						validada = false;
					}
				} else {

					Utilidades.MensajeError("Valor inválido",
							"El valor ingresado en temperatura máxima debe ser un número");
					fieldTemperaturaMaxima.requestFocus();
					validada = false;

				}
			} else {
				Utilidades
						.MensajeError("Valor inválido", "El valor ingresado en temperatura mínima debe ser un número");
				fieldTemperaturaMinima.requestFocus();
				validada = false;
			}

		}

		if (validada == true) {
			if (Utilidades.validarFlotante(fieldGhMinimo.getText())) {
				if (Utilidades.validarFlotante(fieldGhMaximo.getText())) {
					if (Float.parseFloat(fieldGhMinimo.getText()) > Float.parseFloat(fieldGhMaximo.getText())) {

						Utilidades.MensajeError("Error en parametro Gh",
								"El valor de Gh máxima ingresada es menor al mínimo.");
						fieldGhMinimo.requestFocus();
						validada = false;
					}
				} else {

					Utilidades.MensajeError("Valor inválido", "El valor de Gh máximo ingresado debe ser un número");
					fieldGhMaximo.requestFocus();
					validada = false;

				}
			} else {
				Utilidades.MensajeError("Valor inválido", "El valor de Gh mínimo ingresado debe ser un número");
				fieldGhMinimo.requestFocus();
				validada = false;
			}

		}

		if (validada == true) {
			if (Utilidades.validarFlotante(fieldKhMinimo.getText())) {
				if (Utilidades.validarFlotante(fieldKhMaximo.getText())) {
					if (Float.parseFloat(fieldKhMinimo.getText()) > Float.parseFloat(fieldKhMaximo.getText())) {

						Utilidades.MensajeError("Error en parametro Kh",
								"El valor de Kh máxima ingresada es menor al mínimo.");
						fieldKhMinimo.requestFocus();
						validada = false;
					}
				} else {

					Utilidades.MensajeError("Valor inválido", "El valor de Kh máximo ingresado debe ser un número");
					fieldKhMaximo.requestFocus();
					validada = false;

				}
			} else {
				Utilidades.MensajeError("Valor inválido", "El valor de Kh mínimo ingresado debe ser un número");
				fieldKhMinimo.requestFocus();
				validada = false;
			}

		}

		if (validada == true) {
			if (Utilidades.validarFlotante(fieldPhMinimo.getText())) {
				if (Utilidades.validarFlotante(fieldPhMaximo.getText())) {
					if (Float.parseFloat(fieldPhMinimo.getText()) > Float.parseFloat(fieldPhMaximo.getText())) {

						Utilidades.MensajeError("Error en parametro Ph",
								"El valor de Ph máxima ingresada es menor al mínimo.");
						fieldPhMinimo.requestFocus();
						validada = false;
					}
				} else {

					Utilidades.MensajeError("Valor inválido", "El valor de Ph máximo ingresado debe ser un número");
					fieldPhMaximo.requestFocus();
					validada = false;

				}
			} else {
				Utilidades.MensajeError("Valor inválido", "El valor de Ph mínimo ingresado debe ser un número");
				fieldPhMinimo.requestFocus();
				validada = false;
			}

		}

		if (tipoVentana == TipoVentana.NUEVO) {
			if (Sistema.getSistema().especieExiste(fieldNombre.getText())) {
				cambiarWarning(true);
				imagenWarning.setLayoutY(fieldNombre.getLayoutY());
				textoWarning.setLayoutY(fieldNombre.getLayoutY() + 6f);
				imagenWarning.setLayoutX(fieldNombre.getLayoutX() + fieldNombre.getPrefWidth() + 4f);
				textoWarning.setLayoutX(imagenWarning.getLayoutX() + 27f);
				textoWarning.setText("Nombre ya utilizado");
				validada = false;
				Utilidades.MensajeError("Nombre inválido", "El nombre de la especie ya se encuentra utilizado");
				fieldNombre.requestFocus();

			}

		}
		return validada;
	}

	@FXML
	void guardar() {

		if (validarVentana()) {
			if (tipoVentana == TipoVentana.NUEVO) {
				Especie nuevaEspecie = new Especie();

				List<Parametro> parametrosEspecie = new ArrayList<Parametro>();

				Parametro gh = new Parametro();
				gh.setNombre("GH");
				gh.setMinimo(Float.parseFloat(fieldGhMinimo.getText()));
				gh.setMaximo(Float.parseFloat(fieldGhMaximo.getText()));

				Parametro kh = new Parametro();
				kh.setNombre("KH");
				kh.setMinimo(Float.parseFloat(fieldKhMinimo.getText()));
				kh.setMaximo(Float.parseFloat(fieldKhMaximo.getText()));

				Parametro ph = new Parametro();
				ph.setNombre("PH");
				ph.setMinimo(Float.parseFloat(fieldPhMinimo.getText()));
				ph.setMaximo(Float.parseFloat(fieldPhMaximo.getText()));

				Parametro temperatura = new Parametro();
				temperatura.setNombre("TEMPERATURA");
				temperatura.setMinimo(Float.parseFloat(fieldTemperaturaMinima.getText()));
				temperatura.setMaximo(Float.parseFloat(fieldTemperaturaMaxima.getText()));

				parametrosEspecie.add(gh);
				parametrosEspecie.add(kh);
				parametrosEspecie.add(ph);
				parametrosEspecie.add(temperatura);

				nuevaEspecie.setParametros(parametrosEspecie);

				if (listAlimentosEspecie.getItems().size() > 0) {
					for (int i = 0; i < listAlimentosEspecie.getItems().size(); i++) {
						Alimento alimento = Sistema.getSistema().buscarAlimento(listAlimentosEspecie.getItems().get(i));
						nuevaEspecie.getAlimentos().add(alimento);
					}
				}

				nuevaEspecie.setNombre(fieldNombre.getText());
				nuevaEspecie.setCantidad(Integer.parseInt(fieldCantidad.getText()));
				nuevaEspecie.setDescripcion(textAreaDescripcion.getText());
				nuevaEspecie.setTipoAgua(comboTipoAgua.getItems().get(
						comboTipoAgua.getSelectionModel().getSelectedIndex()));

				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				session.save(gh);
				session.save(kh);
				session.save(ph);
				session.save(temperatura);

				session.save(nuevaEspecie);
				session.getTransaction().commit();
				Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
						Utilidades.eventosUsuario.get(TipoEventoUsuario.CREAR_ESPECIE) + nuevaEspecie.getNombre());

			} else if (tipoVentana == TipoVentana.EDITAR) {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();

				Especie especie = (Especie) session.get(Especie.class,
						Sistema.getSistema().buscarEspecie(tablaEspecie.getItems().get(indiceSeleccionado).getNombre())
								.getIdEspecie());

				especie.setNombre(fieldNombre.getText());
				especie.setCantidad(Integer.parseInt(fieldCantidad.getText()));
				especie.setDescripcion(textAreaDescripcion.getText());
				especie.getParametros().get(0).setMaximo(Float.parseFloat(fieldGhMaximo.getText()));
				especie.getParametros().get(0).setMinimo(Float.parseFloat(fieldGhMinimo.getText()));
				especie.getParametros().get(1).setMaximo(Float.parseFloat(fieldKhMaximo.getText()));
				especie.getParametros().get(1).setMinimo(Float.parseFloat(fieldKhMinimo.getText()));
				especie.getParametros().get(2).setMaximo(Float.parseFloat(fieldPhMaximo.getText()));
				especie.getParametros().get(2).setMinimo(Float.parseFloat(fieldPhMinimo.getText()));
				especie.getParametros().get(3).setMaximo(Float.parseFloat(fieldTemperaturaMaxima.getText()));
				especie.getParametros().get(3).setMinimo(Float.parseFloat(fieldTemperaturaMinima.getText()));
				especie.getAlimentos().clear();
				for (int i = 0; i < listAlimentosEspecie.getItems().size(); i++) {
					especie.getAlimentos().add(
							Sistema.getSistema().buscarAlimento(listAlimentosEspecie.getItems().get(i)));
				}
				especie.calcularTotal();
				session.update(especie);

				session.getTransaction().commit();
				Utilidades.guardarEvento(principal.getUsuarioActivo().getNombre(),
						Utilidades.eventosUsuario.get(TipoEventoUsuario.MODIFICAR_ESPECIE) + especie.getNombre());

			}

			HibernateUtil.traerDatosBase();
			principal.getEspeciesObservables().clear();
			principal.setEspeciesObservables(Sistema.getSistema().pasarArrayEspecieAProperty(
					Sistema.getSistema().getEspecies()));
			tablaEspecie.getItems().clear();
			tablaEspecie.setItems(principal.getEspeciesObservables());
			salir();

		}

	}

	@FXML
	void agregarAlimento() {
		int indiceSeleccionado = listAlimentosSistema.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			listAlimentosEspecie.getItems().add(listAlimentosSistema.getItems().get(indiceSeleccionado));
			listAlimentosSistema.getItems().remove(indiceSeleccionado);

		} else if (listAlimentosSistema.getItems().size() < 1) {

			Utilidades.MensajeWarningLargo("Tabla vacia", "La tabla no contiene datos",
					"Para poder operar se debe contener algun valor en la tabla");
		} else {

			Utilidades.MensajeWarningLargo("Selección Erronea", "No se detecta un item de la tabla seleccionado",
					"Por favor seleccione un item de la tabla para poder realizar operacion sobre él");
		}

	}

	@FXML
	void quitarAlimento() {
		int indiceSeleccionado = listAlimentosEspecie.getSelectionModel().getSelectedIndex();
		if (indiceSeleccionado >= 0) {

			listAlimentosSistema.getItems().add(listAlimentosEspecie.getItems().get(indiceSeleccionado));
			listAlimentosEspecie.getItems().remove(indiceSeleccionado);

		} else if (listAlimentosSistema.getItems().size() < 1) {
			Utilidades.MensajeWarningLargo("Tabla vacia", "La tabla no contiene datos",
					"Para poder operar se debe contener algun valor en la tabla");
		} else {
			Utilidades.MensajeWarningLargo("Selección Erronea", "No se detecta un item de la tabla seleccionado",
					"Por favor seleccione un item de la tabla para poder realizar operacion sobre él");

		}

	}

	public void quitarAlimentosRepetidosDelSistema() {
		for (int i = 0; i < listAlimentosEspecie.getItems().size(); i++) {
			for (int j = 0; j < listAlimentosSistema.getItems().size(); j++) {
				if (listAlimentosSistema.getItems().get(j).equals(listAlimentosEspecie.getItems().get(i))) {

					listAlimentosSistema.getItems().remove(j);
				}
			}

		}
	}
}
