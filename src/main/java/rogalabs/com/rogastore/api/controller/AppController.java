package rogalabs.com.rogastore.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
import rogalabs.com.rogastore.api.assembler.AppInputDisassembler;
import rogalabs.com.rogastore.api.assembler.AppModelAssembler;
import rogalabs.com.rogastore.api.model.AppModel;
import rogalabs.com.rogastore.api.model.input.AppInput;
import rogalabs.com.rogastore.api.openapi.AppControllerOpenApi;
import rogalabs.com.rogastore.domain.filter.AppFilter;
import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.service.AppService;
import rogalabs.com.rogastore.infrastructure.repository.spec.AppSpecs;

@Slf4j
@RestController
@RequestMapping(path = "api/v1/apps")
public class AppController implements AppControllerOpenApi{
	
	@Autowired
	private AppService appService;
	
	@Autowired 
	private AppModelAssembler appModelAssembler;
	
	@Autowired 
	private AppInputDisassembler appInputDisassembler;
	
	@Override
	@GetMapping
	public Page<AppModel> findAllApps(AppFilter appFilter, Pageable pageable) {
 
		log.info("Request for find apps");
		
		Page<App> appsPaged = appService.findAllApps(AppSpecs.usingFilter(appFilter), pageable);

		Page<AppModel> appsModelPaged = appModelAssembler.toCollectionModelPaged(appsPaged);

		log.info("Returning result");
		
		return appsModelPaged;
	}
	
	@Override
	@GetMapping("/{appId}")
	public AppModel findAppById(@PathVariable Long appId) {

		log.info("Request for find app with id [{}]", appId);
		
		App app = appService.findAppById(appId);
		
		log.info("Returning result");
		
		return appModelAssembler.toModel(app);
	}
	
	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AppModel saveApp(@RequestBody @Valid AppInput appInput) {
		
		log.info("Request for create app: [{}]", appInput);
		
		App app = appInputDisassembler.toDomainObject(appInput);

		app = appService.saveApp(app);
		
		log.info("Created app with id = " + app.getId());
		
		return appModelAssembler.toModel(app);
	}
	
	@Override
	@PutMapping("/{appId}")
	public AppModel updateApp(@PathVariable Long appId,
			@RequestBody @Valid AppInput appInput) {
 
		log.info("Request for update app: [{}]", appInput);
		
		App app = appService.findAppById(appId);
		
		appInputDisassembler.copyToDomainObject(appInput, app);
		
		app = appService.saveApp(app);
		
		log.info("Updated app");
		
		return appModelAssembler.toModel(app);
	}
	
	@Override
	@DeleteMapping("/{appId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteApp(@PathVariable Long appId) {
		
		log.info("Request for delete app with id [{}]", appId);
		
		appService.deleteApp(appId);
		
		log.info("App deleted");
	}
	
	@Override
	@GetMapping("/cheapest-by-category/{categoryId}")
	public AppModel cheapestAppByCategory(@PathVariable Long categoryId){
		
		log.info("request to find the cheapest app by category: [{}]", categoryId);
		
		App app = appService.cheapestAppByCategory(categoryId); 
		
		log.info("Returning result");
		
		return appModelAssembler.toModel(app);
	}
	
}
