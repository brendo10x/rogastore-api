package rogalabs.com.rogastore.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rogalabs.com.rogastore.api.model.input.AppInput;
import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.model.Category;

@Component
public class AppInputDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	 
	public App toDomainObject(AppInput appInput) {
		return modelMapper.map(appInput, App.class);
	}

	public void copyToDomainObject(AppInput appInput, App app) {
		
		// To avoid org.hibernate.HibernateException: handle of an instance of
		// com.rogastore.domain.model.Category has been changed from 1 to 2
		
		app.setCategory(new Category());
		
		modelMapper.map(appInput, app);
		
	}
}
