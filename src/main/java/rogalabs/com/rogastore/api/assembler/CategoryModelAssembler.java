package rogalabs.com.rogastore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import rogalabs.com.rogastore.api.model.CategoryModel;
import rogalabs.com.rogastore.domain.model.Category;

@Component
public class CategoryModelAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public CategoryModel toModel(Category category) {
		return modelMapper.map(category, CategoryModel.class);

	}

	public List<CategoryModel> toCollectionModel(List<Category> categories) {
		return categories.stream().map(category -> toModel(category)).collect(Collectors.toList());
	}

	public Page<CategoryModel> toCollectionModelPaged(Page<Category> categoriesPage) {

		List<CategoryModel> categoriesModelPaged = toCollectionModel(categoriesPage.getContent());

		Page<CategoryModel> categoryModelPage = new PageImpl<>(categoriesModelPaged, categoriesPage.getPageable(),
				categoriesPage.getTotalElements());

		return categoryModelPage;
	}
}
