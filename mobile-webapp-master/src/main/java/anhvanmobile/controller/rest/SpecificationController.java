package anhvanmobile.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import anhvanmobile.model.Specification;
import anhvanmobile.repository.SpecificationRepository;

@RestController("restSpecificationController")
@RequestMapping("/rest")
public class SpecificationController {

	@Autowired
	private SpecificationRepository repository;

	@GetMapping("/specifications")
	public @ResponseBody List<Specification> all() {
		return repository.findAll();
	}

	@PostMapping("/specification")
	public @ResponseBody Specification create(@RequestBody Specification specification) {
		return repository.save(specification);
	}

	@GetMapping("/specification/{id}")
	public @ResponseBody Specification specification(@PathVariable("id") Integer id) {
		return repository.getOne(id);
	}

	@PutMapping("/specification/{id}")
	public @ResponseBody Specification update(@RequestBody Specification specification) {
		return repository.save(specification);
	}

	@DeleteMapping("/specification/{id}")
	public @ResponseBody boolean delete(@PathVariable("id") Integer id) {
		repository.delete(repository.getOne(id));
		return true;
	}

}
