package anhvanmobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {
	
	@GetMapping("/contact")
	public String goContactPage(Model model) {
		model.addAttribute("contactPage", true);
		return "contact";
	}
}
