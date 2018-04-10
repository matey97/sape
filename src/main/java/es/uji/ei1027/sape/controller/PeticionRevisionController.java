package es.uji.ei1027.sape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.PeticionRevisionDAO;
import es.uji.ei1027.sape.model.PeticionRevision;

/**
 * Controlador para las peticiones de revisión de asignaciones
 * @author Nacho
 *
 */
@Controller
@RequestMapping("/peticionrevision")
public class PeticionRevisionController {

	private PeticionRevisionDAO peticionrevisionDao;
	
	@Autowired
	public void setPeticionRevisionDao(PeticionRevisionDAO peticionrevisionDao) {
		this.peticionrevisionDao = peticionrevisionDao;
	}
	
	@RequestMapping("/list")
	public String listPeticionRevision(Model model) {
		model.addAttribute("peticionrevisions", peticionrevisionDao.getPeticionRevisions());
		return "peticionrevision/list";
	}
	
	@RequestMapping(value="/add")
	public String addPeticionRevision(Model model) {
		model.addAttribute("peticionrevision", new PeticionRevision());
		return "peticionrevision/add";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String processAddSubmit(@ModelAttribute("peticionrevision") PeticionRevision peticionrevision, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "peticionrevision/add";
		peticionrevisionDao.addPeticionRevision(peticionrevision);
		return "redirect:list.html";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String editPeticionRevision(Model model, @PathVariable String id) {
		model.addAttribute("peticionrevision", peticionrevisionDao.getPeticionRevision(Integer.valueOf(id)));
		return "peticionrevision/update";
	}
	
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String processUpdateSubmit(@PathVariable String id,
									@ModelAttribute("peticionrevision") PeticionRevision peticionrevision, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "peticionrevision/update";
		peticionrevisionDao.updatePeticionRevision(peticionrevision);
		return "redirect:../list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String processDelete(@PathVariable String id, @ModelAttribute("peticionrevision") PeticionRevision peticionrevision) {
		peticionrevisionDao.deletePeticionRevision(peticionrevision);
		return "redirect:../list";
	}
}
