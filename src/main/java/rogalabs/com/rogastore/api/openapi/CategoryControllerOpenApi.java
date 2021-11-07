package rogalabs.com.rogastore.api.openapi;

import rogalabs.com.rogastore.api.exceptionhandler.Problem;
import rogalabs.com.rogastore.api.model.CategoryModel;
import rogalabs.com.rogastore.api.model.input.CategoryInput;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Categories")
public interface CategoryControllerOpenApi {

	@ApiOperation("List the categories")
	Page<CategoryModel> findAllCategories(Pageable pageable);

	@ApiOperation("Search a category by ID")
	@ApiResponses({
		@ApiResponse(code = 400, message = "Invalid category id", response = Problem.class),
		@ApiResponse(code = 404, message = "Category not found", response = Problem.class)
	})
	CategoryModel findCategoryById(
			@ApiParam(value = "Category ID", example = "1", required = true)
			Long categoryId);
	
	@ApiOperation("Save a category")
	@ApiResponses({ @ApiResponse(code = 201, message = "Registered category"), })
	CategoryModel saveCategory (
			@ApiParam(name = "body", value = "Representation of a new category", required = true) CategoryInput categoryInput);
	 
	@ApiOperation("Update a category by ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Updated category"),
		@ApiResponse(code = 404, message = "Category not found", response = Problem.class)
	})
	CategoryModel updateCategory(
			@ApiParam(value = "Category ID", example = "1", required = true)
			Long categoryId,
			
			@ApiParam(name = "body", value = "Representation of a category with new data", 
				required = true)
			CategoryInput categoryInput);
	
	@ApiOperation("Delete a category by ID")
	@ApiResponses({
		@ApiResponse(code = 204, message = "Deleted Category"),
		@ApiResponse(code = 404, message = "Category not found", response = Problem.class)
	})
	void deleteCategory(
			@ApiParam(value = "Category ID", example = "1", required = true)
			Long categoryId);
}
