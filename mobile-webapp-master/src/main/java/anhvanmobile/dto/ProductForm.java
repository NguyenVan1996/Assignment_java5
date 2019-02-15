package anhvanmobile.dto;

import anhvanmobile.model.Brand;
import anhvanmobile.model.ProductSpecDetail;
import anhvanmobile.model.Specification;
import lombok.Data;

@Data
public class ProductForm {

	private String id;
	private String name;
	private Brand brand;
	private Integer price;
	private Specification specification;
	private ProductSpecDetail specDetail;
	
}
