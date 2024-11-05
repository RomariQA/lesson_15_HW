package tests;

import models.CreateAndUpdateUserRequestModel;
import models.CreateSuccessfulUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserCreateSpec.*;

@DisplayName("Создание пользователя")
public class UserCreateTests extends TestBase {

    @DisplayName("Успешное создание пользователя")
    @Test
    void successfulCreateUserTest(){

        CreateAndUpdateUserRequestModel request = new CreateAndUpdateUserRequestModel();
        request.setJob("leader");
        request.setName("morpheus");

        CreateSuccessfulUserResponseModel response = step("Отправляем запрос на успешное создание пользователя", () ->
        given(userCreateRequestSpec)
                .body(request)

                .when()
                    .post()

                .then()
                    .spec(userSuccessfulCreateResponseSpec)
                    .extract().as(CreateSuccessfulUserResponseModel.class));

        step("Пользоваетль успешно создан, имя и должнотсь соответствует вводу", () -> {
            assertEquals("morpheus", response.getName());
            assertEquals("leader", response.getJob());
        });
    }

    @DisplayName("Создание пользователя без обязательного поля")
    @Test
    void unsuccessfulCreateUserTest(){

        CreateAndUpdateUserRequestModel request = new CreateAndUpdateUserRequestModel();
        request.setJob("morpheus");

        CreateSuccessfulUserResponseModel response = step("Отправляем запрос без обязательного поля должности", () ->
        given(userCreateRequestSpec)
                .body(request)

                .when()
                    .post()

                .then()
                .spec(userUnSuccessfulCreateResponseSpec)
                .extract().as(CreateSuccessfulUserResponseModel.class));

        step("Пользователь не создан, ошибка соовтетствует докуемнтации", () ->
            assertEquals("Some error", response.getName()));
    }
}
