package anhvanmobile.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "orderLines", "imageFile" })
@ToString(exclude = { "orderLines", "imageFile" })
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_ID")
	private Integer id;

	@Length(min = 1, max = 255, message = "Tên phải nằm trong khoảng từ 1 đến 255 ký tự!")
	@Column(name = "PRODUCT_NAME", unique = true, length = 255)
	private String name;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "BRAND_ID")
	private Brand brand;

	@NotNull(message = "Vui lòng nhập vào giá sản phẩm")
	@Range(min = 0, max = 100000000, message = "Giá trong khoảng từ 0 - 100 triệu")
	@Column(name = "PRICE")
	private Integer price;

	@Length(max = 45, message = "Đơn vị tính phải nhỏ hơn 45 ký tự!")
	@Column(name = "PRODUCT_UNIT", length = 45)
	private String unit;

	@NotNull(message = "Vui lòng nhập vào số lượng sản phẩm")
	@Range(min = 0, max = 5000, message = "Số lượng trong khoảng từ 0 - 5000")
	@Column(name = "QUANTITY_IN_STOCK")
	private Integer qtyInStock;

	@Length(min = 0, max = 255, message = "Mô tả phải nhỏ hơn 256 ký tự")
	@Column(name = "SHORT_DESCRIPTION", length = 255)
	private String shortDescription;

	@Length(max = 255, message = "Đường dẫn quá dài!")
	@Column(name = "THUMBNAIL", length = 255)
	private String thumbnail;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "VIEW", insertable = false)
	private Integer view;

	@Length(min = 0, max = 255, message = "Bảo hành phải nhỏ hơn 46 ký tự")
	@Column(name = "WARRANTY", length = 45)
	private String warranty;

	@Column(name = "CREATED_TIME", insertable = false, updatable = false)
	private Date createdTime;

	@Valid
	@OneToMany(mappedBy = "product", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductSpec> productSpecs;

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<OrderLine> orderLines;

	@Transient
	private MultipartFile imageFile;

}