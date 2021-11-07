package rogalabs.com.rogastore.api.model;
  

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
 
@Setter
@Getter
public class CategoryModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Productivity")
	private String name;
}
