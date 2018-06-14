package es.uji.ei1027.sape.model;

/**
 * Clase para modelar la información de los profesores tutores de proyectos
 * @author Miguel
 *
 */
public class ProfesorTutor {
	
	String id;
	String nombre;
	String departamento;
	String despacho;
	String email;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getDespacho() {
		return despacho;
	}
	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "ProfesorTutor [id=" + id + ", nombre=" + nombre + ", departamento=" + departamento + ", despacho="
				+ despacho + ", email=" + email + "]";
	}
	
	
	
	

}
