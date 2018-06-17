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
import es.uji.ei1027.sape.model.PreferenciaAlumno;
import es.uji.ei1027.sape.model.UserDetails;
import es.uji.ei1027.sape.services.OfertaProyectoService;

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
	private static int count = 0;
	
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
		String result = (String)session.getAttribute("result");
		if (result != null && count == 0) {
			count++;
		}else {
			count = 0;
			session.setAttribute("result", null);
		}
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
	
	@RequestMapping("/visibiliza")
	public String visibilizaOferta(Model model,  HttpSession session) {
		ofertaproyectoDao.visibilizaOfertas();
		return "redirect:/ofertaproyecto/list";
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
	
	@RequestMapping(value="/update/{num}", method=RequestMethod.GET)
	public String editOfertaProyecto(Model model, @PathVariable String num, HttpSession session) {
		String result = (String)session.getAttribute("result");
		if (result != null && count == 0) {
			count++;
		}else {
			count = 0;
			session.setAttribute("result", null);
		}
		model.addAttribute("ofertaproyecto", ofertaproyectoDao.getOfertaProyecto(Integer.valueOf(num)));
		return "ofertaproyecto/update";
	}
	
	@RequestMapping(value="/update/{num}", method=RequestMethod.POST)
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
