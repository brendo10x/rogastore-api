package rogalabs.com.rogastore.api.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryInput {
	
	@ApiModelProperty(example = "Productivity", required = true)
	private String name;

}
