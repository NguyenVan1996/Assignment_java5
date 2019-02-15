package anhvanmobile.model;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product_spec")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "product", "productSpecDetails" })
@ToString(exclude = { "product", "productSpecDetails" })
public class ProductSpec implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PROD_SPEC_ID")
	private Integer id;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "PRODUCT_ID")
	private Product product;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "SPECIFICATION_ID")
	private Specification specification;

	@Valid
	@OneToMany(mappedBy = "productSpec", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductSpecDetail> productSpecDetails;

}