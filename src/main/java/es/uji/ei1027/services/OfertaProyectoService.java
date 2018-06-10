package es.uji.ei1027.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.sape.dao.EstudianteDAO;
import es.uji.ei1027.sape.dao.OfertaProyectoDAO;
import es.uji.ei1027.sape.model.Estudiante;
import es.uji.ei1027.sape.model.OfertaProyecto;

public class OfertaProyectoService {
	
	@Autowired
	OfertaProyectoDAO ofertaProyectoDAO;
	
	@Autowired
	EstudianteDAO estudianteDAO;

	public List<OfertaProyecto> getOfertasDeEmpresa(String cif){
		return ofertaProyectoDAO.getOfertasEmpresa(cif);
	}
	
	 /**
	  * Listado de ofertas del itinerario del alumno
	  * @param dni
	  * @return
	  */
	public List<OfertaProyecto> getOfertasParaEstudiante(String dni){
		Estudiante e = estudianteDAO.getEstudiante(dni);
		return ofertaProyectoDAO.getOfertasItinerario(e.getItinerario().toLowerCase());
	}
}
