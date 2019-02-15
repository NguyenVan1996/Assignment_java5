package anhvanmobile.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.dto.ProductMostSell;
import anhvanmobile.dto.ProductMostView;
import anhvanmobile.dto.ProductNewest;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;

@Transactional
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

	Product findByBrand(Brand brand);

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p WHERE p.brand.name like :search OR p.name like :search")
	Page<ProductDTO> findProduct(@Param("search") String keyword, Pageable pageable);

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p ORDER BY p.id")
	Page<ProductDTO> findProductBy(Pageable page);

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p WHERE p.brand = :brand")
	Page<ProductDTO> findProductsByBrand(@Param("brand") Brand brand, Pageable pageable);

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p WHERE p.brand.id = :id")
	Page<ProductDTO> findProductsByBrandId(@Param("id") Integer id, Pageable pageable);

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p WHERE p.id = :id")
	ProductDTO findProductById(@Param("id") Integer id);

	@Modifying
	@Query("UPDATE Product p SET p.view = p.view + 1 WHERE p.id= :id")
	void incrementViewCount(@Param("id") Integer id);

	// --------------------------- TOP ------------------------------

	@Query("SELECT new anhvanmobile.dto.ProductNewest"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty) "
			+ "FROM Product p WHERE p.enabled = true ORDER BY p.createdTime DESC")
	List<ProductNewest> findTopProductNewest(Pageable page);

	default List<ProductNewest> findTop5ProductNewest() {
		return findTopProductNewest(PageRequest.of(0, 5));
	}

	@Query("SELECT new anhvanmobile.dto.ProductMostSell"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, "
			+ "SUM(ol.quantity) as qty) FROM Product p LEFT JOIN OrderLine ol ON p = ol.product WHERE p.enabled = true "
			+ "GROUP BY p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty "
			+ "HAVING SUM(ol.quantity) > 0 ORDER BY qty DESC")
	List<ProductMostSell> findTopProductMostSell(Pageable pageable);

	default List<ProductMostSell> findTop4ProductMostSell() {
		return findTopProductMostSell(PageRequest.of(0, 4));
	}

	@Query("SELECT new anhvanmobile.dto.ProductMostView"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty) "
			+ "FROM Product p WHERE p.enabled = true ORDER BY p.view DESC")
	List<ProductMostView> findTopProductMostView(Pageable pageable);

	default List<ProductMostView> findTop4ProductMostView() {
		return findTopProductMostView(PageRequest.of(0, 4));
	}

	@Query("SELECT new anhvanmobile.dto.ProductDTO"
			+ "(p.id, p.brand.name, p.name, p.price, p.qtyInStock, p.shortDescription, p.thumbnail, p.view, p.warranty, p.enabled) "
			+ "FROM Product p WHERE p.brand.name like :search OR p.name like :search")
	Page<ProductDTO> getPages(@Param("search") String seach, Pageable pageable);

}
