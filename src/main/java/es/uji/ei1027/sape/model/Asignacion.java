package es.uji.ei1027.sape.model;

import java.sql.Date;

public class Asignacion {

	String id;
	Date fechaPropuesta;
	Date fechaAceptacion;
	Date fechaRechazo;
	Date fechaTraspasoIGLU;
	String comentarioPetCambio;
	String estadoAceptadaRechazada;
	String dni;
	String idTutor;
	int numeroProyecto;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFechaPropuesta() {
		return fechaPropuesta;
	}

	public void setFechaPropuesta(Date fechaPropuesta) {
		this.fechaPropuesta = fechaPropuesta;
	}

	public Date getFechaAceptacion() {
		return fechaAceptacion;
	}


	public void setFechaAceptacion(Date fechaAceptacion) {
		this.fechaAceptacion = fechaAceptacion;
	}


	public Date getFechaRechazo() {
		return fechaRechazo;
	}

	public void setFechaRechazo(Date fechaRechazo) {
		this.fechaRechazo = fechaRechazo;
	}

	public Date getFechaTraspasoIGLU() {
		return fechaTraspasoIGLU;
	}

	public void setFechaTraspasoIGLU(Date fechaTraspasoIGLU) {
		this.fechaTraspasoIGLU = fechaTraspasoIGLU;
	}

	public String getComentarioPetCambio() {
		return comentarioPetCambio;
	}

	public void setComentarioPetCambio(String comentarioPetCambio) {
		this.comentarioPetCambio = comentarioPetCambio;
	}

	public String getEstadoAceptadaRechazada() {
		return estadoAceptadaRechazada;
	}

	public void setEstadoAceptadaRechazada(String estadoAceptadaRechazada) {
		this.estadoAceptadaRechazada = estadoAceptadaRechazada;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getIdTutor() {
		return idTutor;
	}

	public void setIdTutor(String idTutor) {
		this.idTutor = idTutor;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	@Override
	public String toString() {
		return "Asignacion [id=" + id + ", fechaPropuesta=" + fechaPropuesta + ", fechaAceptacion=" + fechaAceptacion + ", fechaRechazo=" + fechaRechazo + ", fechaTraspasoIGLU=" + fechaTraspasoIGLU + ", comentarioPetCambio=" + comentarioPetCambio + ", estadoAceptadaRechazada=" + estadoAceptadaRechazada + ", dni=" 
				+ dni + ", idTutor=" + idTutor + ", numeroProyecto=" + numeroProyecto + "]";
	}
}