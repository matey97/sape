package es.uji.ei1027.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.sape.dao.OfertaProyectoDAO;
import es.uji.ei1027.sape.model.OfertaProyecto;

public class OfertaProyectoService {
	
	@Autowired
	OfertaProyectoDAO ofertaProyectoDAO;

	public List<OfertaProyecto> getOfertasDeEmpresa(String cif){
		return ofertaProyectoDAO.getOfertasEmpresa(cif);
	}
}
