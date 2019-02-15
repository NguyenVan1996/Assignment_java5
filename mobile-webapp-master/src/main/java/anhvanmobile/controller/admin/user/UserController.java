package anhvanmobile.controller.admin.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import anhvanmobile.dto.UserDTO;
import anhvanmobile.model.User;
import anhvanmobile.service.user.UserService;
import anhvanmobile.util.pagination.Pagination;

@Controller
@RequestMapping("/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<UserDTO> pages = userService.getPages(page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("users", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/user/list";
	}

	@GetMapping(value = "/users", params = "find")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String search) {
		Page<UserDTO> pages = userService.getPages(search, page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("users", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/user/list";
	}

	@GetMapping("/user/view/{id}")
	public @ResponseBody User get(@PathVariable("id") Integer id) {
		User user = userService.findUserById(id);
		return user;
	}

	@GetMapping("/user/{id}")
	public String get(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userService.findUserById(id));
		return "admin/user/edit";
	}

	@PutMapping("/user/{id}")
	public String update(@Valid @ModelAttribute("user") User user, Errors errors, Model model) {
		if (errors.hasErrors())
			return "admin/user/edit";
		try {
			userService.disableUser(user.getUsername(), user.getEnabled());
			return "redirect:/admin/users";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@DeleteMapping("/user/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		User user = userService.findUserById(id);
		return userService.remove(user);
	}

	@ModelAttribute("adminUserPage")
	public boolean active() {
		return true;
	}
}
