package anhvanmobile.controller.cart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import anhvanmobile.dto.Cart;
import anhvanmobile.service.product.ProductService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductService service;

	@GetMapping("/add/{id}")
	public @ResponseBody Integer addToCart(@PathVariable("id") Integer id, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}
		if (cart.containsKey(id))
			cart.add(id, cart.get(id).getProduct());
		else
			cart.add(id, service.findProductById(id));
		session.setAttribute("cart", cart);
		return cart.getQuantity();
	}

	@GetMapping("/show")
	public String showCart() {
		return "cart/show-cart";
	}

	@GetMapping(value = "/update", params = { "key", "quantity" })
	public @ResponseBody void update(@RequestParam("key") Integer key, @RequestParam("quantity") Integer quantity,
			HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.updateQuantity(key, quantity);
	}

	@GetMapping(value = "/remove", params = { "key" })
	public @ResponseBody void remove(@RequestParam("key") Integer key, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.remove(key);
		if (cart.isEmpty()) {
			cart = null;
			session.setAttribute("cart", cart);
		}
	}

}
