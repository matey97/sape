package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.PreferenciaAlumnoDAO;
import es.uji.ei1027.sape.model.PreferenciaAlumno;

/**
 * Controlador para las preferencias de los alumnos
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/preferenciaalumno")
public class PreferenciaAlumnoController {

	private PreferenciaAlumnoDAO preferenciaalumnoDao;
	
	@Autowired
	public void setPreferenciaAlumnoDao(PreferenciaAlumnoDAO preferenciaalumnoDao) {
		this.preferenciaalumnoDao = preferenciaalumnoDao;
	}
	
	@RequestMapping("/list")
	public String listPreferenciaAlumno(Model model) {
		model.addAttribute("preferenciaalumnos", preferenciaalumnoDao.getPreferenciaAlumnos());
		return "preferenciaalumno/list";
	}
	
	@RequestMapping(value="/add")
	public String addPreferenciaAlumno(Model model) {
		model.addAttribute("preferenciaalumno", new PreferenciaAlumno());
		return "preferenciaalumno/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("preferenciaalumno") PreferenciaAlumno preferenciaalumno, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "preferenciaalumno/add";
		preferenciaalumnoDao.addPreferenciaAlumno(preferenciaalumno);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{orden}", method=RequestMethod.GET)
	public String editPreferenciaAlumno(Model model, @PathVariable String orden) {
		model.addAttribute("preferenciaalumno", preferenciaalumnoDao.getPreferenciaAlumno(Integer.valueOf(orden)));
		return "preferenciaalumno/update";
	}
	
	@RequestMapping(value="/update/{orden}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String orden,
									@ModelAttribute("preferenciaalumno") PreferenciaAlumno preferenciaalumno, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "preferenciaalumno/update";
		preferenciaalumnoDao.updatePreferenciaAlumno(preferenciaalumno);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{orden}")
	public String processDelete(@PathVariable String orden, @ModelAttribute("preferenciaalumno") PreferenciaAlumno preferenciaalumno) {
		preferenciaalumnoDao.deletePreferenciaAlumno(preferenciaalumno);
		return "redirect:../list";
	}
}