package anhvanmobile.controller.auth;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import anhvanmobile.dto.OrderDTO;
import anhvanmobile.dto.OrderLineDTO;
import anhvanmobile.model.User;
import anhvanmobile.service.order.OrderService;
import anhvanmobile.service.user.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/profile")
	public String profile() {
		return "auth/profile";
	}

	@GetMapping("/profile/user-info")
	public @ResponseBody User getUser(Principal principal) {
		return userService.findByUsername(principal.getName());
	}

	@PutMapping("/profile/change-fullname")
	public @ResponseBody Boolean changeFullname(@RequestBody String fullname, Principal principal) {
		return userService.changeFullName(principal.getName(), fullname);
	}

	@PutMapping("/profile/change-address")
	public @ResponseBody Boolean changeAddress(@RequestBody String address, Principal principal) {
		return userService.changeAddress(principal.getName(), address);
	}

	@PutMapping("/profile/change-gender")
	public @ResponseBody Boolean changeGender(@RequestBody Boolean gender, Principal principal) {
		return userService.changeGender(principal.getName(), gender);
	}

	@GetMapping("/profile/order/total-order")
	public @ResponseBody Long getCount(Principal principal) {
		return orderService.countNumberOfOrder(userService.findByUsername(principal.getName()));
	}

	@GetMapping("/profile/order/history")
	public @ResponseBody List<OrderDTO> getOrderList(Principal principal) {
		return orderService.findOrderListByUsername(principal.getName());
	}

	@GetMapping("/profile/order/{id}")
	public @ResponseBody List<OrderLineDTO> getOrderLines(@PathVariable("id") Integer id) {
		return orderService.findOrderLinesByOrderId(id);
	}

}
