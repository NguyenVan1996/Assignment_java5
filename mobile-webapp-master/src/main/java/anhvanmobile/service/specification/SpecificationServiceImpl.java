package anhvanmobile.service.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import anhvanmobile.dto.SpecificationDTO;
import anhvanmobile.exception.DuplicateSpecificationNameException;
import anhvanmobile.model.Specification;
import anhvanmobile.repository.SpecificationRepository;

@Service
public class SpecificationServiceImpl implements SpecificationSerivce {

	@Autowired
	private SpecificationRepository specificationRepository;

	@Override
	public Specification create(Specification s) {
		Specification specification = findByName(s.getName());
		if (specification != null)
			throw new DuplicateSpecificationNameException();

		return specificationRepository.save(s);
	}

	@Override
	public Specification update(Specification s) {
		Specification specification = findByName(s.getName());
		if (specification != null)
			if (!specification.getId().equals(s.getId()))
				throw new DuplicateSpecificationNameException();
		return specificationRepository.save(s);
	}

	@Override
	public boolean remove(Specification s) {
		try {
			specificationRepository.delete(s);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Specification findById(Integer id) {
		return specificationRepository.getOne(id);
	}

	@Override
	public List<Specification> findAll() {
		return specificationRepository.findAll();
	}

	@Override
	public Specification findByName(String name) {
		return specificationRepository.findByName(name);
	}

	@Override
	public Page<SpecificationDTO> getPages(int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return specificationRepository.getPages(PageRequest.of(page - 1, size));
	}

	@Override
	public Page<SpecificationDTO> getPages(String search, int page, int size) {
		if (page < 1)
			page = 1;
		if (size < 5)
			size = 5;
		return specificationRepository.getPages("%" + search + "%", PageRequest.of(page - 1, size));
	}

}
