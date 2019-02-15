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
public class ProductTest {
	
	@Autowired
	private ProductService productService;
	
	@Test
	public void testProduct() {
		Product product = productService.findById(1);
		product.getProductSpecs().forEach(e->{
			System.out.println(e.getProduct().getName());
			System.out.println(e.getProductSpecDetails().size());
		});
	}
}
