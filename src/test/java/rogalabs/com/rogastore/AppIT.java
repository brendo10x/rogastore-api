package rogalabs.com.rogastore;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import io.restassured.http.ContentType;
import rogalabs.com.rogastore.domain.model.App;
import rogalabs.com.rogastore.domain.model.Category;
import rogalabs.com.rogastore.domain.service.AppService;
import rogalabs.com.rogastore.util.ResourceUtils;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class AppIT {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private AppService appService;
	
	private final String APPS_ENDPOINT = "/api/v1/apps";
	
	@Test
	public void mustReturnStatus200_WhenQueryingApps() {
 
		given()
			.port(port)
			.accept(ContentType.JSON)
			.log().all()
		.when()  
			.get(APPS_ENDPOINT)
	    .then()
	    	.assertThat().statusCode(200)
	    	.log().all();

	}
	
	@Test
	@Order(1)
	public void mustReturnStatus201_WhenToRegisterApp() {
		
		String jsonCorrectAppPomodoro = ResourceUtils.getContentFromResource("/json/correct/pomodoro-app.json");
		 
		given()
			.port(port)
			.body(jsonCorrectAppPomodoro)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.log().all()
		.when()
			.post(APPS_ENDPOINT)
		.then()
			.statusCode(HttpStatus.CREATED.value())
			.log().all();
	}
	
	@Test
	@Order(2)
	@Disabled
	public void mustReturnStatus204_WhenToDeleteCategory() {
		
		App app = createApp();
		
		given()
			.port(port)
			.pathParam("appId", app.getId())
			.accept(ContentType.JSON)
			.log().all()
		.when()  
			.delete(APPS_ENDPOINT + "/{appId}")
	    .then()
	    	.assertThat().statusCode(204)
	    	.log().all();

	}
	
	private App createApp() {
		App app = App.builder().name("Florest").price(new BigDecimal("50.00"))
				.category(Category.builder().id(1L).build())
				.build();
		
		return appService.saveApp(app);
	}
	
}
