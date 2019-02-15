package anhvanmobile.controller.admin.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import anhvanmobile.exception.DuplicateProductNameException;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;
import anhvanmobile.model.ProductSpec;
import anhvanmobile.model.ProductSpecDetail;
import anhvanmobile.model.Specification;
import anhvanmobile.service.brand.BrandService;
import anhvanmobile.service.product.ProductService;
import anhvanmobile.service.specification.SpecificationSerivce;
import anhvanmobile.util.StringUtils;

@Controller
@RequestMapping("/admin/product/{id}")
@SessionAttributes(names = { "brands", "specifications" })
public class ProductUpdatingController {

	@Autowired
	private BrandService brandService;

	@Autowired
	private SpecificationSerivce specService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ServletContext context;

	@GetMapping
	public String form(@PathVariable("id") Integer id, Model model) {
		Product product = productService.findById(id);
		model.addAttribute("product", product);
		return "admin/product/edit";
	}

	@PostMapping(params = "addSpecRow")
	public String addSpecRow(@ModelAttribute("product") Product product, @RequestParam("addSpecRow") Integer rowIndex) {
		addProductSpecificationRow(product);
		return "admin/product/edit";
	}

	@PostMapping(params = "addSpecDetailRow")
	public String addSpecDetailRow(@ModelAttribute("product") Product product,
			@RequestParam("addSpecDetailRow") Integer rowIndex) {
		ProductSpec productSpec = product.getProductSpecs().get(rowIndex.intValue());
		ProductSpecDetail productSpecDetail = new ProductSpecDetail();
		productSpecDetail.setProductSpec(productSpec);
		List<ProductSpecDetail> productSpecDetails = productSpec.getProductSpecDetails();
		if(productSpecDetails==null)
			productSpec.setProductSpecDetails(new ArrayList<>());
		productSpec.getProductSpecDetails().add(productSpecDetail);
		return "admin/product/edit";
	}

	@PostMapping(params = "removeSpecRow")
	public String removeSpecRow(@ModelAttribute("product") Product product,
			@RequestParam("removeSpecRow") Integer rowIndex) {
		product.getProductSpecs().remove(rowIndex.intValue());
		return "admin/product/edit";
	}

	@PostMapping(params = "removeSpecDetailRow")
	public String removeSpecDetailRow(@ModelAttribute("product") Product product,
			@RequestParam("removeSpecDetailRow") String values) {
		String[] rows = values.split(",");

		int specIndex = Integer.parseInt(rows[0]);
		int specDetailIndex = Integer.parseInt(rows[1]);

		ProductSpec productSpec = product.getProductSpecs().get(specIndex);

		productSpec.getProductSpecDetails().remove(specDetailIndex);

		if (productSpec.getProductSpecDetails().isEmpty())
			product.getProductSpecs().remove(specIndex);

		return "admin/product/edit";
	}

	@PostMapping(params = "update")
	public String update(@Valid @ModelAttribute("product") Product product, Errors errors, SessionStatus status) {

		if (errors.hasErrors()) {
			return "admin/product/edit";
		}

		List<ProductSpec> productSpecs = product.getProductSpecs();

		if (productSpecs == null) {
			product.setProductSpecs(new ArrayList<>());
		} else {
			productSpecs.forEach(spec -> {
				spec.getProductSpecDetails().forEach(specDetail -> {
					if (specDetail.getProductSpec() == null) {
						specDetail.setProductSpec(spec);
					}
				});
			});
		}

		MultipartFile image = product.getImageFile();
		if (image != null) {
			if (!image.isEmpty()) {
				try {
					String brandFolder = product.getBrand().getName().toLowerCase().replaceAll("\\s+", "");
					String name = StringUtils.formatProductName(product.getName());
					String extension = StringUtils.getFileExtension(image.getOriginalFilename());
					String filename = name + extension;
					String parent = context.getRealPath("/images/products/" + brandFolder);
					File file = new File(parent);
					if (!file.exists())
						file.mkdirs();

					String path = String.format("%s/%s", parent, filename);
					String thumbnail = String.format("/images/products/%s/%s", brandFolder, filename);
					image.transferTo(new File(path));
					product.setThumbnail(thumbnail);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		try {
			productService.update(product);
			status.setComplete();
		} catch (DuplicateProductNameException e) {
			e.printStackTrace();
			errors.rejectValue("name", "product.name", "Trùng tên sản phẩm!");
			return "admin/product/edit";
		}

		return "redirect:/admin/products";
	}

	@ModelAttribute("brands")
	public List<Brand> getBrands() {
		return brandService.findAll();
	}

	@ModelAttribute("specifications")
	public List<Specification> getSpecifications() {
		return specService.findAll();
	}

	private void addProductSpecificationRow(Product product) {
		List<ProductSpec> productSpecs = product.getProductSpecs();
		List<ProductSpecDetail> productSpecDetails = new ArrayList<>();

		if (productSpecs == null) {
			productSpecs = new ArrayList<>();
		}

		ProductSpec productSpec = new ProductSpec();

		ProductSpecDetail productSpecDetail = new ProductSpecDetail();
		productSpecDetail.setProductSpec(productSpec);
		productSpecDetails.add(productSpecDetail);

		productSpec.setProduct(product);
		productSpec.setProductSpecDetails(productSpecDetails);
		productSpecs.add(productSpec);

		product.setProductSpecs(productSpecs);
	}

}
