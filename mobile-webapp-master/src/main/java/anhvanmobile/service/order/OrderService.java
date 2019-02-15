package anhvanmobile.service.order;

import java.util.List;

import anhvanmobile.dto.OrderDTO;
import anhvanmobile.dto.OrderLineDTO;
import anhvanmobile.model.Order;
import anhvanmobile.model.OrderStatus;
import anhvanmobile.model.User;
import anhvanmobile.service.BaseService;
import anhvanmobile.service.DTOService;

public interface OrderService extends BaseService<Order, Integer>, DTOService<OrderDTO> {

	OrderStatus findOrderStatusById(Integer id);

	List<OrderStatus> findAllOrderStatus();

	List<OrderDTO> findOrderListByUsername(String username);
	
	List<OrderLineDTO> findOrderLinesByOrderId(Integer id);

	long countNumberOfOrder(User user);

	long getTotalOrder();

}
