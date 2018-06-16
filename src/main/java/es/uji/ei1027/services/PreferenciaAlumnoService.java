package es.uji.ei1027.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.uji.ei1027.sape.dao.PreferenciaAlumnoDAO;
import es.uji.ei1027.sape.model.PreferenciaAlumno;

public class PreferenciaAlumnoService {

	PreferenciaAlumnoDAO preferenciaDao;
	
	@Autowired
	public void setPreferenciaDao(PreferenciaAlumnoDAO preferenciaDao) {
		this.preferenciaDao = preferenciaDao;
	}
	
	public boolean updatePreferenciaAlumno(PreferenciaAlumno p) {
		List<PreferenciaAlumno> preferencias = preferenciaDao.getPreferenciasAlumno(p.getDni());
		for (PreferenciaAlumno preferencia : preferencias) {
			if (p.getOrden() == preferencia.getOrden())
				return false;
		}
		preferenciaDao.updatePreferenciaAlumno(p);
		return true;
	}
	
}
