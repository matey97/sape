package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.EstanciaDAO;
import es.uji.ei1027.sape.model.Estancia;

/**
 * Controlador para las estancias
 * @author Miguel
 *
 */
@Controller
@RequestMapping("/estancia")
public class EstanciaController {

	private EstanciaDAO estanciaDao;
	
	@Autowired
	public void setEstanciaDao(EstanciaDAO estanciaDao) {
		this.estanciaDao = estanciaDao;
	}
	
	@RequestMapping("/list")
	public String listEstanias(Model model) {
		model.addAttribute("estancias", estanciaDao.getEstancias());
		return "estancia/list";
	}
	
	@RequestMapping(value="/add")
	public String addEstancia(Model model) {
		model.addAttribute("estancia", new Estancia());
		return "estancia/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("estancia") Estancia estancia, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estancia/add";
		estanciaDao.addEstancia(estancia);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editEstancia(Model model, @PathVariable String id) {
		model.addAttribute("estancia", estanciaDao.getEstancia(Integer.valueOf(id)));
		return "estancia/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
									@ModelAttribute("estancia") Estancia estancia, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estancia/update";
		estanciaDao.updateEstancia(estancia);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("estancia") Estancia estancia) {
		estanciaDao.deleteEstancia(estancia);
		return "redirect:../list";
	}
}
