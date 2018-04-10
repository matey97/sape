package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.EstudianteDAO;
import es.uji.ei1027.sape.model.Estudiante;

/**
 * Controlador para los estudiantes
 * @author Miguel
 *
 */
@Controller
@RequestMapping("/estudiante")
public class EstudianteController {

	private EstudianteDAO estudianteDao;
	
	@Autowired
	public void setEstudianteDAO(EstudianteDAO estudianteDao) {
		this.estudianteDao = estudianteDao;
	}
	
	@RequestMapping("/list")
	public String listEstudiantes(Model model) {
		model.addAttribute("estudiantes", estudianteDao.getEstudiantes());
		return "estudiante/list";
	}
	
	@RequestMapping(value="/add")
	public String addEstudiante(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "estudiante/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("estudiante") Estudiante estudiante, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estudiante/add";
		estudianteDao.addEstudiante(estudiante);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{dni}", method=RequestMethod.GET)
	public String editEstudiante(Model model, @PathVariable String dni) {
		model.addAttribute("estudiante", estudianteDao.getEstudiante(dni));
		return "estudiante/update";
	}
	
	@RequestMapping(value="/update/{dni}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String dni,
									@ModelAttribute("estudiante") Estudiante estudiante, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estudiante/update";
		estudianteDao.updateEstudiante(estudiante);
		return "redirect:../list.html";
	}
	
	@RequestMapping(value="/delete/{dni}")
	public String processDelete(@PathVariable String dni, @ModelAttribute("estudiante") Estudiante estudiante) {
		estudianteDao.deleteEstudiante(estudiante);
		return "redirect:../list.html";
	}
}
