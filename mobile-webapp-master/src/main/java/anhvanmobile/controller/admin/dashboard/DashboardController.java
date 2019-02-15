package anhvanmobile.controller.admin.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import anhvanmobile.service.brand.BrandService;
import anhvanmobile.service.order.OrderService;
import anhvanmobile.service.product.ProductService;
import anhvanmobile.service.user.UserService;

@Controller
@RequestMapping("/admin")
public class DashboardController {
	
	@Autowired
	private UserService userSerivce;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	

	@GetMapping("/dashboard")
	public String goDashboard(Model model) {
		model.addAttribute("dashboardPage", true);
		model.addAttribute("totalUser", userSerivce.getTotalUser());
		model.addAttribute("totalOrder", orderService.getTotalOrder());
		model.addAttribute("totalProduct", productService.getTotalProduct());
		model.addAttribute("totalBrand", brandService.getTotalBrand());
		return "admin/dashboard";
	}
}
