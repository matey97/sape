package es.uji.ei1027.sape.model;

import java.sql.Date;

/**
 * Clase para modelar la información de las peticiones de revision de asignaciones
 * @author Nacho
 *
 */
public class PeticionRevision {

	int id;
	int numeroProyecto;
	Date fecha;
	String textoPeticion;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getNumeroProyecto() {
		return numeroProyecto;
	}

	public void setNumeroProyecto(int numeroProyecto) {
		this.numeroProyecto = numeroProyecto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTextoPeticion() {
		return textoPeticion;
	}

	public void setTextoPeticion(String textoPeticion) {
		this.textoPeticion = textoPeticion;
	}


	@Override
	public String toString() {
		return "PeticionRevision [id=" + id + ", numeroProyecto=" + numeroProyecto + ", fecha=" + fecha + ", textoPeticion=" 
				+ textoPeticion + "]";
	}
	
}