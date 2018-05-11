package es.uji.ei1027.sape.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.uji.ei1027.sape.dao.UserDAO;
import es.uji.ei1027.sape.model.UserDetails;

@Controller
public class LoginController {
	@Autowired
    private UserDAO userDao;
	
    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDetails());
        return "login";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String checkLogin(@ModelAttribute("user") UserDetails user,          
                BindingResult bindingResult, HttpSession session) {
        UserValidator userValidator = new UserValidator(); 
        userValidator.validate(user, bindingResult); 
        if (bindingResult.hasErrors()) {
            return "login";
        }
        user = userDao.loadUserByUsername(user.getUsername(),user.getPassword()); 
        if (user == null) {
            bindingResult.rejectValue("password", "badpw", "Contraseña incorrecta"); 
            return "login";
        }
        
        session.setAttribute("user", user); 
            
        String url = (String)session.getAttribute("nextUrl");
        session.removeAttribute("nextUrl");
        if (url != null){
        	return "redirect:"+url;
        }

        if (user.getType() == 1)
        	return "estudiante/index";
        if (user.getType() == 2)
        	return "btc/index";
        return "dcc/index";
    }
    
//    @RequestMapping("/custom_index")
//    public String customIndex(Model model){
//    	return "custom_index";
//    }

    @RequestMapping("/logout") 
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/";
    }
}
