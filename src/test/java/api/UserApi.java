package api;

import io.restassured.response.Response;
import user.User;

import java.util.HashMap;
import java.util.Map;

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

    public Response loginUser(String email, String password) {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(credentials)
                .when()
                .post("/api/auth/login");
    }

    public void deleteUser(String accessToken) {
        given()
                .baseUri(BASE_URL)
                .header("Authorization", accessToken)
                .when()
                .delete("/api/auth/user");
    }
}
