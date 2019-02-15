package anhvanmobile.service;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SearchTest {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void testSearchProduct() {
		Page<ProductDTO> page = productRepository.findProduct("%iPhone%", PageRequest.of(0, 10));
		page.getContent().forEach(e -> {
			System.out.println(e.getName());
		});
		assertTrue(page.getContent().isEmpty());
	}
}
