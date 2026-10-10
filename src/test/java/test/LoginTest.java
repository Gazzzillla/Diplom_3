package test;

import api.UserApi;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.ForgotPasswordPage;
import page.LoginPage;
import page.MainPage;
import page.RegisterPage;
import user.User;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    private User user;
    private UserApi userApi;
    private String accessToken;

    @Before
    public void createUser() {
        userApi = new UserApi();

        user = new User(
                "test" + System.currentTimeMillis() + "@mail.ru",
                "123456",
                "Владимир"
        );

        Response response = userApi.createUser(user);
        accessToken = response.jsonPath().getString("accessToken");
    }

    @After
    public void deleteUser() {
        if (accessToken != null) {
            userApi.deleteUser(accessToken);
        }
    }

    @Test
    @Description("Проверка входа по кнопке Войти в аккаунт на главной странице")
    public void loginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickLoginButton();

        login(loginPage);

        checkSuccessfulLogin(mainPage);
    }

    @Test
    @Description("Проверка входа через кнопку Личный кабинет")
    public void loginFromPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.open();
        mainPage.clickPersonalAccount();

        login(loginPage);

        checkSuccessfulLogin(mainPage);
    }

    @Test
    @Description("Проверка входа через ссылку Войти на странице регистрации")
    public void loginFromRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registerPage.open();
        registerPage.clickLoginLink();

        login(loginPage);

        checkSuccessfulLogin(mainPage);
    }

    @Test
    @Description("Проверка входа через ссылку Войти на странице восстановления пароля")
    public void loginFromForgotPasswordPage() {
        MainPage mainPage = new MainPage(driver);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        forgotPasswordPage.open();
        forgotPasswordPage.clickLoginLink();

        login(loginPage);

        checkSuccessfulLogin(mainPage);
    }

    @Step("Ввести данные пользователя и войти")
    public void login(LoginPage loginPage) {
        loginPage.login(
                user.getEmail(),
                user.getPassword()
        );
    }

    @Step("Проверить успешный вход")
    public void checkSuccessfulLogin(MainPage mainPage) {
        assertTrue(mainPage.isCreateOrderButtonDisplayed());
    }
}