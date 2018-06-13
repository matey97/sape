package es.uji.ei1027.sape.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.AsignacionDAO;
import es.uji.ei1027.sape.dao.PreferenciaAlumnoDAO;
import es.uji.ei1027.sape.dao.ProfesorTutorDAO;
import es.uji.ei1027.sape.model.Asignacion;
import es.uji.ei1027.sape.model.UserDetails;

/**
 * Controlador para las asignaciones
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/asignacion")
public class AsignacionController {

	private AsignacionDAO asignacionDao;
	private PreferenciaAlumnoDAO preferenciaalumnoDao;
	private ProfesorTutorDAO tutorDao;
	
	@Autowired
	public void setAsignacionDao(AsignacionDAO asignacionDao) {
		this.asignacionDao = asignacionDao;
	}
	
	@Autowired
	public void setPreferenciaAlumnoDao(PreferenciaAlumnoDAO preferenciaalumnoDao) {
		this.preferenciaalumnoDao = preferenciaalumnoDao;
	}
	
	@Autowired
	public void setProfesorTutorDao(ProfesorTutorDAO tutorDao) {
		this.tutorDao = tutorDao;
	}
	
	@RequestMapping("/index")
	public String indexAsignacion(Model model, HttpSession session){
		UserDetails user = (UserDetails)session.getAttribute("user");
		if (user.getType() == 3)
			return "redirect:/asignacion/list";
		return "asignacion/index";
	}
	
	@RequestMapping("/list")
	public String listAsignacion(Model model) {
		model.addAttribute("asignaciones", asignacionDao.getAsignacions());
		return "asignacion/list";
	}
	
	@RequestMapping("/list/{dni}")
	public String listAsignacionByDni(Model model, @PathVariable String dni) {
		model.addAttribute("asignaciones", asignacionDao.getAsignacionByDni(dni));
		model.addAttribute("dni", dni);
		return "asignacion/list";
	}
	
	@RequestMapping(value="/add/{dni}")
	public String addAsignacion(Model model, @PathVariable String dni) {
		Asignacion a = new Asignacion();
		a.setDni(dni);
		model.addAttribute("asignacion", a);
		model.addAttribute("dni", dni);
		model.addAttribute("preferencias", preferenciaalumnoDao.getPreferenciasFinalesAlumno(dni));
		model.addAttribute("tutores", tutorDao.getProfesoresTutores());
		return "asignacion/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("asignacion") Asignacion asignacion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "asignacion/add";
		asignacionDao.addAsignacion(asignacion);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editAsignacion(Model model, @PathVariable String id) {
		model.addAttribute("asignacion", asignacionDao.getAsignacion(Integer.valueOf(id)));
		return "asignacion/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
									@ModelAttribute("asignacion") Asignacion asignacion, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "asignacion/update";
		asignacionDao.updateAsignacion(asignacion);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("asignacion") Asignacion asignacion) {
		asignacionDao.deleteAsignacion(asignacion);
		return "redirect:../list";
	}
}