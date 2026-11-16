package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

/** Topic 18: Validate REST endpoints independently of the browser UI. */
public class PostsApiTest {
    private static final String BASE_URI = "https://jsonplaceholder.typicode.com";

    @Test
    public void shouldGetPostAndValidateResponse() {
        given()
                .baseUri(BASE_URI)
                .accept("application/json")
        .when()
                .get("/posts/{id}", 1)
        .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("id", equalTo(1))
                .body("userId", equalTo(1))
                .body("title", notNullValue());
    }

    @Test
    public void shouldCreatePostAndValidateReturnedPayload() {
        String requestBody = """
                {
                  "title": "Selenium API practice",
                  "body": "Created from a Rest Assured test",
                  "userId": 7
                }
                """;

        given()
                .baseUri(BASE_URI)
                .contentType("application/json")
                .body(requestBody)
        .when()
                .post("/posts")
        .then()
                .statusCode(201)
                .body("title", equalTo("Selenium API practice"))
                .body("body", equalTo("Created from a Rest Assured test"))
                .body("userId", equalTo(7))
                .body("id", notNullValue());
    }
}