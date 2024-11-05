package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;


public class UserUpdateSpec {
    public static RequestSpecification userUpdateRequestSpec = with()
            .contentType(JSON)
            .log().uri()
            .log().body()
            .log().headers()
            .basePath("/users/2");

    public static ResponseSpecification userUpdateResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
