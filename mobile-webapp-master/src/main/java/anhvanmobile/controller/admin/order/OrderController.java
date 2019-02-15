package anhvanmobile.controller.admin.order;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import anhvanmobile.dto.OrderDTO;
import anhvanmobile.model.Order;
import anhvanmobile.model.OrderStatus;
import anhvanmobile.service.order.OrderService;
import anhvanmobile.util.pagination.Pagination;

@Controller
@RequestMapping("/admin")
public class OrderController {

	//tiêm service orderService
	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public String all(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page) {
		Page<OrderDTO> pages = orderService.getPages(page, 5);
		model.addAttribute("orders", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		return "admin/order/list";
	}
	
	@GetMapping(value = "/orders", params = "find")
	public String all(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
			@RequestParam("find") String search) {
		Page<OrderDTO> pages = orderService.getPages(search, page, 5);
		model.addAttribute("orders", pages.getContent());
		model.addAttribute("pagination", new Pagination(pages.getTotalPages(), 5, page));
		return "admin/order/list";
	}

	@GetMapping("/order/{id}")
	public String getOrder(Model model, @PathVariable("id") Integer id) {
		Order order = orderService.findById(id);
		model.addAttribute("order", order);
		return "admin/order/edit";
	}

	@PutMapping(value = "/order/{id}", params = "update")
	public String replaceOrder(@Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
		if (result.hasErrors())
			return "admin/order/edit";

		try {
			order.setUpdatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
			orderService.update(order);
			return "redirect:/admin/orders";
		} catch (Exception e) {
			e.printStackTrace();
			return "admin/order/edit";
		}

	}

	@DeleteMapping("/order/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		return orderService.remove(orderService.findById(id));
	}

	@ModelAttribute("statuses")
	public List<OrderStatus> getOrderStatus() {
		return orderService.findAllOrderStatus();
	}

	@ModelAttribute("adminOrderPage")
	public boolean active() {
		return true;
	}

}
