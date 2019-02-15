package anhvanmobile.controller.admin.product.specification;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import anhvanmobile.dto.SpecificationDTO;
import anhvanmobile.exception.DuplicateSpecificationNameException;
import anhvanmobile.model.Specification;
import anhvanmobile.service.specification.SpecificationSerivce;
import anhvanmobile.util.pagination.Pagination;

@Controller
@RequestMapping("/admin/product")
public class SpecificationController {

	@Autowired
	private SpecificationSerivce specificationSerivce;

	@GetMapping("/specifications")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<SpecificationDTO> pages = specificationSerivce.getPages(page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("specifications", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/product/specification/list";
	}

	@GetMapping(value = "/specifications", params = "find")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String search) {
		Page<SpecificationDTO> pages = specificationSerivce.getPages(search, page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("specifications", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/product/specification/list";
	}

	@GetMapping("/specification")
	public String add() {
		return "admin/product/specification/add";
	}

	@PostMapping(value = "/specification", params = "create")
	public String create(@Valid @ModelAttribute("specification") Specification specification, BindingResult errors,
			Model model) {

		if (errors.hasErrors())
			return "admin/product/specification/add";

		try {
			specificationSerivce.create(specification);
			return "redirect:/admin/product/specifications";
		} catch (DuplicateSpecificationNameException e) {
			e.printStackTrace();
			errors.rejectValue("name", "specification.name", "Trùng tên thông số!");
			return "admin/product/specification/edit";
		}
	}

	@GetMapping("/specification/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Specification specification = specificationSerivce.findById(id);
		model.addAttribute("specification", specification);
		return "admin/product/specification/edit";
	}

	@PutMapping(value = "/specification/{id}", params = "update")
	public String update(@Valid @ModelAttribute("specification") Specification specification, Errors errors,
			Model model) {

		if (errors.hasErrors())
			return "admin/product/specification/edit";

		try {
			specificationSerivce.update(specification);
			return "redirect:/admin/product/specifications";
		} catch (DuplicateSpecificationNameException e) {
			e.printStackTrace();
			errors.rejectValue("name", "specification.name", "Trùng tên thông số!");
			return "admin/product/specification/edit";
		}
	}

	@DeleteMapping("/specification/{id}")
	public @ResponseBody Boolean edit(@PathVariable("id") Integer id) {
		return specificationSerivce.remove(specificationSerivce.findById(id));
	}

	@ModelAttribute("specification")
	public Specification getSpecification() {
		return new Specification();
	}

	@ModelAttribute("adminProductPage")
	public boolean active() {
		return true;
	}

}
