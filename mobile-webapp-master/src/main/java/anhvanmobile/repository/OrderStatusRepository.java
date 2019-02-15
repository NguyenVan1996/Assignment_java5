package anhvanmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.model.OrderStatus;

@Transactional
public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {

}
