package anhvanmobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.dto.SpecificationDTO;
import anhvanmobile.model.Specification;

@Transactional
public interface SpecificationRepository extends JpaRepository<Specification, Integer> {

	Specification findByName(String name);

	@Query("SELECT new anhvanmobile.dto.SpecificationDTO(s.id, s.name) FROM Specification s ORDER BY s.id")
	Page<SpecificationDTO> getPages(Pageable pageable);

	@Query("SELECT new anhvanmobile.dto.SpecificationDTO(s.id, s.name) FROM Specification s WHERE s.name like :search ORDER BY s.id")
	Page<SpecificationDTO> getPages(@Param("search") String seach, Pageable pageable);

}
