package rogalabs.com.rogastore.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.model.Category;

@Repository
public interface AppRepository extends JpaRepository<App, Long>, JpaSpecificationExecutor<App>{
	
	App findTopByCategoryOrderByPriceAsc(Category category);	

}
