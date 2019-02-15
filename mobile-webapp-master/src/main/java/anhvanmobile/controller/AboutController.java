package anhvanmobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {
	
	@GetMapping("/about")
	public String goAboutPage(Model model) {
		model.addAttribute("aboutPage", true);
		return "about";
	}
}
