package es.uji.ei1027.sape.model;

import java.sql.Date;

import javax.sql.rowset.serial.SerialRef;

public class PeticionRevision {

	String id;
	int numeroProyecto;
	Date fecha;
	String textoPeticion;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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