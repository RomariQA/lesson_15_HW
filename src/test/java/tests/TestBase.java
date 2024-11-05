package tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


import static helpers.CustomAllureListener.withCustomTemplates;

public class TestBase {

    @BeforeAll
    static void beforeAll(){
        RestAssured.baseURI = "https://reqres.in/api";
        RestAssured.filters(withCustomTemplates());
    }
}
