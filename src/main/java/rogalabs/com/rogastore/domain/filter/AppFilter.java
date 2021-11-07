package rogalabs.com.rogastore.domain.filter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppFilter {

	@ApiModelProperty(example = "Pomodoro", value = "App name for search filter")
	private String name;
	
	@ApiModelProperty(example = "1", value = "Category ID for search filter")
	private Long categoryId;
}
