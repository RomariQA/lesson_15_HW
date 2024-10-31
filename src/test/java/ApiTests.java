import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class ApiTests {

    @Test
    void singleUserInfoTest(){
        given()
                .log().uri()
                .get("https://reqres.in/api/users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.id", is(2))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void singleUserNotFoundTest(){
        given()
                .log().uri()
                .get("https://reqres.in/api/users/22")
        .then()
                .log().status()
                .log().body()
                .statusCode(404);
    }

    @Test
    void listResourceTest(){
        given()
                .log().uri()
                .get("https://reqres.in/api/unknown")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data", hasSize(6))
                .body("per_page", equalTo(6))
                .body("page", equalTo(1))
                .body("data[0].id", is(notNullValue()))
                .body("data[0].name", is(notNullValue()))
                .body("data[0].year", is(notNullValue()))
                .body("data[0].color", is(notNullValue()))
                .body("data[0].pantone_value", is(notNullValue()));
    }

    @Test
    void successfulCreateUserTest(){
        String userData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .log().body()
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void unsuccessfulCreateUserTest(){
        String userData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .log().body()
        .when()
                .post("https://reqres.in/api/users")
        .then()
                .log().status()
                .log().body()
                .statusCode(400);
    }

    @Test
    void successfulUpdateUserTest(){
        String userData = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";
        given()
                .body(userData)
                .contentType(JSON)
                .log().uri()
                .log().body()
        .when()
                .put("https://reqres.in/api/users/2")
        .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("zion resident"));
    }
}
