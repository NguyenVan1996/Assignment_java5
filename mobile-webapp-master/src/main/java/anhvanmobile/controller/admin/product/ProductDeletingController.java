package anhvanmobile.controller.admin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import anhvanmobile.service.product.ProductService;

@RestController
@RequestMapping("/admin")
public class ProductDeletingController {

	@Autowired
	private ProductService productService;

	@DeleteMapping("/product/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		return productService.remove(productService.findById(id));
	}

}
