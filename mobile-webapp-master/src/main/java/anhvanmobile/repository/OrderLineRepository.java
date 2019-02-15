package anhvanmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.model.OrderLine;

@Transactional
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {


}
