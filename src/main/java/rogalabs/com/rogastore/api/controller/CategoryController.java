package rogalabs.com.rogastore.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import rogalabs.com.rogastore.api.assembler.CategoryInputDisassembler;
import rogalabs.com.rogastore.api.assembler.CategoryModelAssembler;
import rogalabs.com.rogastore.api.model.CategoryModel;
import rogalabs.com.rogastore.api.model.input.CategoryInput;
import rogalabs.com.rogastore.api.openapi.CategoryControllerOpenApi;
import rogalabs.com.rogastore.domain.model.Category;
import rogalabs.com.rogastore.domain.service.CategoryService;
import org.springframework.http.HttpStatus;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/categories")
public class CategoryController implements CategoryControllerOpenApi {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryModelAssembler categoryModelAssembler;

	@Autowired
	private CategoryInputDisassembler categoryInputDisassembler;

	@Override
	@GetMapping
	public Page<CategoryModel> findAllCategories(@PageableDefault(size = 10) Pageable pageable) {
		
		log.info("Request for find categories");
		
		Page<Category> categoriesPaged = categoryService.findAllCategories(pageable);

		Page<CategoryModel> categoriesModelPaged = categoryModelAssembler.toCollectionModelPaged(categoriesPaged);
		
		log.info("Returning result");
		
		return categoriesModelPaged;
	}

	@Override
	@GetMapping("/{categoryId}")
	public CategoryModel findCategoryById(@PathVariable Long categoryId) {
		
		log.info("Request for find category with id [{}]", categoryId);
		
		Category category = categoryService.findCategoryById(categoryId);
		
		log.info("Returning result");

		return categoryModelAssembler.toModel(category);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoryModel saveCategory(@RequestBody @Valid CategoryInput categoryInput) {
		
		log.info("Request for create category: [{}]", categoryInput);
		
		Category category = categoryInputDisassembler.toDomainObject(categoryInput);

		category = categoryService.saveCategory(category);
		
		log.info("Created category with id = " + category.getId());
		
		return categoryModelAssembler.toModel(category);
	}

	@Override
	@PutMapping("/{categoryId}")
	public CategoryModel updateCategory(@PathVariable Long categoryId,
			@RequestBody @Valid CategoryInput categoryInput) {

		log.info("Request for update category: [{}]", categoryInput);
		
		Category category = categoryService.findCategoryById(categoryId);

		categoryInputDisassembler.copyToDomainObject(categoryInput, category);

		category = categoryService.saveCategory(category);
		
		log.info("Updated app");
		
		return categoryModelAssembler.toModel(category);
	}

	@Override
	@DeleteMapping("/{categoryId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable Long categoryId) {
		
		log.info("Request for delete category with id [{}]", categoryId);
		
		categoryService.deleteCategory(categoryId);
		
		log.info("App deleted");
	}

}
