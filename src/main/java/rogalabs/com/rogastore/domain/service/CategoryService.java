package rogalabs.com.rogastore.domain.service;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rogalabs.com.rogastore.domain.exception.AppNotFoundException;
import rogalabs.com.rogastore.domain.exception.CategoryNotFoundException;
import rogalabs.com.rogastore.domain.exception.EntityInUseException;

import rogalabs.com.rogastore.domain.model.Category;
import rogalabs.com.rogastore.domain.repository.CategoryRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class CategoryService {

	private static final String MSG_CATEGORY_IN_USE = "Code %d category cannot be removed as it is in use";

	@Autowired
	private CategoryRepository categoryRepository;

	public Category findCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
	}

	public Page<Category> findAllCategories(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Transactional
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Transactional
	public void deleteCategory(Long categoryId) {
		try {

			categoryRepository.deleteById(categoryId);
			categoryRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new AppNotFoundException(categoryId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, categoryId));
		}
	}

}