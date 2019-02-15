package anhvanmobile.controller.order;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import anhvanmobile.dto.Cart;
import anhvanmobile.model.Order;
import anhvanmobile.model.OrderLine;
import anhvanmobile.model.User;
import anhvanmobile.service.order.OrderService;
import anhvanmobile.service.product.ProductService;
import anhvanmobile.service.user.UserService;

@Controller("userOrderController")
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String order(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			return "redirect:/cart/show";
		}

		return "order/order";
	}

	@PostMapping
	public String order(@Valid @ModelAttribute("order") Order order, Errors errors, HttpSession session) {
		if (errors.hasErrors()) {
			return "order/order";
		}

		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null) {
			return "redirect:/cart/show";
		}

		User user = null;
		final List<OrderLine> orderLines = new ArrayList<>();

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			user = (User) principal; // Current logon user
			System.out.println(user);
		} else {
			user = userService.findUserById(1); // default user
		}

		cart.forEach((productId, value) -> {
			OrderLine line = new OrderLine();
			line.setProduct(productService.findById(productId));
			line.setQuantity(value.getQuantity());
			line.setOrder(order);
			orderLines.add(line);
		});

		order.setOrderLines(orderLines);
		order.setAmount(cart.getAmount());
		order.setUser(user);
		order.setStatus(orderService.findOrderStatusById(1)); // default status = Chờ xác nhận

		// insert to database
		orderService.create(order);

		// clear session cart
		session.setAttribute("cart", null);

		return "redirect:/";
	}
	
	@ModelAttribute("order")
	public Order getOrder() {
		return new Order();
	}

}
