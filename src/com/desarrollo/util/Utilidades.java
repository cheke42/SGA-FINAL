package com.desarrollo.util;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javafx.scene.control.DatePicker;

import org.controlsfx.dialog.DialogStyle;
import org.controlsfx.dialog.Dialogs;
import org.hibernate.Session;

import com.desarrollo.enumerados.TipoEventoUsuario;
import com.desarrollo.enumerados.TipoMedidaUnidad;
import com.desarrollo.modelo.Evento;

public class Utilidades {

	@SuppressWarnings("serial")
	public static Hashtable<String, String> miniUnidad = new Hashtable<String, String>() {
		{
			put(TipoMedidaUnidad.MILIGRAMOS.toString(), "mg");
			put(TipoMedidaUnidad.GRRAMOS.toString(), "gr");
			put(TipoMedidaUnidad.KILOGRAMOS.toString(), "kg");
			put(TipoMedidaUnidad.MILILITROS.toString(), "ml");
			put(TipoMedidaUnidad.CENTILITROS.toString(), "cl");
			put(TipoMedidaUnidad.LITROS.toString(), "litros");
			put(TipoMedidaUnidad.DECALITROS.toString(), "dal");
			put(TipoMedidaUnidad.HECTOLITROS.toString(), "hl");
			put(TipoMedidaUnidad.KILOLITROS.toString(), "kl");
			put(TipoMedidaUnidad.MILIMETROSCUBICOS.toString(), "mm3");
			put(TipoMedidaUnidad.CENTIMETROSCUBICOS.toString(), "cm3");
			put(TipoMedidaUnidad.DECIMETROSCUBICOS.toString(), "dm3");
			put(TipoMedidaUnidad.METROSCUBICOS.toString(), "m3");
			put(TipoMedidaUnidad.DECAMETROSCUBICOS.toString(), "dam3");
			put(TipoMedidaUnidad.HECTOMETROSCUBICOS.toString(), "hm3");
			put(TipoMedidaUnidad.KILOMETROSCUBICOS.toString(), "km3");

		}
	};

	@SuppressWarnings("serial")
	public static Hashtable<TipoEventoUsuario, String> eventosUsuario = new Hashtable<TipoEventoUsuario, String>() {

		{
			put(TipoEventoUsuario.NO_HABLITIADO, "Intent� logearse pero est� deshabilitado");
			put(TipoEventoUsuario.LOGEAR, "Se loge� en el sistema");
			put(TipoEventoUsuario.DESLOGEAR, "Se desloge� del sistema");
			put(TipoEventoUsuario.CREAR_USUARIO, "Cre� al usuario ");
			put(TipoEventoUsuario.BORRAR_USUARIO, "Borr� al usuario ");
			put(TipoEventoUsuario.MODIFICAR_USUARIO, "Modific� al usuario ");
			put(TipoEventoUsuario.CREAR_ESPECIE, "Cre� la especie ");
			put(TipoEventoUsuario.BORRAR_ESPECIE, "Borr� la especie ");
			put(TipoEventoUsuario.MODIFICAR_ESPECIE, "Modific� la especie ");
			put(TipoEventoUsuario.CREAR_ALIMENTO, "Cre� el alimento ");
			put(TipoEventoUsuario.BORRAR_ALIMENTO, "Borr� el alimento ");
			put(TipoEventoUsuario.MODIFICAR_ALIMENTO, "Modif� el alimento ");
			put(TipoEventoUsuario.CREAR_PAQUETE_ALIMENTO, "Cre� un paquete de alimento a ");
			put(TipoEventoUsuario.BORRAR_PAQUETE_ALIMENTO, "Borr� un paquete del alimento ");
			put(TipoEventoUsuario.MODIFICAR_PAQUETE_ALIMENTO, "Se modific� el paquete de ");
			put(TipoEventoUsuario.CREAR_INSUMO, "Se cre� el insumo ");
			put(TipoEventoUsuario.BORRAR_INSUMO, "Se borr� el insumo ");
			put(TipoEventoUsuario.MODIFICAR_INSUMO, "Se modific� el insumo ");
			put(TipoEventoUsuario.CREAR_PAQUETE_INSUMO, "Cre� un paquete de insumo a ");
			put(TipoEventoUsuario.MODIFICAR_PAQUETE_INSUMO, "Se modific� el paquete de ");
			put(TipoEventoUsuario.BORRAR_PAQUETE_INSUMO, "Se borr� un paquete del insumo ");
			put(TipoEventoUsuario.CREAR_ACUARIO, "Se cre� el acuario ");
			put(TipoEventoUsuario.MODIFICAR_ACUARIO, "Se modific� el acuario ");
			put(TipoEventoUsuario.BORRAR_ACUARIO, "Se borr� el acuario ");
			put(TipoEventoUsuario.INGRESAR_ESPECIE_ACUARIO, "Ingreso en el acuario");
			put(TipoEventoUsuario.TRASLADAR_ESPECIE_ACUARIO, "Traslad� del acuario");
			put(TipoEventoUsuario.BORRAR_ESPECIE_ACARUIO, "Borr� la especie");
		}
	};

	public static final URL restToURL(String res) {
		return Utilidades.class.getResource(res);
	}

	@SuppressWarnings("deprecation")
	public static Date datePickerToDate(DatePicker datePicker) {
		Date fechaDate = new Date();
		fechaDate.setYear(datePicker.getValue().getYear() - 1900);
		fechaDate.setMonth(datePicker.getValue().getMonthValue() - 1);
		fechaDate.setDate(datePicker.getValue().getDayOfMonth());
		fechaDate.setHours(23);
		fechaDate.setMinutes(59);
		fechaDate.setSeconds(59);
		return fechaDate;
	}

	@SuppressWarnings("deprecation")
	public static DatePicker dateToDatePicker(Date date) {
		DatePicker datePicker = new DatePicker();

		datePicker.setValue(LocalDateTime.ofInstant(
				Instant.ofEpochMilli((new Date(date.getYear(), date.getMonth(), date.getDate())).getTime()),
				ZoneId.systemDefault()).toLocalDate());

		return datePicker;
	}

	@SuppressWarnings("deprecation")
	public static Date sumarRestarDiasFecha(Date fecha, int dias) {

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(fecha); // Configuramos la fecha que se recibe

		calendar.add(Calendar.DAY_OF_YEAR, dias); // numero de d�as a a�adir, o
													// restar en caso de d�as<0
		calendar.getTime().setHours(23);
		calendar.getTime().setMinutes(59);

		return calendar.getTime(); // Devuelve el objeto Date con los nuevos
									// d�as a�adidos

	}

	@SuppressWarnings("deprecation")
	public static Date blanquearHora(Date date) {
		date.setHours(00);
		date.setMinutes(00);
		date.setSeconds(00);
		return date;
	}

	@SuppressWarnings("deprecation")
	public static Date siguienteDia(Date date) {

		date.setDate(date.getDate() + 1);
		return date;
	}

	public static boolean fechaValida(DatePicker fecha) {
		try {
			fecha.getValue().toString();
			return false;
		} catch (Exception e) {
			return true;
		}
	}

	public static boolean validarEntero(String entero) {
		try {
			Integer.parseInt(entero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean validarFlotante(String flotante) {
		try {
			Float.parseFloat(flotante);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void MensajeError(String titulo, String texto) {
		Dialogs.create().title(titulo).style(DialogStyle.NATIVE).message(texto).showError();
	}

	public static void MensajeWarning(String titulo, String texto) {
		Dialogs.create().title(titulo).style(DialogStyle.NATIVE).message(texto).showWarning();
	}

	public static void MensajeErrorLargo(String titulo, String masthead, String mensaje) {
		Dialogs.create().title(titulo).style(DialogStyle.NATIVE).masthead(masthead).message(mensaje).showError();
	}

	public static void MensajeWarningLargo(String titulo, String masthead, String mensaje) {
		Dialogs.create().title(titulo).style(DialogStyle.NATIVE).masthead(masthead).message(mensaje).showWarning();
	}

	public static void guardarEvento(String usuario, String descripcion) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Evento ev = new Evento();
		ev.setNombreUsuario(usuario);
		ev.setDescripcion(descripcion);
		ev.setFecha(new Date());
		session.save(ev);

		session.getTransaction().commit();

	}

	public static void abrirLinkWeb(String direccion) {

		File htmlFile = new File(direccion);
		try {
			Desktop.getDesktop().browse(htmlFile.toURI());
			// Desktop.getDesktop().open(htmlFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
