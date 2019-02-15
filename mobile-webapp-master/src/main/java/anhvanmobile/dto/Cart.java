package anhvanmobile.dto;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cart extends LinkedHashMap<Integer, Item> {

	private static final long serialVersionUID = 1L;

	private int quantity;

	public void add(Integer key, ProductDTO p) {
		if (containsKey(key)) {
			Item item = this.get(key);
			item.setQuantity(item.getQuantity() + 1);
			item.calculateAmount();
			quantity++;
		} else {
			this.put(key, new Item(p, 1, p.getPrice()));
			quantity++;
		}
	}

	public boolean updateQuantity(Integer key, Integer quantity) {
		if (containsKey(key)) {
			Item item = this.get(key);
			int oldQty = item.getQuantity();
			item.setQuantity(quantity);
			item.calculateAmount();
			if (quantity > oldQty)
				this.quantity += quantity - oldQty;
			else if (quantity < oldQty)
				this.quantity -= oldQty - quantity;
		} else
			return false;
		return true;
	}

	public boolean remove(Integer key) {
		if (containsKey(key)) {
			int itemQty = this.get(key).getQuantity();
			super.remove(key);
			quantity -= itemQty;
		} else
			return false;
		return true;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAmount() {
		int amount = 0;
		for (Map.Entry<Integer, Item> item : this.entrySet()) {
			amount += item.getValue().getAmount();
		}
		return amount;
	}

}
