package anhvanmobile;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.service.product.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileWebappApplicationTests {

	@SuppressWarnings("unused")
	@Autowired
	private ProductService productService;
	
}
