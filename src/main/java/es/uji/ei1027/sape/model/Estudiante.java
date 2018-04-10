package es.uji.ei1027.sape.model;

/**
 * Clase para modelar la información de los estudiantes
 * @author Miguel
 *
 */
public class Estudiante {

	String dni;
	String nombre;
	int numeroCreditosAprob;
	double notaMedia;
	String itinerario;
	int numAsignaturasPendientes4t;
	String semestreInicioEstancia;
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getNumeroCreditosAprob() {
		return numeroCreditosAprob;
	}
	public void setNumeroCreditosAprob(int numeroCreditosAprob) {
		this.numeroCreditosAprob = numeroCreditosAprob;
	}
	public double getNotaMedia() {
		return notaMedia;
	}
	public void setNotaMedia(double notaMedia) {
		this.notaMedia = notaMedia;
	}
	public String getItinerario() {
		return itinerario;
	}
	public void setItinerario(String itinerario) {
		this.itinerario = itinerario;
	}
	public int getNumAsignaturasPendientes4t() {
		return numAsignaturasPendientes4t;
	}
	public void setNumAsignaturasPendientes4t(int numAsignaturasPendientes4t) {
		this.numAsignaturasPendientes4t = numAsignaturasPendientes4t;
	}
	public String getSemestreInicioEstancia() {
		return semestreInicioEstancia;
	}
	public void setSemestreInicioEstancia(String semestreInicioEstancia) {
		this.semestreInicioEstancia = semestreInicioEstancia;
	}
	
	@Override
	public String toString() {
		return "Estudiante [dni=" + dni + ", nombre=" + nombre + ", numeroCreditosAprob=" + numeroCreditosAprob
				+ ", notaMedia=" + notaMedia + ", itinerario=" + itinerario + ", numAsignaturasPendientes4t="
				+ numAsignaturasPendientes4t + ", semestreInicioEstancia=" + semestreInicioEstancia + "]";
	}
	
	
}
