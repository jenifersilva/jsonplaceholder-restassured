package modules.user;

import DataFactory.ObjectDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.ObjectPojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("User REST API Tests")
public class UserTest {
    @BeforeEach
    public void beforeEach(){
        baseURI = "https://jsonplaceholder.typicode.com";
        basePath = "/users";
    }

    @Test
    @DisplayName("Verify if user is added")
    public void testAddUserSuccessfully() {
        given()
            .contentType(ContentType.JSON)
                .body(ObjectDataFactory.createNewObject("foo", "bar"))
        .when()
            .post()
        .then()
            .assertThat()
                .body("id", notNullValue())
                .statusCode(201);
    }

    @Test
    @DisplayName("Verify if user is updated")
    public void testUpdateUserSuccessfully() {
        int userId = 1;
        ObjectPojo objectUser = new ObjectPojo();
        objectUser.setTitle("foo2");

        given()
            .contentType(ContentType.JSON)
                .body(objectUser)
            .when()
                .put("/" + userId)
            .then()
                .assertThat()
                    .body("title", equalTo(objectUser.getTitle()))
                    .statusCode(200);
    }

    @Test
    @DisplayName("Verify if one user is returned")
    public void testReturnOneUserSuccessfully() {
        int userId = 1;

        given()
            .contentType(ContentType.JSON)
        .when()
                .get("/" + userId)
        .then()
            .assertThat()
                .body("name", equalTo("Leanne Graham"))
                .statusCode(200);
    }
}
