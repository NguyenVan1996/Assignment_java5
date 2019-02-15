package anhvanmobile.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.service.product.ProductService;
import anhvanmobile.util.pagination.Pagination;

@Controller
@RequestMapping("/admin")
public class ProductListController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<ProductDTO> pages = productService.getPages(page, 5);
		Pagination pagination = new Pagination(pages.getTotalPages(), 10, page);
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", pagination);
		model.addAttribute("adminProductPage", true);
		return "admin/product/list";
	}
	
	@GetMapping(value = "/products", params = "find")
	public String all(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String search) {
		Page<ProductDTO> pages = productService.getPages(search, page, 5);
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 10, page));
		model.addAttribute("adminProductPage", true);
		return "admin/product/list";
	}

}
