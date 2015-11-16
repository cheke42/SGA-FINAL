package com.desarrollo.controladores;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.modelo.Acuario;
import com.desarrollo.modelo.AgrupacionEspecie;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.principal.Principal;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class ControladorTransferirEspecie {

	@FXML
	private ComboBox<String> comboBoxAcuarioDestino;
	@FXML
	private ImageView imagenTrasnferir;

	@FXML
	private Button botonCancelar;

	@FXML
	private Button botonTransferir;

	@FXML
	private TextField fieldAcuarioOrigen;

	@FXML
	private TextField fieldEspecie;

	@FXML
	private TextField fieldCantidad;

	private int nroAcuarioorigen;

	private int indiceSeleccionado;

	private List<AgrupacionEspecie> listaAgrupacionEspecie;

	private Stage escenario;

	private String nombreEspecie;

	private Principal principal;

	public Principal getPrincipal() {
		return principal;
	}

	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}

	public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void setEscenario(Stage escenario) {
		this.escenario = escenario;
	}

	public ComboBox<String> getComboBoxAcuarioDestino() {
		return comboBoxAcuarioDestino;
	}

	public void setComboBoxAcuarioDestino(ComboBox<String> comboBoxAcuarioDestino) {
		this.comboBoxAcuarioDestino = comboBoxAcuarioDestino;
	}

	public ImageView getImagenTrasnferir() {
		return imagenTrasnferir;
	}

	public void setImagenTrasnferir(ImageView imagenTrasnferir) {
		this.imagenTrasnferir = imagenTrasnferir;
	}

	public Button getBotonCancelar() {
		return botonCancelar;
	}

	public void setBotonCancelar(Button botonCancelar) {
		this.botonCancelar = botonCancelar;
	}

	public Button getBotonTransferir() {
		return botonTransferir;
	}

	public void setBotonTransferir(Button botonTransferir) {
		this.botonTransferir = botonTransferir;
	}

	public TextField getFieldAcuarioOrigen() {
		return fieldAcuarioOrigen;
	}

	public void setFieldAcuarioOrigen(TextField fieldAcuarioOrigen) {
		this.fieldAcuarioOrigen = fieldAcuarioOrigen;
	}

	public TextField getFieldEspecie() {
		return fieldEspecie;
	}

	public void setFieldEspecie(TextField fieldEspecie) {
		this.fieldEspecie = fieldEspecie;
	}

	public TextField getFieldCantidad() {
		return fieldCantidad;
	}

	public void setFieldCantidad(TextField fieldCantidad) {
		this.fieldCantidad = fieldCantidad;
	}

	public int getNroAcuarioorigen() {
		return nroAcuarioorigen;
	}

	public void setNroAcuarioorigen(int nroAcuarioorigen) {
		this.nroAcuarioorigen = nroAcuarioorigen;
	}

	public int getIndiceSeleccionado() {
		return indiceSeleccionado;
	}

	public void setIndiceSeleccionado(int indiceSeleccionado) {
		this.indiceSeleccionado = indiceSeleccionado;
	}

	public List<AgrupacionEspecie> getListaAgrupacionEspecie() {
		return listaAgrupacionEspecie;
	}

	public void setListaAgrupacionEspecie(List<AgrupacionEspecie> listaAgrupacionEspecie) {
		this.listaAgrupacionEspecie = listaAgrupacionEspecie;
	}

	@FXML
	void salir() {
		escenario.close();
	}

	private boolean validarVentana() {
		boolean validada = true;

		if (comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex() == -1) {
			validada = false;
			Utilidades.MensajeError("Acuario no seleccionado", "Seleccione el número de acuario destino");
			comboBoxAcuarioDestino.requestFocus();
		}

		if (validada & !Utilidades.validarEntero(fieldCantidad.getText())) {
			validada = false;
			Utilidades.MensajeError("Cantidad inválido", "El valor ingresado debe ser un número entero");
			fieldCantidad.requestFocus();
		}

		if (validada) {
			if (Integer.parseInt(fieldCantidad.getText()) < 1) {
				validada = false;
				Utilidades.MensajeError("Cantidad inválido", "La cantidad a transferir debe ser mayor o igual a uno");
				fieldCantidad.requestFocus();
			}
		}

		return validada;
	}

	@FXML
	void guardar() {

		if (validarVentana()) {

			if (Sistema.getSistema().especieCompatibleAcuario(
					Sistema.getSistema().buscarEspecie(nombreEspecie),
					Sistema.getSistema().buscarAcuario(
							Integer.parseInt(comboBoxAcuarioDestino.getItems().get(
									comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex()))))
					& Sistema
							.getSistema()
							.buscarAcuario(
									Integer.parseInt(comboBoxAcuarioDestino.getItems().get(
											comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex())))
							.isDisponible()) {

				if (listaAgrupacionEspecie.get(indiceSeleccionado).getCantidad() > (Integer.parseInt(fieldCantidad
						.getText()))) {
					// si esta dentro del rango

					int cantidad = listaAgrupacionEspecie.get(indiceSeleccionado).getCantidad();
					listaAgrupacionEspecie.get(indiceSeleccionado).setCantidad(
							cantidad - Integer.parseInt(fieldCantidad.getText()));

					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();

					AgrupacionEspecie agrupacionExistente = (AgrupacionEspecie) session.get(AgrupacionEspecie.class,
							listaAgrupacionEspecie.get(indiceSeleccionado).getIdAgrupacion());

					agrupacionExistente.setCantidad(cantidad - Integer.parseInt(fieldCantidad.getText()));

					session.update(agrupacionExistente);

					Acuario acuario = (Acuario) session.get(
							Acuario.class,
							Integer.parseInt(comboBoxAcuarioDestino.getItems().get(
									comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex())));

					boolean marca = false;
					int indice = -1;
					for (int i = 0; i < acuario.getAgrupacionEspecie().size(); i++) {
						if (acuario.getAgrupacionEspecie().get(i).getEspecie().getNombre()
								.equals(fieldEspecie.getText())) {
							marca = true;
							indice = i;
						}
					}

					if (marca) {
						int cantidadAnterior = acuario.getAgrupacionEspecie().get(indice).getCantidad();
						acuario.getAgrupacionEspecie().get(indice)
								.setCantidad(cantidadAnterior + Integer.parseInt(fieldCantidad.getText()));

					} else {
						AgrupacionEspecie nuevaAgrupacion = new AgrupacionEspecie();
						nuevaAgrupacion.setCantidad(Integer.parseInt(fieldCantidad.getText()));
						nuevaAgrupacion.setEspecie(Sistema.getSistema().buscarEspecie(nombreEspecie));
						acuario.getAgrupacionEspecie().add(nuevaAgrupacion);
						session.save(nuevaAgrupacion);
					}

					Especie especie = (Especie) session.get(Especie.class,
							Sistema.getSistema().buscarEspecie(nombreEspecie).getIdEspecie());
					especie.calcularTotal();

					session.update(especie);
					session.update(acuario);
					session.getTransaction().commit();

				} else if (listaAgrupacionEspecie.get(indiceSeleccionado).getCantidad() == (Integer
						.parseInt(fieldCantidad.getText()))) {
					// si es igual

					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					session.beginTransaction();

					AgrupacionEspecie agrEsp = (AgrupacionEspecie) session.get(AgrupacionEspecie.class,
							listaAgrupacionEspecie.get(indiceSeleccionado).getIdAgrupacion());

					session.delete(agrEsp);
					session.getTransaction().commit();

					listaAgrupacionEspecie.remove(indiceSeleccionado);

					Session session2 = HibernateUtil.getSessionFactory().getCurrentSession();
					session2.beginTransaction();
					Acuario acuario = (Acuario) session2.get(
							Acuario.class,
							Integer.parseInt(comboBoxAcuarioDestino.getItems().get(
									comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex())));

					boolean marca = false;
					int indice = -1;
					for (int i = 0; i < acuario.getAgrupacionEspecie().size(); i++) {
						if (acuario.getAgrupacionEspecie().get(i).getEspecie().getNombre()
								.equals(fieldEspecie.getText())) {
							marca = true;
							indice = i;
						}
					}

					if (marca) {
						int cantidadAnterior = acuario.getAgrupacionEspecie().get(indice).getCantidad();
						acuario.getAgrupacionEspecie().get(indice)
								.setCantidad(cantidadAnterior + Integer.parseInt(fieldCantidad.getText()));
					} else {
						AgrupacionEspecie agruEsp = new AgrupacionEspecie();
						agruEsp.setCantidad(Integer.parseInt(fieldCantidad.getText()));
						agruEsp.setEspecie(Sistema.getSistema().buscarEspecie(nombreEspecie));
						acuario.getAgrupacionEspecie().add(agruEsp);
						session2.save(agruEsp);
					}

					Especie especie = (Especie) session2.get(Especie.class,
							Sistema.getSistema().buscarEspecie(nombreEspecie).getIdEspecie());
					especie.calcularTotal();

					session2.update(especie);
					session2.update(acuario);
					session2.getTransaction().commit();
					Utilidades.guardarEvento(
							principal.getUsuarioActivo().getNombre(),
							"Transferió la especie "
									+ nombreEspecie
									+ " del acuario "
									+ nroAcuarioorigen
									+ " al acuario "
									+ comboBoxAcuarioDestino.getItems().get(
											comboBoxAcuarioDestino.getSelectionModel().getSelectedIndex()));

				} else {
					Dialogs.create()
							.style(DialogStyle.NATIVE)
							.title("Cantidad Erronea")
							.masthead(
									"La cantidad de especie indicada es mayor que la que posee el acuario ("
											+ listaAgrupacionEspecie.get(indiceSeleccionado).getCantidad() + " "
											+ listaAgrupacionEspecie.get(indiceSeleccionado).getEspecie().getNombre()
											+ ")").message("Por favor ingrese de nuevo").showError();
				}
				salir();
			} else {
				Utilidades
						.MensajeError("No es posible transferir al acuario seleccionado",
								"El acuario que seleccionó no es compatible con la especie o no se encuentra actualmente disponible");

			}

		}

	}
}
