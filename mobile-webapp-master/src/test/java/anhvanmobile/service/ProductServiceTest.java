package anhvanmobile.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;
import anhvanmobile.service.brand.BrandService;
import anhvanmobile.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Autowired
	private BrandService brandService;

	@Test
	public void testA_ProductServiceNotNull() {
		assertNotNull(productService);
	}

	@Test
	public void testB_FindAll() {
		assertTrue(productService.findAll().size() > 0);
	}

	@Test
	public void testC_FindProductByProductName() {
		assertNotNull(productService.findProductByName("Xiaomi Mi A2"));
	}

	@Test
	public void testD_FindProductByProductNameNotFound() {
		assertNull(productService.findProductByName("ABC"));
	}

	@Test(expected = NullPointerException.class)
	public void testE_FindProductWithNameIsNull() {
		productService.findProductByName(null);
	}

	@Test
	public void testF_FindProductByBrand() {
		List<ProductDTO> list = productService.findProductsByBrand(brandService.findBrandByName("Xiaomi"), 0)
				.getContent();
		assertTrue(list.size() > 0);
	}

	@Test(expected = NullPointerException.class)
	public void testF_FindProductByBrandNotFound() {
		Brand brand = brandService.findBrandByName("Xiaomi1");
		productService.findProductsByBrand(brand, 0);
	}
	
	@Test
	public void testG_InsertProduct() {
		Product product = new Product();
		product.setBrand(brandService.findBrandByName("Xiaomi"));
		product.setName("Mobile test");
		product.setPrice(10000);
		product.setUnit("Chiếc");
		product.setQtyInStock(10);
		product.setEnabled(true);
		assertNotNull(productService.create(product));
	}
	
	@Test
	public void testH_UpdateProduct() {
		Product product = productService.findProductByName("Mobile test");
		product.setName("Mobile Update");
		assertNotNull(productService.update(product));
	}
	
	@Test
	public void testW_DeleteProduct() {
		Product product = productService.findProductByName("Mobile Update");
		productService.remove(product);
	}
	
	@Ignore
	@Test
	public void testZ_DeleteProductHasManySpec() {
		Product product = productService.findProductByName("iPhone X 256GB Gray");
		productService.remove(product);
	}

}
