package anhvanmobile.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import anhvanmobile.service.order.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void testOrderList() {
		orderService.getPages(0, 5).getContent().forEach(e->{
			System.out.println(e);
		});
	}
}
