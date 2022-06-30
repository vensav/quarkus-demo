package com.vensav.quarkus;

import com.vensav.quarkus.model.User;
import com.vensav.quarkus.repository.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;



@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserResourceTest {

    private User user;

    @Inject
    private UserRepository userRepository;

    @BeforeAll
    void init() {
        user = new User(1, "John", "Doe");
    }


    @Test
    @Order(1)
    public void testAddNewUser_Get201Response() {
        given()
                .body(user)
                .contentType(ContentType.JSON)
                .when().post("/users")
                .then()
                .statusCode(201)
                .body("firstName", is(user.getFirstName()))
                .body("lastName", is(user.getLastName()));
    }


    @Test
    @Order(2)
    public void testGetAllUsers_200Response() {
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("firstName", hasItem(user.getFirstName()))
                .body("lastName", hasItem(user.getLastName()));
    }

    
    
    @Test
    @Order(3)
    public void testUpdateUserById_202Response() {
        user.setLastName("Cena");
        given()
                .contentType(ContentType.JSON)
                .body(user)
                .when().put("/users/1")
                .then()
                .statusCode(202);

    }

    @Test
    @Order(4)
    public void testGetUserById_200Response() {
        given()
                .when().get("/users/1")
                .then()
                .statusCode(200)
                .body("firstName", is(user.getFirstName()))
                .body("lastName", is(user.getLastName()));
    }
    

    @Test
    @Order(5)
    public void testDeleteUserById_202Response() {
        given()
                .when().delete("/users/1")
                .then()
                .statusCode(204);
    }

}
