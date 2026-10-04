package test;

import io.qameta.allure.Description;
import org.junit.Before;
import org.junit.Test;
import page.MainPage;

import static org.junit.Assert.assertTrue;

public class ConstructorTest extends BaseTest {

    private MainPage mainPage;

    @Before
    public void openMainPage() {
        mainPage = new MainPage(driver);
        mainPage.open();
    }

    @Test
    @Description("Проверка перехода к разделу Булки")
    public void switchToBuns() {
        mainPage.clickSauces();
        mainPage.clickBuns();

        assertTrue(mainPage.isBunsTabActive());
    }

    @Test
    @Description("Проверка перехода к разделу Соусы")
    public void switchToSauces() {
        mainPage.clickSauces();

        assertTrue(mainPage.isSaucesTabActive());
    }

    @Test
    @Description("Проверка перехода к разделу Начинки")
    public void switchToFillings() {
        mainPage.clickFillings();

        assertTrue(mainPage.isFillingsTabActive());
    }
}