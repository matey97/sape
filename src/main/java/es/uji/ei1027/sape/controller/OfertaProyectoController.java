package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.OfertaProyectoDAO;
import es.uji.ei1027.sape.model.OfertaProyecto;

@Controller
@RequestMapping("/ofertaproyecto")
public class OfertaProyectoController {

	private OfertaProyectoDAO ofertaproyectoDao;
	
	@Autowired
	public void setOfertaProyectoDao(OfertaProyectoDAO ofertaProyectoDao) {
		this.ofertaproyectoDao = ofertaproyectoDao;
	}
	
	@RequestMapping("/list")
	public String listOfertaProyecto(Model model) {
		model.addAttribute("ofertaproyecto", ofertaproyectoDao.getOfertaProyectos());
		return "ofertaproyecto/list";
	}
	
	@RequestMapping(value="/add")
	public String addOfertaProyecto(Model model) {
		model.addAttribute("ofertaproyecto", new OfertaProyecto());
		return "ofertaproyecto/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("ofertaproyecto") OfertaProyecto ofertaproyecto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "ofertaproyecto/add";
		ofertaproyectoDao.addOfertaProyecto(ofertaproyecto);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{numero}", method=RequestMethod.GET)
	public String editOfertaProyecto(Model model, @PathVariable int numero) {
		model.addAttribute("ofertaproyecto", ofertaproyectoDao.getOfertaProyecto(numero));
		return "ofertaproyecto/update";
	}
	
	@RequestMapping(value="/update/{numero}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable int numero,
									@ModelAttribute("ofertaproyecto") OfertaProyecto ofertaproyecto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "ofertaproyecto/update";
		ofertaproyectoDao.updateOfertaProyecto(ofertaproyecto);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{numero}")
	public String processDelete(@PathVariable int numero, @ModelAttribute("ofertaproyecto") OfertaProyecto ofertaproyecto) {
		ofertaproyectoDao.deleteOfertaProyecto(ofertaproyecto);
		return "redirect:../list";
	}
}