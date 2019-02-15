package anhvanmobile.service.product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.dto.ProductMostSell;
import anhvanmobile.dto.ProductMostView;
import anhvanmobile.dto.ProductNewest;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;
import anhvanmobile.service.BaseService;
import anhvanmobile.service.DTOService;

@Service
public interface ProductService extends BaseService<Product, Integer>, DTOService<ProductDTO> {

	/**
	 * San pham moi nhat
	 * 
	 * @return
	 */
	public List<ProductNewest> findTop5ProductNewest();
	
	public List<ProductMostSell> findTop4ProductMostSell();
	
	public List<ProductMostView> findTop4ProductMostView();

	public Page<ProductDTO> findProductsByBrand(Brand brand, int page);
	
	public Page<ProductDTO> findProductsByBrandId(Integer brandId, int page);

	public Page<ProductDTO> search(String keyword, int page);

	public Product findProductByName(String name);

	public ProductDTO findProductById(Integer id);
	
	public void incrementViewCount(Integer id);

	public long getTotalProduct();

}
