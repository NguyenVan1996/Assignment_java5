package anhvanmobile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.model.ProductSpec;

@Transactional
public interface ProductSpecRepository extends JpaRepository<ProductSpec, Integer> {

}
