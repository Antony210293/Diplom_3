package steps;

import model.Data;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserSteps {

    public static RequestSpecification requestSpecification() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri(Data.BASE_API_URI);
    }

    @Step("Создание нового пользователя")
    public ValidatableResponse userCreate(UserCreateAccount userCreateAccount) {
        return requestSpecification()
                .body(userCreateAccount)
                .post(Data.AUTH_API_URI)
                .then();
    }
    @Step("Авторизация пользователя")
    public ValidatableResponse userLogin(UserLogin userLogin) {
        return requestSpecification()
                .body(userLogin)
                .post(Data.LOGIN_API_URI)
                .then();
    }
    @Step("Удаление пользователя без авторизации")
    public ValidatableResponse userDelete(String accessToken) {
        return requestSpecification()
                .header("Authorization", accessToken)
                .delete(Data.USER_API_URI)
                .then();
    }
    @Step("Удаление пользователя после авторизации")
    public ValidatableResponse userDeleteAfterLogin(UserLogin userLogin) {
        Response response = userLogin(userLogin)
                .extract().response();
        UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);
        String accessToken = userLoginResponse.getAccessToken();
        return userDelete(accessToken);
    }
}
