package es.uji.ei1027.sape.model;

import java.sql.Date;

/**
 * Clase para modelar la informacion de las preferencias de los alumnos
 * @author Nacho
 *
 */
public class PreferenciaAlumno {

	int orden;
	Date fechaUltimoCambio;
	String estado;
	String dni;
	int numeroProyecto;

	public int getOrden() {
		return orden;
	}

	public void setOrden(int orden) {
		this.orden = orden;
	}

	public Date getFechaUltimoCambio() {
		return fechaUltimoCambio;
	}

	public void setFechaUltimoCambio(Date fechaUltimoCambio) {
		this.fechaUltimoCambio = fechaUltimoCambio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	@Override
	public String toString() {
		return "PreferenciaAlumno [orden=" + orden + ", fechaUltimoCambio=" + fechaUltimoCambio + ", estado=" + estado + ", dni=" + dni + ", numeroProyecto=" 
				+ numeroProyecto + "]";
	}
}