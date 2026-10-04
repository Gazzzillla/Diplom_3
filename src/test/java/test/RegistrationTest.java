package test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.Test;
import page.LoginPage;
import page.RegisterPage;

import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @Description("Проверка успешной регистрации пользователя")
    public void successfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        openRegistrationPage(registerPage);

        registerPage.register(
                "Владимир",
                "test" + System.currentTimeMillis() + "@mail.ru",
                "123456"
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