package es.uji.ei1027.sape.model;

import java.sql.Date;

/**
 * Clase para modelar la información de las ofertas 
 * @author Nacho
 *
 */
public class OfertaProyecto {

	int numero;
	String tarea;
	String objetivo;
	int estado;
	Date fechaAlta;
	Date fechaUltimoCambio;
	String itinerario;
	int idEstancia;
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTarea() {
		return tarea;
	}

	public void setTarea(String tarea) {
		this.tarea = tarea;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaUltimoCambio() {
		return fechaUltimoCambio;
	}

	public void setFechaUltimoCambio(Date fechaUltimoCambio) {
		this.fechaUltimoCambio = fechaUltimoCambio;
	}

	public String getItinerario() {
		return itinerario;
	}

	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}

	public int getIdEstancia() {
		return idEstancia;
	}

	public void setIdEstancia(int idEstancia) {
		this.idEstancia = idEstancia;
	}

	@Override
	public String toString() {
		return "OfertaProyecto [numero=" + numero + ", tarea=" + tarea + ", objetivo=" + objetivo + ", estado=" + estado + ", fechaAlta=" + fechaAlta + ", fechaUltimoCambio=" + fechaUltimoCambio + ", itinerario=" + itinerario + ", idEstancia=" 
				+ idEstancia + "]";
	}
	
}