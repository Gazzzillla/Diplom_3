
package test;

import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    private final UserApi userApi = new UserApi();

    private String registeredEmail;
    private String registeredPassword;

    @Test
    @Description("Проверка успешной регистрации пользователя")
    public void successfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String email = "test" + System.currentTimeMillis() + "@mail.ru";
        String password = "123456";

        openRegistrationPage(registerPage);

        registeredEmail = email;
        registeredPassword = password;

        registerPage.register(
                "Владимир",
                email,
                password
        );

        checkLoginPageOpened(loginPage);
    }

    @Test
    @Description("Проверка появления ошибки при регистрации с паролем короче шести символов")
    public void registrationWithShortPasswordShowsError() {
        RegisterPage registerPage = new RegisterPage(driver);

        openRegistrationPage(registerPage);

        registerPage.register(
                "Владимир",
                "test" + System.currentTimeMillis() + "@mail.ru",
                "12345"
        );

        checkPasswordError(registerPage);
    }

    @After
    @Step("Удалить зарегистрированного пользователя")
    public void deleteRegisteredUser() {
        if (registeredEmail == null) {
            return;
        }

        Response response = userApi.loginUser(
                registeredEmail,
                registeredPassword
        );

        if (response.statusCode() == 200) {
            String accessToken = response.jsonPath()
                    .getString("accessToken");

            if (accessToken != null && !accessToken.isEmpty()) {
                userApi.deleteUser(accessToken);
            }
        }
    }

    @Step("Открыть страницу регистрации")
    public void openRegistrationPage(RegisterPage registerPage) {
        registerPage.open();
    }

    @Step("Проверить, что открылась страница входа")
    public void checkLoginPageOpened(LoginPage loginPage) {
        assertTrue(loginPage.isLoginButtonDisplayed());
    }

    @Step("Проверить появление ошибки некорректного пароля")
    public void checkPasswordError(RegisterPage registerPage) {
        assertTrue(registerPage.isPasswordErrorDisplayed());
    }
}