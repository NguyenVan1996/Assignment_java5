package anhvanmobile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	private ProductDTO product;
	private int quantity;
	private int amount;
	
	public void calculateAmount() {
		amount = quantity * product.getPrice();
	}
	
}
