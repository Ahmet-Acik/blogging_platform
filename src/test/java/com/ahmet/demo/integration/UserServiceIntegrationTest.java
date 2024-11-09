package com.ahmet.demo.integration;

import com.ahmet.demo.model.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceIntegrationTest.class);
    private static final String BASE_URL = "/api/users";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";

    @LocalServerPort
    private int port;

    private Long createdUserId;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterEach
    public void tearDown() {
        if (createdUserId != null) {
            given()
                    .when()
                    .delete(BASE_URL + "/" + createdUserId)
                    .then()
                    .statusCode(HttpStatus.NO_CONTENT.value());
            createdUserId = null;
        }
    }

    private User createUser(String username, String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    @Test
    public void createTestUser() {
        User user = createUser("testuser", "testuser@example.com", "password", "Test", "User");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(USERNAME, equalTo("testuser"))
                .body(EMAIL, equalTo("testuser@example.com"))
                .extract().response();

        createdUserId = response.jsonPath().getLong("id");
    }

    @Test
    public void testGetUserById() {
        createTestUser();

        given()
                .when()
                .get(BASE_URL + "/" + createdUserId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(createdUserId.intValue()));
    }

    @Test
    public void testUpdateUser() {
        createTestUser();
        User user = createUser("updateduser", "updateduser@example.com", "newpassword", "Updated", "User");

        assertNotNull(user);
        assertNotNull(createdUserId);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/" + createdUserId)
                .then()
                .extract().response();

        logger.info("Response body: {}", response.getBody().asString());

        response.then()
                .statusCode(HttpStatus.OK.value())
                .body(USERNAME, equalTo("updateduser"))
                .body(EMAIL, equalTo("updateduser@example.com"));
    }

    @Test
    public void testDeleteUser() {
        createTestUser();

        given()
                .when()
                .delete(BASE_URL + "/" + createdUserId)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        createdUserId = null;
    }
}