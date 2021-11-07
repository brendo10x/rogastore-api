package rogalabs.com.rogastore.api.model;

import java.math.BigDecimal;
 

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppModel {

	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Pomodoro")
	private String name;
	
	@ApiModelProperty(example = "Productivity app")
	private String description;
	
	@ApiModelProperty(example = "12.50")
	private BigDecimal price;
	
	private CategoryModel category;
}
