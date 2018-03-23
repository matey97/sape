package es.uji.ei1027.sape.model;

public class EstadoOferta {

	int id;
	String estado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString() {
		return "EstadoOferta [id=" + id + ", estado=" + estado + "]";
	}
	
}
