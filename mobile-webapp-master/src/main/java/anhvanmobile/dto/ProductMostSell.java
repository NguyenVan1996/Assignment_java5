package anhvanmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductMostSell {
	
	private Integer id;
	private String brand;
	private String name;
	private Integer price;
	private Integer qtyInStock;
	private String shortDescription;
	private String thumbnail;
	private Integer view;
	private String warranty;
	private Long quantity;
	
}
