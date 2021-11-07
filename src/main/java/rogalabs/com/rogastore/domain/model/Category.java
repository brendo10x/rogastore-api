package rogalabs.com.rogastore.domain.model;
 
import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "tb_category")
public class Category implements Serializable{
 
	private static final long serialVersionUID = 1L;
	
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_category_id_seq")
	@SequenceGenerator(name = "tb_category_id_seq", sequenceName = "tb_category_id_seq", allocationSize = 1)
	@Id
	private Long id;
	
	@Column(nullable = false)
	private String name;
}
