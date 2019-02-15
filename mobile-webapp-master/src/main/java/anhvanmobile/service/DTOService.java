package anhvanmobile.service;

import org.springframework.data.domain.Page;

public interface DTOService<DTO> {
	
	Page<DTO> getPages(int page, int size);
	
	Page<DTO> getPages(String search, int page, int size);
	
}
