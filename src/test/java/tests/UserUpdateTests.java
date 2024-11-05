package tests;

import models.CreateAndUpdateUserRequestModel;
import models.UpdateUserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.UserUpdateSpec.userUpdateRequestSpec;
import static specs.UserUpdateSpec.userUpdateResponseSpec;

@DisplayName("Редактирование пользователя")
public class UserUpdateTests extends TestBase {

    @DisplayName("Успешное редактирование пользователя")
    @Test
    void successfulUpdateUserTest(){

        CreateAndUpdateUserRequestModel request = new CreateAndUpdateUserRequestModel();
        request.setJob("zion resident");
        request.setName("morpheus");

        UpdateUserResponseModel response = step("Отправляем запрос на изменения пользователя", () ->
            given(userUpdateRequestSpec)
                .body(request)

                .when()
                    .put()

                .then()
                    .spec(userUpdateResponseSpec)
                    .extract().as(UpdateUserResponseModel.class));

        step("Изменения пользователя соответствуют вводу", () ->
        assertEquals("zion resident", response.getJob()));
    }
}
