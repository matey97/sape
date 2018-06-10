package es.uji.ei1027.sape.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import es.uji.ei1027.sape.model.PeticionRevision;
import es.uji.ei1027.sape.model.UserDetails;
import es.uji.ei1027.services.OfertaProyectoService;

/**
 * Controlador para las ofertas de proyectos
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/ofertaproyecto")
public class OfertaProyectoController {

	private OfertaProyectoDAO ofertaproyectoDao;
	private OfertaProyectoService ofertaproyectoService;
	
	@Autowired
	public void setOfertaProyectoDao(OfertaProyectoDAO ofertaProyectoDao) {
		this.ofertaproyectoDao = ofertaProyectoDao;
	}
	
	@Autowired
	public void setOfertaProyectoService(OfertaProyectoService ofertaProyectoService) {
		this.ofertaproyectoService = ofertaProyectoService;
	}
	
	@RequestMapping("/list")
	public String listOfertaProyecto(Model model, HttpSession session) {
		session.setAttribute("result", null);
		UserDetails user = (UserDetails) session.getAttribute("user");
		if (user.getDni() != null) {
			model.addAttribute("ofertaproyectos", ofertaproyectoService.getOfertasParaEstudiante(user.getDni()));
		}else {
			model.addAttribute("ofertaproyectos", ofertaproyectoDao.getOfertaProyectos());
		}
		return "ofertaproyecto/list";
	}
	
	@RequestMapping("/list/{cif}")
	public String listOfertaEmpresa(Model model, @PathVariable String cif) {
		model.addAttribute("ofertaproyectos", ofertaproyectoService.getOfertasDeEmpresa(cif));
		model.addAttribute("cif", cif);
		return "ofertaproyecto/list";
	}
	
	@RequestMapping("/{numeroOferta}")
	public String detallesOfertaEmpresa(Model model, @PathVariable String numeroOferta) {
		model.addAttribute("ofertaproyecto", ofertaproyectoDao.getOfertaProyecto(Integer.valueOf(numeroOferta)));
		model.addAttribute("numeroOferta", numeroOferta);
		return "ofertaproyecto/detalle";
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
	public String editOfertaProyecto(Model model, @PathVariable String numero) {
		model.addAttribute("ofertaproyecto", ofertaproyectoDao.getOfertaProyecto(Integer.valueOf(numero)));
		return "ofertaproyecto/update";
	}
	
	@RequestMapping(value="/update/{numero}", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("ofertaproyecto") OfertaProyecto ofertaproyecto, BindingResult bindingResult, HttpSession session) {
		session.setAttribute("result", null);
		if (bindingResult.hasErrors()) {
			session.setAttribute("result", "bad");
			return "ofertaproyecto/update";
		}
		
		ofertaproyectoDao.updateOfertaProyecto(ofertaproyecto);
		session.setAttribute("result", "ok");
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{numero}")
	public String processDelete(@PathVariable String numero, @ModelAttribute("ofertaproyecto") OfertaProyecto ofertaproyecto) {
		ofertaproyectoDao.deleteOfertaProyecto(ofertaproyecto);
		return "redirect:../list";
	}
}
