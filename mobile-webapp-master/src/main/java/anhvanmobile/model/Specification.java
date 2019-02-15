package anhvanmobile.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "productSpecs")
public class Specification implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SPECIFICATION_ID")
	private Integer id;

	@NotNull(message = "Không được để trống tên thông số kỹ thuật!")
	@Column(name = "SPECIFICATION_NAME", nullable = false, unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "specification", cascade = CascadeType.ALL)
	private List<ProductSpec> productSpecs;

}