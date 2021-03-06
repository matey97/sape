package es.uji.ei1027.sape.model;

import java.sql.Date;

/**
 * Clase para modelar la información de las ofertas 
 * @author Nacho
 *
 */
public class OfertaProyecto {

	int numero;
	String titulo;
	String tarea;
	String objetivo;
	String estado;
	Date fechaAlta;
	Date fechaUltimoCambio;
	String itinerario;
	int idEstancia;
	String empresa;
	String ciudad;
	
	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
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