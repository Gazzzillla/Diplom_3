package api;

import io.restassured.response.Response;
import user.User;

import static io.restassured.RestAssured.given;

public class UserApi {

    private static final String BASE_URL =
            "https://stellarburgers.education-services.ru";

    public Response createUser(User user) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    public void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}