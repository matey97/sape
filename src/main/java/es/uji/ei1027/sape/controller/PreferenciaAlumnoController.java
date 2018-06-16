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

import es.uji.ei1027.sape.dao.PreferenciaAlumnoDAO;
import es.uji.ei1027.sape.model.ListaPreferencias;
import es.uji.ei1027.sape.model.PreferenciaAlumno;
import es.uji.ei1027.sape.model.UserDetails;
import es.uji.ei1027.services.PreferenciaAlumnoService;

/**
 * Controlador para las preferencias de los alumnos
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/preferenciaalumno")
public class PreferenciaAlumnoController {

	private PreferenciaAlumnoDAO preferenciaalumnoDao;
	private PreferenciaAlumnoService preferenciaService;
	private static int count=0;
	
	@Autowired
	public void setPreferenciaAlumnoDao(PreferenciaAlumnoDAO preferenciaalumnoDao) {
		this.preferenciaalumnoDao = preferenciaalumnoDao;
	}
	
	@Autowired
	public void setPreferenciaAlumnoService(PreferenciaAlumnoService preferenciaService) {
		this.preferenciaService = preferenciaService;
	}
	
	@RequestMapping("/list")
	public String listPreferenciaAlumnos(Model model, HttpSession session) {
		String result = (String)session.getAttribute("result");
		if (result != null && count == 0) {
			count++;
		}else {
			count = 0;
			session.setAttribute("result", null);
		}
		model.addAttribute("preferencias", preferenciaalumnoDao.getPreferenciasAlumno(((UserDetails)session.getAttribute("user")).getDni()));
		return "preferenciaalumno/list";
	}
	
	@RequestMapping("/list/{dni}")
	public String listPreferenciaAlumno(Model model, @PathVariable String dni) {
		model.addAttribute("preferencias", preferenciaalumnoDao.getPreferenciasAlumno(dni));
		model.addAttribute("dni", dni);
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
	
	@RequestMapping("/add/{numeroProyecto}")
	public String addPreferenciaAlumno(Model model, @PathVariable("numeroProyecto") String numero, HttpSession session) {
		PreferenciaAlumno pref = new PreferenciaAlumno();
		pref.setNumeroProyecto(Integer.valueOf(numero));
		pref.setDni(((UserDetails)session.getAttribute("user")).getDni());
		pref.setOrden(99);
		preferenciaalumnoDao.addPreferenciaAlumno(pref);
		session.setAttribute("result", "ok");
		return "redirect:/preferenciaalumno/list";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editPreferenciaAlumno(Model model, @PathVariable String id, HttpSession session) {
		String result = (String)session.getAttribute("result");
		if (result != null && count == 0) {
			count++;
		}else {
			count = 0;
			session.setAttribute("result", null);
		}
		model.addAttribute("preferencia", preferenciaalumnoDao.getPreferenciaAlumno(Integer.valueOf(id)));
		return "preferenciaalumno/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@ModelAttribute("preferencia") PreferenciaAlumno preferencia, BindingResult bindingResult, @PathVariable("id") String id, HttpSession session) {
		if (bindingResult.hasErrors())
			return "preferenciaalumno/update";
		if(preferenciaService.updatePreferenciaAlumno(preferencia))
			return "redirect:../list";
		session.setAttribute("result", "bad");
		return "redirect:/preferenciaalumno/update/"+id;
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("preferenciaalumno") PreferenciaAlumno preferenciaalumno) {
		preferenciaalumnoDao.deletePreferenciaAlumno(preferenciaalumno);
		return "redirect:../list";
	}
	
}