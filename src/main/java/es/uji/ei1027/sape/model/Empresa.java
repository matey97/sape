package es.uji.ei1027.sape.model;

/**
 * Clase para modelar la imformación de las empresas
 * @author Miguel
 *
 */
public class Empresa {

	String cif;
	String nombre;
	String domicilio;
	String poblacion;
	String telefonoPrincipal;
	
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getTelefonoPrincipal() {
		return telefonoPrincipal;
	}
	public void setTelefonoPrincipal(String telefonoPrincipal) {
		this.telefonoPrincipal = telefonoPrincipal;
	}
	
	@Override
	public String toString() {
		return "Empresa [cif=" + cif + ", nombre=" + nombre + ", domicilio=" + domicilio + ", poblacion=" + poblacion
				+ ", telefonoPrincipal=" + telefonoPrincipal + "]";
	}
	
	
	
	
}
