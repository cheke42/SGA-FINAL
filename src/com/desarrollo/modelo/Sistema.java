package com.desarrollo.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import org.hibernate.Session;

import com.desarrollo.enumerados.TipoPerfil;
import com.desarrollo.property.AcuarioProperty;
import com.desarrollo.property.AlimentoInsumoProperty;
import com.desarrollo.property.EspecieProperty;
import com.desarrollo.property.PaqueteProperty;
import com.desarrollo.property.TareaProperty;
import com.desarrollo.property.UsuarioProperty;
import com.desarrollo.reportes.AbstractJasperReports;
import com.desarrollo.reportes.ConnectionDB;
import com.desarrollo.util.HibernateUtil;
import com.desarrollo.util.Utilidades;

public class Sistema {

	private static Sistema sistema = new Sistema();

	private String nombre = "SGA  -  Sistema de Gestion de Acuarios";
	private Image favicon = new Image("file:recursos/imagenes/favicon.png");
	private List<Evento> eventos;
	private List<Acuario> acuarios;
	private List<Alimento> alimentos;
	private List<Insumo> insumos;
	private List<Usuario> usuarios;
	private List<Perfil> perfiles;
	private List<Parametro> parametros;
	private List<Alerta> alertas;
	private List<Informe> informes;
	private List<Especie> especies;
	private List<Permiso> permisos;
	private List<Tarea> tareas;
	private static ConnectionDB con;

	private Sistema() {
		eventos = new ArrayList<Evento>();
		perfiles = new ArrayList<Perfil>();
		usuarios = new ArrayList<Usuario>();
		parametros = new ArrayList<Parametro>();
		alimentos = new ArrayList<Alimento>();
		insumos = new ArrayList<Insumo>();
		especies = new ArrayList<Especie>();
		acuarios = new ArrayList<Acuario>();
		tareas = new ArrayList<Tarea>();
	}

	private static void conectToDatabase() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String db = "acuariobd";

		con = new ConnectionDB(driver, db, url);
		con.connect();
	}

	public void cargarReporte(String reporte) {
		conectToDatabase();
		AbstractJasperReports.createReport(con.getConn(), reporte);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Image getFavicon() {
		return favicon;
	}

	public void setFavicon(Image favicon) {
		this.favicon = favicon;
	}

	public static Sistema getSistema() {

		return sistema;

	}

	public Boolean acuarioExiste(Acuario acuario) {
		Boolean btmp = false;
		for (int i = 0; i < this.acuarios.size(); i++) {
			if (this.acuarios.get(i).getIdAcuario() == acuario.getIdAcuario()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public Boolean acuarioExiste(int idAcuario) {
		Boolean btmp = false;
		for (int i = 0; i < this.acuarios.size(); i++) {
			if (this.acuarios.get(i).getIdAcuario() == idAcuario) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarAcuario(Acuario acuario) {
		if (!acuarioExiste(acuario)) {
			this.acuarios.add(acuario);
		}
	}

	public void quitarAcuario(Acuario acuario) {
		Integer itmp = null;
		if (acuarioExiste(acuario)) {
			for (int i = 0; i < this.acuarios.size(); i++) {
				if (this.acuarios.get(i).getIdAcuario() == acuario.getIdAcuario()) {
					itmp = i;
				}
			}
		}

		if (itmp != null) {
			this.acuarios.remove(itmp);
		}
	}

	public Boolean alimentoExiste(Alimento alimento) {
		Boolean btmp = false;
		for (int i = 0; i < this.alimentos.size(); i++) {
			if (this.alimentos.get(i).getIdAlimento() == alimento.getIdAlimento()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public boolean alimentoExiste(String nombreAlimento) {
		boolean btmp = false;
		for (int i = 0; i < this.alimentos.size(); i++) {
			if (this.alimentos.get(i).getNombre().equals(nombreAlimento)) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarAlimento(Alimento alimento) {
		if (!alimentoExiste(alimento)) {
			this.alimentos.add(alimento);
		}
	}

	public void quitarAlimento(Alimento alimento) {
		Integer itmp = null;
		if (alimentoExiste(alimento)) {
			for (int i = 0; i < this.alimentos.size(); i++) {
				if (this.alimentos.get(i).getIdAlimento() == alimento.getIdAlimento()) {
					itmp = i;
				}
			}
		}

		if (itmp != null) {
			this.alimentos.remove(itmp);
		}
	}

	public Boolean insumoExiste(Insumo insumo) {
		Boolean btmp = false;
		for (int i = 0; i < this.insumos.size(); i++) {
			if (this.insumos.get(i).getIdInsumo() == insumo.getIdInsumo()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public boolean insumoExiste(String nombreInsumo) {
		boolean btmp = false;
		for (int i = 0; i < this.insumos.size(); i++) {
			if (this.insumos.get(i).getNombre().equals(nombreInsumo)) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarInsumo(Insumo insumo) {
		if (!insumoExiste(insumo)) {
			this.insumos.add(insumo);
		}
	}

	public void quitarInsumo(Insumo insumo) {
		Integer itmp = null;
		if (insumoExiste(insumo)) {
			for (int i = 0; i < this.insumos.size(); i++) {
				if (this.insumos.get(i).getIdInsumo() == insumo.getIdInsumo()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.insumos.remove(itmp);
		}
	}

	public Boolean especieExiste(String nombre) {
		Boolean btmp = false;
		for (int i = 0; i < this.especies.size(); i++) {
			if (especies.get(i).getNombre().equals(nombre)) {
				btmp = true;
			}
		}

		return btmp;
	}

	public Boolean especieExiste(Especie especie) {
		Boolean btmp = false;

		for (int i = 0; i < this.especies.size(); i++) {
			if (especies.get(i).getIdEspecie() == especie.getIdEspecie()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarEspecie(Especie especie) {
		if (!especieExiste(especie)) {
			this.especies.add(especie);
		}
	}

	public void quitarEspecie(Especie especie) {
		Integer itmp = null;
		if (especieExiste(especie)) {
			for (int i = 0; i < this.especies.size(); i++) {
				if (this.especies.get(i).getIdEspecie() == especie.getIdEspecie()) {
					itmp = i;
				}
			}
		}

		if (itmp != null) {
			this.especies.remove(itmp);
		}
	}

	public Boolean permisoExiste(Permiso permiso) {
		Boolean btmp = false;

		for (int i = 0; i < this.permisos.size(); i++) {
			if (this.permisos.get(i).getIdPermiso() == permiso.getIdPermiso()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarPermiso(Permiso permiso) {
		if (!permisoExiste(permiso)) {
			this.permisos.add(permiso);
		}
	}

	public void quitarPermiso(Permiso permiso) {
		Integer itmp = null;
		if (permisoExiste(permiso)) {
			for (int i = 0; i < this.permisos.size(); i++) {
				if (this.permisos.get(i).getIdPermiso() == permiso.getIdPermiso()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.permisos.remove(itmp);
		}

	}

	public Boolean informeExiste(Informe informe) {
		Boolean btmp = false;
		for (int i = 0; i < this.informes.size(); i++) {
			if (this.informes.get(i).getIdInforme() == informe.getIdInforme()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarInforme(Informe informe) {
		if (!informeExiste(informe)) {
			this.informes.add(informe);
		}
	}

	public void quitarInforme(Informe informe) {
		Integer itmp = null;
		if (informeExiste(informe)) {
			for (int i = 0; i < this.informes.size(); i++) {
				if (this.informes.get(i).getIdInforme() == informe.getIdInforme()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.informes.remove(itmp);
		}

	}

	public Boolean alertaExiste(Alerta alerta) {
		Boolean btmp = false;
		for (int i = 0; i < this.alertas.size(); i++) {
			if (this.alertas.get(i).getIdAlerta() == alerta.getIdAlerta()) {
				btmp = true;
			}
		}
		return btmp;

	}

	public void agregarAlerta(Alerta alerta) {
		if (!alertaExiste(alerta)) {
			this.alertas.add(alerta);
		}
	}

	public void quitarAlerta(Alerta alerta) {
		Integer itmp = null;
		if (alertaExiste(alerta)) {
			for (int i = 0; i < this.alertas.size(); i++) {
				if (this.alertas.get(i).getIdAlerta() == alerta.getIdAlerta()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.alertas.remove(itmp);
		}

	}

	public Boolean parametroExiste(Parametro parametro) {
		Boolean btmp = false;
		for (int i = 0; i < this.parametros.size(); i++) {
			if (this.parametros.get(i).getIdParametro() == parametro.getIdParametro()) {
				btmp = true;
			}
		}
		return btmp;
	}

	public void agregarParametro(Parametro parametro) {
		if (!parametroExiste(parametro)) {
			this.parametros.add(parametro);
		}
	}

	public void quitarParametro(Parametro parametro) {
		Integer itmp = null;
		if (parametroExiste(parametro)) {
			for (int i = 0; i < this.parametros.size(); i++) {
				if (this.parametros.get(i).getIdParametro() == parametro.getIdParametro()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.parametros.remove(itmp);
		}
	}

	public Boolean usuarioExiste(Usuario usuario) {
		Boolean btmp = false;
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()) {
				btmp = true;
			}
		}

		return btmp;
	}

	public boolean usuarioExiste(String nombreUsuario) {
		boolean btmp = false;
		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getNombre().equals(nombreUsuario)) {
				btmp = true;
			}
		}

		return btmp;
	}

	public void agregarUsuario(Usuario usuario) {
		if (!usuarioExiste(usuario)) {
			this.usuarios.add(usuario);
		}
	}

	public void quitarUsuario(Usuario usuario) {
		Integer itmp = null;
		if (usuarioExiste(usuario)) {
			for (int i = 0; i < this.usuarios.size(); i++) {
				if (this.usuarios.get(i).getIdUsuario() == usuario.getIdUsuario()) {
					itmp = i;
				}
			}
		}
		if (itmp != null) {
			this.usuarios.remove(itmp);
		}

	}

	public Boolean logearse(String nombre, String password) {
		Boolean btmp = false;
		Usuario tusuario = buscarUsuario(nombre);
		if (tusuario != null) {
			if (tusuario.getPassword().equals(password)) {
				btmp = true;
			}
		}
		return btmp;

	}

	public void agregarPerfilUsuario(Integer idUsuario, Perfil perfil) {
		Usuario utmp = new Usuario();
		utmp.setIdUsuario(idUsuario);

		if (usuarioExiste(utmp)) {
			for (int i = 0; i < this.usuarios.size(); i++) {
				if (this.usuarios.get(i).getIdUsuario() == idUsuario) {
					this.usuarios.get(i).agregarPerfil(perfil);
				}
			}
		}

	}

	public void quitarPerfilUsuario(Integer idUsuario, Perfil perfil) {
		Usuario utmp = new Usuario();
		utmp.setIdUsuario(idUsuario);

		if (usuarioExiste(utmp)) {
			for (int i = 0; i < this.usuarios.size(); i++) {
				if (this.usuarios.get(i).getIdUsuario() == idUsuario) {
					this.usuarios.get(i).quitarPerfil(perfil);
				}
			}
		}

	}

	public void confirmarCreacionUsuario(Object idUsuario) {
		// NO TENGO IDEA DE EN QUE PENSARON CUANDO PUSIERON ESTE METODO.
	}

	public void asentarActividad(Integer idActividad, String observaciones) {
		for (int i = 0; i < this.tareas.size(); i++) {
			// PENSAR EL RAZONAMIENTO DE ESTO EN GRUPO..
		}
	}

	public void asentarMedicion(Object idMedicion, Object valorMedido, Object observaciones) {

	}

	// Busca en la lista de usuarios el nombre de usuario pasado por parametro y
	// devuelve todos sus datos.
	public Usuario buscarUsuario(String nombre) {
		Usuario utmp = null;

		for (int i = 0; i < this.usuarios.size(); i++) {
			if (this.usuarios.get(i).getNombre().equals(nombre)) {
				utmp = this.usuarios.get(i);
			}
		}
		return utmp;

	}

	public Alimento buscarAlimento(String nombre) {
		Alimento tAlim = null;
		for (int i = 0; i < this.alimentos.size(); i++) {
			if (this.alimentos.get(i).getNombre().equals(nombre)) {
				tAlim = this.alimentos.get(i);
			}
		}
		return tAlim;
	}

	public Insumo buscarInsumo(String nombre) {
		Insumo ins = null;
		for (int i = 0; i < this.insumos.size(); i++) {
			if (this.insumos.get(i).getNombre().equals(nombre)) {
				ins = this.insumos.get(i);
			}
		}
		return ins;
	}

	public void mostrarEventosAcuario(Integer idAcuario, Date fechaInicio, Date fechaFin) {

	}

	public void mostrarEventosUsuario(Integer idUsuario, Date fechaInicio, Date fechaFin) {

	}

	public void verPlanificacion(Integer idAcuario, Date fechaInicio, Date fechaFin) {

	}

	public void buscarTarea(Integer idAcuario, Date fechaLimite) {

	}

	// Busca en la lista de especies el nombre de especie pasado por parametro y
	// devuelve todos sus datos.
	public Especie buscarEspecie(String nombre) {
		Especie etmp = null;
		for (int i = 0; i < this.especies.size(); i++) {
			if (this.especies.get(i).getNombre().equals(nombre)) {
				etmp = this.especies.get(i);

			}
		}
		return etmp;

	}

	public void reemplazarEspecie(Especie datosEspecieNueva, String nombreEspecieAnterior) {
		Especie especie = buscarEspecie(nombreEspecieAnterior);
		especie.setAlimentos(datosEspecieNueva.getAlimentos());
		especie.setDescripcion(datosEspecieNueva.getDescripcion());
		especie.setNombre(datosEspecieNueva.getNombre());
		especie.setParametros(datosEspecieNueva.getParametros());

	}

	// public void agregarIndividuosAAcuario(Integer idAcuario,
	// String nombreEspecie, Integer cantidadIndividuos) {
	// Acuario acuario = buscarAcuario(idAcuario);
	// Especie especie = buscarEspecie(nombreEspecie);
	// acuario.agregarIndividuos(especie.getIdEspecie(), cantidadIndividuos);
	// }

	public Acuario buscarAcuario(Integer idAcuario) {
		Acuario atmp = null;
		System.out.println("valor del id buscado" + idAcuario);
		if (acuarioExiste(idAcuario)) {
			for (int i = 0; i < this.acuarios.size(); i++) {
				if (this.acuarios.get(i).getIdAcuario() == idAcuario) {
					atmp = this.acuarios.get(i);

				}
			}
		}
		return atmp;
	}

	public void agregarStockAlimento(Object idAlimento, Object datosPaqueteAlimento) {

	}

	@SuppressWarnings("null")
	public Alimento buscarAlimento(Integer idAlimento) {
		Alimento atmp = null;
		atmp.setIdAlimento(idAlimento);
		if (alimentoExiste(atmp)) {
			for (int i = 0; i < this.alimentos.size(); i++) {
				if (this.alimentos.get(i).getIdAlimento() == idAlimento) {
					atmp = this.alimentos.get(i);
				}
			}
		}
		return atmp;

	}

	public void generarInformeEspecie(Object idEspecie) {

	}

	public void generarInformeAcuario(Object idAcuario) {

	}

	public void getResumenEspecie(Object idEspecie) {

	}

	public void getResumenAcuario(Object idAcuario) {

	}

	public void generarReporteAlertas(Object fechaInicio, Object fechaFin) {

	}

	public void buscarAlertas(Object fechaInicio, Object fechaTope) {

	}

	public void mostrarEstadoAcuario(Object idAcuario) {

	}

	public void modificarEstadoAcuario(Object idAcuario, Object nuevoEstado) {

	}

	public void mostrarIndividuosCompatibles(Object idAcuario1, Object idAcuario2) {

	}

	public void transferirIndividuos(Object idOrigen, Object idDestino, Object cantidad) {

	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}

	public Perfil obtenerPerfil(TipoPerfil tipoPerfil) {
		Perfil tperfil = null;
		for (int i = 0; i < 0; i++) {
			if (perfiles.get(i).getNombre().equals(tipoPerfil)) {
				tperfil = perfiles.get(i);
			}

		}
		return tperfil;
	}

	public ObservableList<com.desarrollo.property.UsuarioProperty> pasarArrayUsuarioAProperty(List<Usuario> ussers) {
		ObservableList<com.desarrollo.property.UsuarioProperty> usuariosObservables = FXCollections
				.observableArrayList();

		if (ussers.size() > 0) {
			for (int i = 0; i < ussers.size(); i++) {
				String estado = "Deshabilitado";
				if (ussers.get(i).getEstado()) {
					estado = "Habilitado";
				}
				usuariosObservables.add(new UsuarioProperty(ussers.get(i).getNombre(), ussers.get(i).getPassword(),
						estado));
			}
		}
		return usuariosObservables;

	}

	public ObservableList<com.desarrollo.property.EspecieProperty> pasarArrayEspecieAProperty(List<Especie> ussers) {
		ObservableList<com.desarrollo.property.EspecieProperty> especieObservable = FXCollections.observableArrayList();

		if (ussers.size() > 0) {
			for (int i = 0; i < ussers.size(); i++) {
				especieObservable.add(new EspecieProperty(ussers.get(i).getNombre(), ussers.get(i).getDescripcion(),
						Integer.toString(ussers.get(i).getCantidad())));

			}
		}
		return especieObservable;

	}

	public ObservableList<AlimentoInsumoProperty> pasarArrayAlimentosAProperty(List<Alimento> alimentos) {
		ObservableList<AlimentoInsumoProperty> alimentosObservable = FXCollections.observableArrayList();

		for (int i = 0; i < alimentos.size(); i++) {
			String nombre = alimentos.get(i).getNombre();
			String cantidadDisponible = Float.toString(alimentos.get(i).getCantidadDisponible()) + " "
					+ Utilidades.miniUnidad.get(alimentos.get(i).getUnidadMedida());
			String cantidadMinima = Float.toString(alimentos.get(i).getCantidadMinima()) + " "
					+ Utilidades.miniUnidad.get(alimentos.get(i).getUnidadMedida());
			String cantidadMaxima = Float.toString(alimentos.get(i).getCantidadMaxima()) + " "
					+ Utilidades.miniUnidad.get(alimentos.get(i).getUnidadMedida());
			alimentosObservable.add(new AlimentoInsumoProperty(nombre, cantidadDisponible, cantidadMinima,
					cantidadMaxima));
		}
		return alimentosObservable;
	}

	public ObservableList<AlimentoInsumoProperty> pasarArrayInsumosAProperty(List<Insumo> insumos) {
		ObservableList<AlimentoInsumoProperty> insumosObservables = FXCollections.observableArrayList();

		for (int i = 0; i < insumos.size(); i++) {
			String nombre = insumos.get(i).getNombre();
			String cantidadDisponible = (Float.toString(insumos.get(i).getCantidadDisponible()) + " " + Utilidades.miniUnidad
					.get(insumos.get(i).getUnidadMedida()));
			String cantidadMinima = Float.toString(insumos.get(i).getCantidadMinima()) + " "
					+ Utilidades.miniUnidad.get(insumos.get(i).getUnidadMedida());
			String cantidadMaxima = Float.toString(insumos.get(i).getCantidadMaxima()) + " "
					+ Utilidades.miniUnidad.get(insumos.get(i).getUnidadMedida());
			insumosObservables.add(new AlimentoInsumoProperty(nombre, cantidadDisponible, cantidadMinima,
					cantidadMaxima));
		}
		return insumosObservables;
	}

	@SuppressWarnings("deprecation")
	public ObservableList<PaqueteProperty> pasarArrayPaqueteAlimento(List<PaqueteAlimento> listaPaqueteAlimento) {
		ObservableList<PaqueteProperty> paquetesObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaPaqueteAlimento.size(); i++) {

			String id = Integer.toString(listaPaqueteAlimento.get(i).getIdPaqueteAlimento());

			String fechaAdquisicion = (Integer.toString(listaPaqueteAlimento.get(i).getFechaAdquisicion().getDate())
					+ "-" + Integer.toString(listaPaqueteAlimento.get(i).getFechaAdquisicion().getMonth() + 1) + "-" + Integer
					.toString(listaPaqueteAlimento.get(i).getFechaAdquisicion().getYear() + 1900));

			String fechaVencimiento = (Integer.toString(listaPaqueteAlimento.get(i).getFechaVencimiento().getDate())
					+ "-" + Integer.toString(listaPaqueteAlimento.get(i).getFechaVencimiento().getMonth() + 1) + "-" + Integer
					.toString(listaPaqueteAlimento.get(i).getFechaVencimiento().getYear() + 1900));
			String cantidad = Float.toString(listaPaqueteAlimento.get(i).getCantidad());

			String disponible = "Disponible";

			if (!listaPaqueteAlimento.get(i).isDisponible()) {
				disponible = "No Disponible";
			}

			PaqueteProperty paqueteAlimento = new PaqueteProperty(id, fechaAdquisicion, fechaVencimiento, cantidad,
					disponible);
			paquetesObservables.add(paqueteAlimento);
		}

		return paquetesObservables;
	}

	@SuppressWarnings("deprecation")
	public ObservableList<PaqueteProperty> pasarArrayPaqueteInsumo(List<PaqueteInsumo> listaPaqueteInsumo) {
		ObservableList<PaqueteProperty> paquetesObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaPaqueteInsumo.size(); i++) {

			String id = Integer.toString(listaPaqueteInsumo.get(i).getIdPaqueteInsumo());

			String fechaAdquisicion = (Integer.toString(listaPaqueteInsumo.get(i).getFechaAdquisicion().getDate())
					+ "-" + Integer.toString(listaPaqueteInsumo.get(i).getFechaAdquisicion().getMonth() + 1) + "-" + Integer
					.toString(listaPaqueteInsumo.get(i).getFechaAdquisicion().getYear() + 1900));

			String fechaVencimiento = (Integer.toString(listaPaqueteInsumo.get(i).getFechaCaducacion().getDate()) + "-"
					+ Integer.toString(listaPaqueteInsumo.get(i).getFechaCaducacion().getMonth() + 1) + "-" + Integer
					.toString(listaPaqueteInsumo.get(i).getFechaCaducacion().getYear() + 1900));
			String cantidad = Float.toString(listaPaqueteInsumo.get(i).getCantidad());

			String disponible = "Disponible";

			if (!listaPaqueteInsumo.get(i).isDisponible()) {
				disponible = "No Disponible";
			}

			PaqueteProperty paqueteInsumo = new PaqueteProperty(id, fechaAdquisicion, fechaVencimiento, cantidad,
					disponible);
			paquetesObservables.add(paqueteInsumo);
		}
		return paquetesObservables;

	}

	@SuppressWarnings("deprecation")
	public ObservableList<AcuarioProperty> pasarArrayAcuariosAProperty(List<Acuario> listaAcuarios) {
		ObservableList<AcuarioProperty> acuariosObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaAcuarios.size(); i++) {

			String numero = Integer.toString(listaAcuarios.get(i).getIdAcuario());

			String fechaCreacion = (Integer.toString(listaAcuarios.get(i).getFechaCreacion().getDate()) + "-"
					+ Integer.toString(listaAcuarios.get(i).getFechaCreacion().getMonth() + 1) + "-" + Integer
					.toString(listaAcuarios.get(i).getFechaCreacion().getYear() + 1900));

			String capacidad = Float.toString(listaAcuarios.get(i).getCapacidad()) + " Lt";

			String estado = "Disponible";

			if (!listaAcuarios.get(i).isDisponible()) {
				estado = "No Disponible";
			}

			AcuarioProperty acuario = new AcuarioProperty(numero, fechaCreacion, capacidad, estado);
			acuariosObservables.add(acuario);
		}

		return acuariosObservables;

	}

	@SuppressWarnings("deprecation")
	public ObservableList<TareaProperty> pasarArrayTareasProperty(List<Tarea> listaTareas) {
		ObservableList<TareaProperty> tareasObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaTareas.size(); i++) {

			String numeroTarea = Integer.toString(listaTareas.get(i).getIdTarea());

			String fechaCreacion = (Integer.toString(listaTareas.get(i).getFechaCrecion().getDate()) + "-"
					+ Integer.toString(listaTareas.get(i).fechaCrecion.getMonth() + 1) + "-" + Integer
					.toString(listaTareas.get(i).getFechaCrecion().getYear() + 1900));

			String tipoTarea = listaTareas.get(i).getTipo();

			String creador = listaTareas.get(i).getUsuario().getNombre();

			String estado = "Pendiente";
			if (listaTareas.get(i).isRealizada()) {
				estado = "Realizada";
			}

			TareaProperty tarea = new TareaProperty(numeroTarea, fechaCreacion, tipoTarea, creador, estado);
			tareasObservables.add(tarea);
		}

		return tareasObservables;
	}

	@SuppressWarnings("deprecation")
	public ObservableList<TareaProperty> pasarArrayTareasRealizadasProperty(List<Tarea> listaTareas) {
		ObservableList<TareaProperty> tareasObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaTareas.size(); i++) {

			if (listaTareas.get(i).isRealizada()) {
				String numeroTarea = Integer.toString(listaTareas.get(i).getIdTarea());

				String fechaCreacion = (Integer.toString(listaTareas.get(i).getFechaCrecion().getDate()) + "-"
						+ Integer.toString(listaTareas.get(i).fechaCrecion.getMonth() + 1) + "-" + Integer
						.toString(listaTareas.get(i).getFechaCrecion().getYear() + 1900));

				String tipoTarea = listaTareas.get(i).getTipo();

				String creador = listaTareas.get(i).getUsuario().getNombre();

				String estado = "Pendiente";
				if (listaTareas.get(i).isRealizada()) {
					estado = "Realizada";
				}

				TareaProperty tarea = new TareaProperty(numeroTarea, fechaCreacion, tipoTarea, creador, estado);
				tareasObservables.add(tarea);
			}

		}

		return tareasObservables;
	}

	@SuppressWarnings("deprecation")
	public ObservableList<TareaProperty> pasarArrayTareasPendientesProperty(List<Tarea> listaTareas) {
		ObservableList<TareaProperty> tareasObservables = FXCollections.observableArrayList();

		for (int i = 0; i < listaTareas.size(); i++) {

			if (!listaTareas.get(i).isRealizada()) {
				String numeroTarea = Integer.toString(listaTareas.get(i).getIdTarea());

				String fechaCreacion = (Integer.toString(listaTareas.get(i).getFechaCrecion().getDate()) + "-"
						+ Integer.toString(listaTareas.get(i).fechaCrecion.getMonth() + 1) + "-" + Integer
						.toString(listaTareas.get(i).getFechaCrecion().getYear() + 1900));

				String tipoTarea = listaTareas.get(i).getTipo();

				String creador = listaTareas.get(i).getUsuario().getNombre();

				String estado = "Pendiente";
				if (listaTareas.get(i).isRealizada()) {
					estado = "Realizada";
				}

				TareaProperty tarea = new TareaProperty(numeroTarea, fechaCreacion, tipoTarea, creador, estado);
				tareasObservables.add(tarea);
			}

		}

		return tareasObservables;
	}

	@SuppressWarnings("deprecation")
	public PaqueteProperty pasarPaqueteAlimentoAProperty(PaqueteAlimento paqueteAlimento) {

		String id = Integer.toString(paqueteAlimento.getIdPaqueteAlimento());

		String fechaAdquisicion = (Integer.toString(paqueteAlimento.getFechaAdquisicion().getDate()) + "-"
				+ Integer.toString(paqueteAlimento.getFechaAdquisicion().getMonth() + 1) + "-" + Integer
				.toString(paqueteAlimento.getFechaAdquisicion().getYear() + 1900));

		String fechaVencimiento = (Integer.toString(paqueteAlimento.getFechaVencimiento().getDate()) + "-"
				+ Integer.toString(paqueteAlimento.getFechaVencimiento().getMonth() + 1) + "-" + Integer
				.toString(paqueteAlimento.getFechaVencimiento().getYear() + 1900));
		String cantidad = Float.toString(paqueteAlimento.getCantidad());

		String disponible = "Disponible";

		if (!paqueteAlimento.isDisponible()) {
			disponible = "No Disponible";
		}

		PaqueteProperty paqueteObservable = new PaqueteProperty(id, fechaAdquisicion, fechaVencimiento, cantidad,
				disponible);
		return paqueteObservable;
	}

	@SuppressWarnings("deprecation")
	public PaqueteProperty pasarPaqueteInsumoAProperty(PaqueteInsumo paqueteInsumo) {
		String id = Integer.toString(paqueteInsumo.getIdPaqueteInsumo());

		String fechaAdquisicion = (Integer.toString(paqueteInsumo.getFechaAdquisicion().getDate()) + "-"
				+ Integer.toString(paqueteInsumo.getFechaAdquisicion().getMonth() + 1) + "-" + Integer
				.toString(paqueteInsumo.getFechaAdquisicion().getYear() + 1900));

		String fechaVencimiento = (Integer.toString(paqueteInsumo.getFechaCaducacion().getDate()) + "-"
				+ Integer.toString(paqueteInsumo.getFechaCaducacion().getMonth() + 1) + "-" + Integer
				.toString(paqueteInsumo.getFechaCaducacion().getYear() + 1900));

		String cantidad = Float.toString(paqueteInsumo.getCantidad());

		String disponible = "Disponible";

		if (!paqueteInsumo.isDisponible()) {
			disponible = "No Disponible";
		}

		PaqueteProperty paqueteObservable = new PaqueteProperty(id, fechaAdquisicion, fechaVencimiento, cantidad,
				disponible);
		return paqueteObservable;

	}

	public List<Parametro> getParametros() {
		return parametros;
	}

	public void setParametros(List<Parametro> parametros) {
		this.parametros = parametros;
	}

	public List<Alimento> getAlimentos() {
		return alimentos;
	}

	public void setAlimentos(List<Alimento> alimentos) {
		this.alimentos = alimentos;
	}

	public List<Insumo> getInsumos() {
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}

	public List<Especie> getEspecies() {
		return especies;
	}

	public void setEspecies(List<Especie> especies) {
		this.especies = especies;
	}

	public List<Acuario> getAcuarios() {
		return acuarios;
	}

	public void setAcuarios(List<Acuario> acuarios) {
		this.acuarios = acuarios;
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public boolean especieCompatibleAcuario(Especie especie, Acuario acuario) {
		boolean compatible = false;
		boolean ghCompatible = false;
		boolean khCompatible = false;
		boolean phCompatible = false;
		boolean temperaturaCompatible = false;
		boolean tipoAguaCompatible = false;

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Especie esp = (Especie) session.get(Especie.class, especie.getIdEspecie());
		Acuario acu = (Acuario) session.get(Acuario.class, acuario.getIdAcuario());
		System.out.println("Agua de especie =" + esp.getTipoAgua() + ". Agua de acuario=" + acu.getTipoAgua());

		if (esp.getTipoAgua().equals(acu.getTipoAgua())) {
			tipoAguaCompatible = true;

			for (int i = 0; i <= 3; i++) {
				if ((esp.getParametros().get(i).getMinimo() <= acu.getParametros().get(i).getMinimo())
						& (esp.getParametros().get(i).getMaximo() >= acu.getParametros().get(i).getMaximo())) {

					switch (i) {
					case 0:
						ghCompatible = true;
						break;

					case 1:
						khCompatible = true;
						break;

					case 2:
						phCompatible = true;
						break;

					case 3:
						temperaturaCompatible = true;
						break;
					}
				}

			}
		}

		compatible = ghCompatible & khCompatible & phCompatible & temperaturaCompatible & tipoAguaCompatible;
		session.getTransaction().commit();
		return compatible;
	}

	public void actualizarCantidadEspecie(int idEspecie) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Especie especie = (Especie) session.get(Especie.class, idEspecie);

		especie.calcularTotal();
		session.update(especie);
		session.getTransaction().commit();
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

}
