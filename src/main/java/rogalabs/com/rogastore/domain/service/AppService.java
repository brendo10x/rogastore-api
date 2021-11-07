package rogalabs.com.rogastore.domain.service;

import org.springframework.transaction.annotation.Transactional;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import rogalabs.com.rogastore.domain.exception.AppNotFoundException;

import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.model.Category;
import rogalabs.com.rogastore.domain.repository.AppRepository;

@Service
public class AppService {
 
	@Autowired
	private AppRepository appRepository;

	@Autowired
	private CategoryService categoryService;

	public App findAppById(Long appId) {
		return appRepository.findById(appId).orElseThrow(() -> new AppNotFoundException(appId));
	}

	public Page<App> findAllApps(Specification<App> appFilter, Pageable pageable) {
		return appRepository.findAll(appFilter, pageable);
	}


	public App saveApp(App app) {
		Category categoria = categoryService.findCategoryById(app.getCategory().getId());

		app.setCategory(categoria);

		return appRepository.save(app);
	}

	@Transactional
	public void deleteApp(Long appId) {
		try {

			appRepository.deleteById(appId);
			appRepository.flush();
			 
		} catch (EmptyResultDataAccessException e) {
			throw new AppNotFoundException(appId);
		}
	}

	public App cheapestAppByCategory(Long categoryId) {

		Category category = categoryService.findCategoryById(categoryId);

		return appRepository.findTopByCategoryOrderByPriceAsc(category);
	}

}
