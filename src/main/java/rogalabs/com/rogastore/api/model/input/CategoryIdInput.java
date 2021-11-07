package rogalabs.com.rogastore.api.model.input;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryIdInput {
	
	@ApiModelProperty(example = "1", required = true)
	@NotNull
	private Long id;
}
