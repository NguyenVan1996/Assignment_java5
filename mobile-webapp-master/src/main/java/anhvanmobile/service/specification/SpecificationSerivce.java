package anhvanmobile.service.specification;

import anhvanmobile.dto.SpecificationDTO;
import anhvanmobile.model.Specification;
import anhvanmobile.service.BaseService;
import anhvanmobile.service.DTOService;

public interface SpecificationSerivce extends BaseService<Specification, Integer>, DTOService<SpecificationDTO> {
	
	Specification findByName(String name);
	
}	
