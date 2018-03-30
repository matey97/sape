package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.ProfesorTutorDAO;
import es.uji.ei1027.sape.model.ProfesorTutor;

@Controller
@RequestMapping("/tutor")
public class ProfesorTutorController {
	private ProfesorTutorDAO profesorTutorDao;
	
	@Autowired
	public void setProfesorTutorDao(ProfesorTutorDAO profesorTutorDao) {
		this.profesorTutorDao = profesorTutorDao;
	}
	
	@RequestMapping("/list")
	public String listTutores(Model model) {
		model.addAttribute("tutores", profesorTutorDao.getProfesoresTutores());
		return "tutor/list";
	}
	
	@RequestMapping(value="/add")
	public String addTutor(Model model) {
		model.addAttribute("tutor", new ProfesorTutor());
		return "tutor/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("tutor") ProfesorTutor profesorTutor, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "tutor/add";
		profesorTutorDao.addProfesorTutor(profesorTutor);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editTutor(Model model, @PathVariable String id) {
		model.addAttribute("tutor", profesorTutorDao.getProfesorTutor(Integer.valueOf(id)));
		return "tutor/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
									@ModelAttribute("tutor") ProfesorTutor profesorTutor, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "tutor/update";
		profesorTutorDao.updateProfesorTutor(profesorTutor);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("tutor") ProfesorTutor profesorTutor) {
		profesorTutorDao.deleteProfesorTutor(profesorTutor);
		return "redirect:../list";
	}
}
