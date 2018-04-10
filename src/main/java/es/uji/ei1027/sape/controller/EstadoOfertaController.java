package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.EstadoOfertaDAO;
import es.uji.ei1027.sape.model.EstadoOferta;

/**
 * Controlador para los estados de las ofertas
 * @author Miguel
 *
 */
@Controller
@RequestMapping("/estado")
public class EstadoOfertaController {

	private EstadoOfertaDAO estadoDao;
	
	@Autowired
	public void setEstadoOfertaDao(EstadoOfertaDAO estadoDao) {
		this.estadoDao = estadoDao;
	}
	
	@RequestMapping("/list")
	public String listEstados(Model model) {
		model.addAttribute("estados", estadoDao.getEstadosOfertas());
		return "estado/list";
	}
	
	@RequestMapping(value="/add")
	public String addEstado(Model model) {
		model.addAttribute("estado", new EstadoOferta());
		return "estado/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("estado") EstadoOferta estado, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estado/add";
		estadoDao.addEstadoOferta(estado);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editEstancia(Model model, @PathVariable String id) {
		model.addAttribute("estado", estadoDao.getEstadoOferta(Integer.valueOf(id)));
		return "estado/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
									@ModelAttribute("estado") EstadoOferta estado, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "estado/update";
		estadoDao.updateEstadoOferta(estado);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("estado") EstadoOferta estado) {
		estadoDao.deleteEstadoOferta(estado);
		return"redirect:../list";
	}
}
