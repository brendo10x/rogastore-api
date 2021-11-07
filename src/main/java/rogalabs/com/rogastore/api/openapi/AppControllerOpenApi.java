package rogalabs.com.rogastore.api.openapi;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import rogalabs.com.rogastore.api.exceptionhandler.Problem;
import rogalabs.com.rogastore.api.model.AppModel;
import rogalabs.com.rogastore.api.model.input.AppInput;
import rogalabs.com.rogastore.domain.filter.AppFilter;

@Api(tags = "Apps")
public interface AppControllerOpenApi {

	@ApiOperation("Search the apps")
	Page<AppModel> findAllApps(AppFilter appFilter, Pageable pageable);

	@ApiOperation("Save a app")
	@ApiResponses({ @ApiResponse(code = 201, message = "Registered app"), })
	AppModel saveApp(
			@ApiParam(name = "body", value = "Representation of a new app", required = true) AppInput appInput);

	@ApiOperation("Search a app by ID")
	@ApiResponses({ @ApiResponse(code = 400, message = "Invalid app id", response = Problem.class),
			@ApiResponse(code = 404, message = "App not found", response = Problem.class) })
	AppModel findAppById(@ApiParam(value = "App ID", example = "1", required = true) Long cozinhaId);

	@ApiOperation("Update a app by ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "Updated app"),
			@ApiResponse(code = 404, message = "App not found", response = Problem.class) })
	AppModel updateApp(@ApiParam(value = "App ID", example = "1", required = true) Long appId,

			@ApiParam(name = "body", value = "Representation of a app with new data", required = true) AppInput appInput);

	@ApiOperation("Delete a app by ID")
	@ApiResponses({ @ApiResponse(code = 204, message = "Deleted App"),
			@ApiResponse(code = 404, message = "App not found", response = Problem.class) })
	void deleteApp(@ApiParam(value = "App ID", example = "1", required = true) Long appId);

	@ApiOperation("Cheapest apps by category")
	AppModel cheapestAppByCategory(Long categoryId);
}
