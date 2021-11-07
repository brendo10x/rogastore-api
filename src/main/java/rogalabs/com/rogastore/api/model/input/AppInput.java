package rogalabs.com.rogastore.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppInput {

	@ApiModelProperty(example = "Pomodoro", required = true)
	@NotBlank
	private String name;
	 
	@ApiModelProperty(example = "Productivity app")
	private String description;
	
	@ApiModelProperty(example = "12.50")
	private BigDecimal price;
	
	@Valid
	@NotNull
	private CategoryIdInput category;
}
