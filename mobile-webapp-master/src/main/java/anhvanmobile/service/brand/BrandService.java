package anhvanmobile.service.brand;

import anhvanmobile.dto.BrandDTO;
import anhvanmobile.model.Brand;
import anhvanmobile.service.BaseService;
import anhvanmobile.service.DTOService;

public interface BrandService extends BaseService<Brand, Integer>, DTOService<BrandDTO> {
	
	public Brand findBrandByName(String name);

	public long getTotalBrand();
	
}
