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

@Service
public class RegistrationCategoryService {

	private static final String MSG_CATEGORY_IN_USE = "Code %d category cannot be removed as it is in use";

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Transactional
	public void delete(Long categoryId) {
		try {

			categoryRepository.deleteById(categoryId);
			categoryRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new AppNotFoundException(categoryId);

		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CATEGORY_IN_USE, categoryId));
		}
	}

	@Transactional
	public Category seekOrFail(Long categoryId) {
		return categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
	}
}