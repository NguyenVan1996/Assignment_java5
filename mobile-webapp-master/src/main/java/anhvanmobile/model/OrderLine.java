package anhvanmobile.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_line")
@Data
@NoArgsConstructor
public class OrderLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ODER_LINE_ID")
	private Integer id;

	private Integer quantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ORDER_ID")
	@JsonIgnore
	private Order order;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product; 

}