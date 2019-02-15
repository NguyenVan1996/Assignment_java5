package anhvanmobile.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;
import anhvanmobile.service.brand.BrandService;
import anhvanmobile.service.product.ProductService;
import anhvanmobile.util.pagination.Pagination;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@GetMapping("/products")
	public String show(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page) {
		Page<ProductDTO> pages = productService.getPages(page, 8);

		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		model.addAttribute("productPage", true);
		return "product/all";
	}

	@GetMapping(value = "/products", params = "find")
	public String search(Model model, @RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String keyword) {
		Page<ProductDTO> pages = productService.search(keyword, page);

		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		model.addAttribute("productPage", true);
		return "product/search";
	}

	@GetMapping("/products/{brandId}")
	public String getProductsByBrand(@PathVariable("brandId") Integer brandId,
			@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
		Page<ProductDTO> pages = productService.findProductsByBrandId(brandId, page);

		model.addAttribute("brand", brandService.findById(brandId));
		model.addAttribute("products", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		model.addAttribute("productPage", true);
		return "product/list-with-brand";
	}

	@GetMapping("/product/{id}")
	public String getProductDetail(@PathVariable("id") Integer id, Model model) {
		Product product = productService.findById(id);

		productService.incrementViewCount(id);
		model.addAttribute("product", product);
		model.addAttribute("productPage", true);
		return "product/detail";
	}

	@ModelAttribute("brands")
	public List<Brand> getBrand() {
		return brandService.findAll();
	}

}
