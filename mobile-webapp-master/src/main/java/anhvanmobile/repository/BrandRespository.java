package anhvanmobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.dto.BrandDTO;
import anhvanmobile.model.Brand;

@Transactional
public interface BrandRespository extends JpaRepository<Brand, Integer> {
	
	Brand findByName(String name);

	@Query("SELECT new anhvanmobile.dto.BrandDTO(b.id, b.name) FROM Brand b ORDER BY b.id")
	Page<BrandDTO> getPages(Pageable pageable);

	@Query("SELECT new anhvanmobile.dto.BrandDTO(b.id, b.name) FROM Brand b WHERE b.name like :search ORDER BY b.id")
	Page<BrandDTO> getPages(@Param("search") String seach, Pageable pageable);
}
