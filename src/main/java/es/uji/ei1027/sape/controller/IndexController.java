package es.uji.ei1027.sape.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uji.ei1027.sape.model.UserDetails;

@Controller
public class IndexController {
	
	@RequestMapping("/custom_index")
	public String showIndex(Model model, HttpSession session){
		UserDetails user = (UserDetails)session.getAttribute("user");
		if (user == null){
			return "redirect:/index";
		}
		if (user.getType() == UserDetails.STUDENT)
			return "estudiante/index";
		return "btc/index"; //BTC and DCC share index
	}
	
	@RequestMapping("/nosotros")
	public String nosotros() {
		return "nosotros";
	}
}
