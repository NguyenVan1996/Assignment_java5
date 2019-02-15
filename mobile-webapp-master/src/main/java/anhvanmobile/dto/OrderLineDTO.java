package anhvanmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO {
	
	private Integer productId;
	private String productThumbnail;
	private String productName;
	private Integer quantity;
	private Integer productPrice;
	
}
