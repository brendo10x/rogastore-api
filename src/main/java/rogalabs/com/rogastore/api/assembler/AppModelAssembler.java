package rogalabs.com.rogastore.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import rogalabs.com.rogastore.api.model.AppModel;
import rogalabs.com.rogastore.domain.model.App;

@Component
public class AppModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public AppModel toModel(App app) {
		return modelMapper.map(app, AppModel.class);

	}

	public List<AppModel> toCollectionModel(List<App> apps) {
		return apps.stream().map(app -> toModel(app)).collect(Collectors.toList());
	}

	public Page<AppModel> toCollectionModelPaged(Page<App> appsPage) {

		List<AppModel> appsModelPaged = toCollectionModel(appsPage.getContent());

		Page<AppModel> categoryModelPage = new PageImpl<>(appsModelPaged, appsPage.getPageable(),
				appsPage.getTotalElements());

		return categoryModelPage;
	}
}
