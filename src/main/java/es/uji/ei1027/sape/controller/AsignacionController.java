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
	
	@Autowired
	public void setAsignacionDao(AsignacionDAO asignacionDao) {
		this.asignacionDao = asignacionDao;
	}
	
	@RequestMapping("/index")
	public String indexAsignacion(Model model, HttpSession session){
		UserDetails user = (UserDetails)session.getAttribute("user");
		if (user.getType() == 3)
			return "redirect:/list";
		return "asignacion/index";
	}
	
	@RequestMapping("/list")
	public String listAsignacion(Model model) {
		model.addAttribute("asignacions", asignacionDao.getAsignacions());
		return "asignacion/list";
	}
	
	@RequestMapping(value="/add")
	public String addAsignacion(Model model) {
		model.addAttribute("asignacion", new Asignacion());
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