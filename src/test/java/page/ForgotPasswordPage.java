package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final WebDriver driver;

    private final By loginLink =
            By.xpath("//a[@href='/login' and text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://stellarburgers.education-services.ru/forgot-password");
    }

    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}