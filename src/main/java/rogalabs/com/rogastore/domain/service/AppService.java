package rogalabs.com.rogastore.domain.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import rogalabs.com.rogastore.domain.exception.AppNotFoundException;
import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.repository.AppRepository;

@Service
public class RegistrationAppService {

	@Autowired
	private AppRepository appRepository;

	@Transactional
	public App save(App app) {
		return appRepository.save(app);
	}

	@Transactional
	public void delete(Long appId) {
		try {

			appRepository.deleteById(appId);
			appRepository.flush();

		} catch (EmptyResultDataAccessException e) {
			throw new AppNotFoundException(appId);
		}
	}

	@Transactional
	public App seekOrFail(Long appId) {
		return appRepository.findById(appId).orElseThrow(() -> new AppNotFoundException(appId));
	}

}
