package rogalabs.com.rogastore;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
 
import io.restassured.http.ContentType;
import rogalabs.com.rogastore.domain.model.Category;
import rogalabs.com.rogastore.domain.repository.CategoryRepository;
import rogalabs.com.rogastore.util.ResourceUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CategoryIT { 
	
	@LocalServerPort
	private int port;
	
	private final String CATEGORIES_ENDPOINT = "/api/v1/categories";
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@Disabled
	public void mustReturnStatus200_WhenQueryingCategories() {
		
		given()
			.port(port)
			.accept(ContentType.JSON)
			.log().all()
		.when()  
			.get(CATEGORIES_ENDPOINT)
	    .then()
	    	.assertThat().statusCode(200)
	    	.log().all();

	}
 
	@Test
	public void mustReturnStatus201_WhenToRegisterCategory() {

		String jsonCorrectCategoryGym = ResourceUtils.getContentFromResource("/json/correct/gym-category.json");

		given()
			.port(port)
			.body(jsonCorrectCategoryGym)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.log().all()
		.when()
			.post(CATEGORIES_ENDPOINT)
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all();
	}
	
	@Test
	@Disabled
	public void shouldReturnCorrectResponseAndStatus_WhenQueryingExistingCategory() {
		
		Category category = createCategory("Category");
		
		given()
			.port(port)
			.pathParam("categoryId", category.getId())
			.accept(ContentType.JSON)
			.log().all()
		.when()
			.get(CATEGORIES_ENDPOINT + "/{categoryId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("name", equalTo(category.getName()))
			.log().all();
	}
	
	
	@Test
	@Disabled
	public void mustReturnStatus404_WhenQueryingCategoryNotExisting() {
		
		Long nonExistentCategoryId = 500L;
		
		given()
			.port(port)
			.pathParam("categoryId", nonExistentCategoryId)
			.accept(ContentType.JSON)
			.log().all()
		.when()
			.get(CATEGORIES_ENDPOINT + "/{categoryId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value())
			.log().all();
	}
	
	@Test
	@Disabled
	public void mustReturnStatus204_WhenToDeleteCategory() {
		
		Category category = createCategory("Gym");
		
		given()
			.port(port)
			.pathParam("categoriaId", category.getId())
			.accept(ContentType.JSON)
			.log().all()
		.when()  
			.delete(CATEGORIES_ENDPOINT + "/{categoriaId}")
	    .then()
	    	.assertThat().statusCode(204)
	    	.log().all();

	}
	
	private Category createCategory(String name) {
		return categoryRepository.save(Category.builder().name(name).build());
	}
	
}
