package com.automation.api.tests;

import com.automation.api.base.ApiConfig;
import com.automation.api.models.Post;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

/**
 * REST Assured BDD tests for the same simulated JSONPlaceholder scenarios in the Postman collection.
 */
public class JsonPlaceholderApiTest {

    @Test
    public void shouldGetAllPosts() {
        given()
                .spec(ApiConfig.requestSpecification())
        .when()
                .get("/posts")
        .then()
                .log().ifValidationFails()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200)
                .and()
                .body("$", hasSize(100))
                .and()
                .body("id", everyItem(not(nullValue())));
    }

    @Test
    public void shouldGetPostByPathParameter() {
        given()
                .spec(ApiConfig.requestSpecification())
                .pathParam("postId", 1)
        .when()
                .get("/posts/{postId}")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("userId", equalTo(1));
    }

    @Test
    public void shouldFilterPostsByQueryParameter() {
        Response response = given()
                .spec(ApiConfig.requestSpecification())
                .queryParam("userId", 1)
        .when()
                .get("/posts")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200)
                .and()
                .body("userId", everyItem(equalTo(1)))
                .extract()
                .response();

        String firstTitle = response.jsonPath().getString("[0].title");
        Assert.assertNotNull(firstTitle, "JSONPath should extract the first post title");
        Assert.assertFalse(firstTitle.isBlank(), "Extracted post title should not be blank");
    }

    @Test
    public void shouldCreateSimulatedPostFromJsonString() {
        String payload = """
                {
                  "title": "REST Assured JSON string post",
                  "body": "Created as a simulated JSONPlaceholder request.",
                  "userId": 1
                }
                """;

        given()
                .spec(ApiConfig.requestSpecification())
                .contentType("application/json")
                .body(payload)
        .when()
                .post("/posts")
        .then()
                .log().ifValidationFails()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(201)
                .and()
                .body("title", equalTo("REST Assured JSON string post"))
                .and()
                .body("userId", equalTo(1));
    }

    @Test
    public void shouldSerializeAndDeserializePostPojo() {
        Post requestPost = new Post(null, 1, "REST Assured POJO post",
                "Serialized from a Java object.");

        Response response = given()
                .spec(ApiConfig.requestSpecification())
                .contentType("application/json")
                .body(requestPost)
        .when()
                .post("/posts")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(201)
                .and()
                .body("title", equalTo(requestPost.getTitle()))
                .extract()
                .response();

        Post responsePost = response.as(Post.class);
        Assert.assertNotNull(responsePost.getId(), "Deserialized response should contain an id");
        Assert.assertEquals(responsePost.getTitle(), requestPost.getTitle());
        Assert.assertEquals(responsePost.getUserId(), requestPost.getUserId());
    }

    @Test
    public void shouldUpdateSimulatedPost() {
        Post update = new Post(1, 1, "Updated REST Assured post",
                "Updated as a simulated JSONPlaceholder request.");

        given()
                .spec(ApiConfig.requestSpecification())
                .pathParam("postId", 1)
                .contentType("application/json")
                .body(update)
        .when()
                .put("/posts/{postId}")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("title", equalTo(update.getTitle()));
    }

    @Test
    public void shouldDeleteSimulatedPost() {
        given()
                .spec(ApiConfig.requestSpecification())
                .pathParam("postId", 1)
        .when()
                .delete("/posts/{postId}")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200);
    }

    @Test
    public void shouldVerifyResponseHeaders() {
        given()
                .spec(ApiConfig.requestSpecification())
        .when()
                .get("/posts/1")
        .then()
                .spec(ApiConfig.jsonResponseSpecification())
                .statusCode(200)
                .and()
                .header("Content-Type", containsString("application/json"));
    }
}
