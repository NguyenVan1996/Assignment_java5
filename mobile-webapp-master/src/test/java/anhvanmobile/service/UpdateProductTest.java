package anhvanmobile.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.model.Product;
import anhvanmobile.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateProductTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testOrderList() {
		Product product = productService.findById(1);
		System.err.println(product.getProductSpecs().size());
		product.getProductSpecs().remove(1);
		System.err.println(product.getProductSpecs().size());
		Product updated = productService.update(product);
		System.err.println(updated.getProductSpecs().size());
	}
}
