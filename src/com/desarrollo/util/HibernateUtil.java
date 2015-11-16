package com.desarrollo.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.desarrollo.modelo.Acuario;
import com.desarrollo.modelo.AgrupacionEspecie;
import com.desarrollo.modelo.Alerta;
import com.desarrollo.modelo.Alimentacion;
import com.desarrollo.modelo.Alimento;
import com.desarrollo.modelo.Especie;
import com.desarrollo.modelo.Evento;
import com.desarrollo.modelo.Informe;
import com.desarrollo.modelo.Insumo;
import com.desarrollo.modelo.Limpieza;
import com.desarrollo.modelo.Medicion;
import com.desarrollo.modelo.PaqueteAlimento;
import com.desarrollo.modelo.PaqueteInsumo;
import com.desarrollo.modelo.Parametro;
import com.desarrollo.modelo.Perfil;
import com.desarrollo.modelo.Permiso;
import com.desarrollo.modelo.Sistema;
import com.desarrollo.modelo.Tarea;
import com.desarrollo.modelo.Usuario;

@SuppressWarnings({ "deprecation", "unused" })
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			config.addAnnotatedClass(Alimento.class);
			config.addAnnotatedClass(PaqueteAlimento.class);
			config.addAnnotatedClass(Especie.class);
			config.addAnnotatedClass(Acuario.class);
			config.addAnnotatedClass(Parametro.class);
			config.addAnnotatedClass(Permiso.class);
			config.addAnnotatedClass(Perfil.class);
			config.addAnnotatedClass(Usuario.class);
			config.addAnnotatedClass(PaqueteInsumo.class);
			config.addAnnotatedClass(Insumo.class);
			config.addAnnotatedClass(Alerta.class);
			config.addAnnotatedClass(Informe.class);
			config.addAnnotatedClass(Tarea.class);
			config.addAnnotatedClass(Alimentacion.class);
			config.addAnnotatedClass(Limpieza.class);
			config.addAnnotatedClass(Medicion.class);
			config.addAnnotatedClass(AgrupacionEspecie.class);
			config.addAnnotatedClass(Evento.class);
			config.configure("hibernate.cfg.xml");
			return config.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Error en la creaccion de SessionFactory." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public static List<Usuario> obtenerListaUsuarios() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			usuarios = session.createQuery("FROM com.desarrollo.modelo.Usuario").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return usuarios;
	}

	@SuppressWarnings("unchecked")
	public static List<Parametro> obtenerListaParametros() {
		List<Parametro> parametros = new ArrayList<Parametro>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			parametros = session.createQuery("FROM Parametro").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return parametros;
	}

	@SuppressWarnings("unchecked")
	public static List<Evento> obtenerListaEventos() {
		List<Evento> eventos = new ArrayList<Evento>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			eventos = session.createQuery("FROM Evento").list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.flush();
			session.close();
		}
		return eventos;
	}

	@SuppressWarnings("unchecked")
	public static List<Perfil> obtenerListaPerfiles() {
		List<Perfil> perfiles = new ArrayList<Perfil>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			perfiles = session.createQuery("FROM Perfil").list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.flush();
			session.close();
		}
		return perfiles;
	}

	@SuppressWarnings("unchecked")
	private static List<Alimento> obtenerListaAlimentos() {
		List<Alimento> alimentos = new ArrayList<Alimento>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			alimentos = session.createQuery("FROM Alimento").list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.flush();
			session.close();
		}
		return alimentos;
	}

	@SuppressWarnings("unchecked")
	private static List<Insumo> obtenerListaInsumos() {
		List<Insumo> insumos = new ArrayList<Insumo>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			insumos = session.createQuery("FROM Insumo").list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.flush();
			session.close();
		}
		return insumos;
	}

	@SuppressWarnings("unchecked")
	private static List<Especie> obtenerListaEspecies() {
		List<Especie> especies = new ArrayList<Especie>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			especies = session.createQuery("FROM Especie").list();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			session.flush();
			session.close();
		}
		return especies;
	}

	@SuppressWarnings("unchecked")
	private static List<Acuario> obtenerListaAcuarios() {
		List<Acuario> acuarios = new ArrayList<Acuario>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			acuarios = session.createQuery("FROM Acuario").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return acuarios;
	}

	@SuppressWarnings("unchecked")
	private static List<Tarea> obtenerListaTareas() {
		List<Tarea> tareas = new ArrayList<Tarea>();
		Transaction trns = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			trns = session.beginTransaction();
			tareas = session.createQuery("FROM Tarea").list();
		} catch (Exception e) {

		} finally {
			session.flush();
			session.close();
		}
		return tareas;
	}

	public static void traerDatosBase() {
		Sistema.getSistema().setUsuarios(null);
		Sistema.getSistema().setPerfiles(null);
		Sistema.getSistema().setParametros(null);
		Sistema.getSistema().setAlimentos(null);
		Sistema.getSistema().setInsumos(null);
		Sistema.getSistema().setEspecies(null);
		Sistema.getSistema().setAcuarios(null);
		Sistema.getSistema().setTareas(null);
		Sistema.getSistema().setEventos(null);
		Sistema.getSistema().setParametros(obtenerListaParametros());
		Sistema.getSistema().setUsuarios(obtenerListaUsuarios());
		Sistema.getSistema().setPerfiles(obtenerListaPerfiles());
		Sistema.getSistema().setAlimentos(obtenerListaAlimentos());
		Sistema.getSistema().setInsumos(obtenerListaInsumos());
		Sistema.getSistema().setEspecies(obtenerListaEspecies());
		Sistema.getSistema().setAcuarios(obtenerListaAcuarios());
		Sistema.getSistema().setTareas(obtenerListaTareas());
		Sistema.getSistema().setEventos(obtenerListaEventos());
	}

}