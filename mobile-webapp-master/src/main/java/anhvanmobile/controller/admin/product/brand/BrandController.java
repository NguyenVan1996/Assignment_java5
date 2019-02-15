package anhvanmobile.controller.admin.product.brand;

import java.io.File;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import anhvanmobile.dto.BrandDTO;
import anhvanmobile.exception.DuplicateBrandNameException;
import anhvanmobile.model.Brand;
import anhvanmobile.service.brand.BrandService;
import anhvanmobile.util.StringUtils;
import anhvanmobile.util.pagination.Pagination;

@Controller
@RequestMapping("/admin/product")
public class BrandController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private ServletContext context;

	static final Logger logger = LoggerFactory.getLogger(BrandController.class);

	@GetMapping("/brands")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<BrandDTO> pages = brandService.getPages(page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("brands", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/product/brand/list";
	}

	@GetMapping(value = "/brands", params = "find")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String search) {
		Page<BrandDTO> pages = brandService.getPages(search, page, 5);

		Pagination pagination = new Pagination(pages.getTotalPages(), 5, page);
		model.addAttribute("brands", pages.getContent());
		model.addAttribute("pagination", pagination);
		return "admin/product/brand/list";
	}

	@GetMapping("/brand")
	public String add() {
		return "admin/product/brand/add";
	}

	@GetMapping("/brand/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("brand", brandService.findById(id));
		return "admin/product/brand/edit";
	}

	@PostMapping("/brand")
	public String save(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {

		if (errors.hasErrors()) {
			return "admin/product/brand/add";
		}

		MultipartFile image = brand.getImage();
		if (image != null) {
			if (!image.isEmpty()) {
				try {
					String extension = StringUtils.getFileExtension(image.getOriginalFilename());
					String filename = brand.getName() + extension;
					String path = context.getRealPath("/images/brands/" + filename);
					File file = new File(path);
					image.transferTo(file);
					brand.setLogo("/images/brands/" + filename);
				} catch (Exception e) {
				}
			}
		}

		try

		{
			brandService.create(brand);
			status.setComplete();
			return "redirect:/admin/product/brands";
		} catch (DuplicateBrandNameException e) {
			e.printStackTrace();
			errors.rejectValue("name", "brand.name", "Trùng tên thương hiệu!");
			return "admin/product/brand/add";
		}
	}

	@PostMapping("/brand/{id}")
	public String replace(@Valid @ModelAttribute("brand") Brand brand, Errors errors, SessionStatus status) {

		if (errors.hasErrors()) {
			return "admin/product/brand/edit";
		}

		MultipartFile image = brand.getImage();
		if (image != null) {
			if (!image.isEmpty()) {
				try {
					String extension = StringUtils.getFileExtension(image.getOriginalFilename());
					String filename = brand.getName() + extension;
					String path = context.getRealPath("/images/brands/" + filename);
					File file = new File(path);
					image.transferTo(file);
					brand.setLogo("/images/brands/" + filename);
				} catch (Exception e) {
				}
			}
		}

		try {
			brandService.update(brand);
			status.setComplete();
			return "redirect:/admin/product/brands";
		} catch (DuplicateBrandNameException e) {
			e.printStackTrace();
			errors.rejectValue("name", "brand.name", "Trùng tên thương hiệu!");
			return "admin/product/brand/edit";
		}
	}

	@DeleteMapping("/brand/{id}")
	public @ResponseBody boolean delete(@PathVariable Integer id) {
		return brandService.remove(brandService.findById(id));
	}

	@ModelAttribute("brand")
	public Brand getBrand() {
		return new Brand();
	}

	@ModelAttribute("adminProductPage")
	public boolean active() {
		return true;
	}
}
