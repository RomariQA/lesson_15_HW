package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.*;
import static io.restassured.http.ContentType.JSON;

public class LogSpec {

    public static RequestSpecification RequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .log().all();


    public static ResponseSpecification ResponseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();

    public static ResponseSpecification ResponseSpec201 = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(ALL)
            .build();

    public static ResponseSpecification ResponseSpec400 = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(ALL)
            .build();
    public static ResponseSpecification ResponseSpec404 = new ResponseSpecBuilder()
            .expectStatusCode(404)
            .log(ALL)
            .build();

}
