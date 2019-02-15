package anhvanmobile.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderDTO {

	private Integer id;
	private String customerName;
	private String phoneNumber;
	private String address;
	private Date createdTime;
	private Integer amount;
	private String status;

	public OrderDTO() {
		super();
	}

	public OrderDTO(Integer id, String customerName, String phoneNumber, String address, Date createdTime,
			String status) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdTime = createdTime;
		this.status = status;
	}

	public OrderDTO(Integer id, String customerName, String phoneNumber, String address, Date createdTime,
			Integer amount, String status) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.createdTime = createdTime;
		this.amount = amount;
		this.status = status;
	}

}
