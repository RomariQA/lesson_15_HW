package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;

public class GetUserInfoSpec {

    public static RequestSpecification UserInfoSuccessfulRequestSpec = with()
            .log().uri()
            .log().headers()
            .basePath("/users/2");

    public static RequestSpecification UserInfoUnSuccessfulRequestSpec = with()
            .log().uri()
            .log().headers()
            .basePath("/users/22");



    public static ResponseSpecification getSuccessfulInfoResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification getUnSuccessfulInfoResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(STATUS)
            .log(BODY)
            .build();
}
