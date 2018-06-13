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

import es.uji.ei1027.sape.dao.PreferenciaAlumnoDAO;
import es.uji.ei1027.sape.model.PreferenciaAlumno;
import es.uji.ei1027.sape.model.UserDetails;

/**
 * Controlador para las preferencias de los alumnos
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/preferenciaalumno")
public class PreferenciaAlumnoController {

	private PreferenciaAlumnoDAO preferenciaalumnoDao;
	private static int count=0;
	
	@Autowired
	public void setPreferenciaAlumnoDao(PreferenciaAlumnoDAO preferenciaalumnoDao) {
		this.preferenciaalumnoDao = preferenciaalumnoDao;
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
		model.addAttribute("preferenciaalumno", preferenciaalumnoDao.getPreferenciasAlumno(((UserDetails)session.getAttribute("user")).getDni()));
		return "preferenciaalumno/list";
	}
	
	@RequestMapping("/list/{dni}")
	public String listPreferenciaAlumno(Model model, @PathVariable String dni) {
		model.addAttribute("preferenciaalumno", preferenciaalumnoDao.getPreferenciasAlumno(dni));
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
	
	@RequestMapping("/add/{titulo}")
	public String addPreferenciaAlumno(Model model, @PathVariable("titulo") String titulo, HttpSession session) {
		PreferenciaAlumno pref = new PreferenciaAlumno();
		pref.setTituloProyecto(titulo);
		pref.setDni(((UserDetails)session.getAttribute("user")).getDni());
		preferenciaalumnoDao.addPreferenciaAlumno(pref);
		session.setAttribute("result", "ok");
		return "redirect:/preferenciaalumno/list";
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