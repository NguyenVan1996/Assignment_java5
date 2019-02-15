package anhvanmobile.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anhvanmobile.dto.BrandDTO;
import anhvanmobile.exception.DuplicateBrandNameException;
import anhvanmobile.model.Brand;
import anhvanmobile.repository.BrandRespository;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRespository brandRepository;

	@Override
	public Brand create(Brand b) {
		Brand brand = brandRepository.findByName(b.getName());
		if (brand != null)
			throw new DuplicateBrandNameException();
		return brandRepository.save(b);
	}

	@Override
	public Brand update(Brand b) {
		Brand brand = brandRepository.findByName(b.getName());
		if (brand != null)
			if (!brand.getId().equals(b.getId()))
				throw new DuplicateBrandNameException();
		return brandRepository.save(b);
	}

	@Override
	public boolean remove(Brand b) {
		if (b == null)
			throw new NullPointerException();
		try {
			brandRepository.delete(b);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Brand findById(Integer id) {
		return brandRepository.getOne(id);
	}

	@Override
	public Brand findBrandByName(String name) {
		return brandRepository.findByName(name);
	}

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

	@Override
	public Page<BrandDTO> getPages(int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return brandRepository.getPages(PageRequest.of(page - 1, size));
	}

	@Override
	public long getTotalBrand() {
		return brandRepository.count();
	}

	@Override
	public Page<BrandDTO> getPages(String search, int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return brandRepository.getPages("%" + search + "%", PageRequest.of(page - 1, size));
	}

}
