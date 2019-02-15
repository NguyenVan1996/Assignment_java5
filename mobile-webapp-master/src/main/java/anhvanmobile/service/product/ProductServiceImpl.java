package anhvanmobile.service.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anhvanmobile.dto.ProductDTO;
import anhvanmobile.dto.ProductMostSell;
import anhvanmobile.dto.ProductMostView;
import anhvanmobile.dto.ProductNewest;
import anhvanmobile.exception.DuplicateProductNameException;
import anhvanmobile.model.Brand;
import anhvanmobile.model.Product;
import anhvanmobile.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product create(Product p) {
		Product product = findProductByName(p.getName());
		if (product != null)
			throw new DuplicateProductNameException();
		return productRepository.save(p);
	}

	@Override
	public Product update(Product p) {
		Product product = findProductByName(p.getName());
		if (product != null)
			if (!product.getId().equals(p.getId()))
				throw new DuplicateProductNameException();
		return productRepository.save(p);
	}

	@Override
	public boolean remove(Product object) {
		try {
			productRepository.delete(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Page<ProductDTO> getPages(int page, int size) {
		if (page <= 0)
			page = 1;
		if (size < 5)
			size = 5;
		return productRepository.findProductBy(PageRequest.of(page - 1, size));
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.getOne(id);
	}

	@Override
	public List<ProductNewest> findTop5ProductNewest() {
		return productRepository.findTop5ProductNewest();
	}

	@Override
	public List<ProductMostSell> findTop4ProductMostSell() {
		return productRepository.findTop4ProductMostSell();
	}
	
	@Override
	public List<ProductMostView> findTop4ProductMostView() {
		return productRepository.findTop4ProductMostView();
	}

	@Override
	public Product findProductByName(String name) {
		if (name == null)
			throw new NullPointerException();
		return productRepository.findByName(name);
	}

	@Override
	public Page<ProductDTO> findProductsByBrand(Brand brand, int page) {
		if (brand == null)
			throw new NullPointerException();
		if (page < 1)
			page = 1;
		return productRepository.findProductsByBrand(brand, PageRequest.of(page - 1, 8));
	}

	@Override
	public Page<ProductDTO> findProductsByBrandId(Integer id, int page) {
		if (id == null)
			throw new NullPointerException("Brand Id is null!");
		if (page < 1)
			page = 1;
		return productRepository.findProductsByBrandId(id, PageRequest.of(page - 1, 8));
	}

	@Override
	public ProductDTO findProductById(Integer id) {
		return productRepository.findProductById(id);
	}

	@Override
	public Page<ProductDTO> search(String keyword, int page) {
		if (keyword == null)
			throw new NullPointerException();

		if (page < 1)
			page = 1;

		keyword = "%" + keyword + "%";
		return productRepository.findProduct(keyword, PageRequest.of(page - 1, 8));
	}

	@Override
	public void incrementViewCount(Integer id) {
		productRepository.incrementViewCount(id);
	}

	@Override
	public long getTotalProduct() {
		return productRepository.count();
	}

	@Override
	public Page<ProductDTO> getPages(String search, int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return productRepository.getPages("%" + search + "%", PageRequest.of(page - 1, size));
	}

}
