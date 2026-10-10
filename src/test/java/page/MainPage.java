package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton =
            By.xpath("//button[text()='Войти в аккаунт']");

    private final By personalAccountButton =
            By.xpath("//a[@href='/account']");

    private final By createOrderButton =
            By.xpath("//button[text()='Оформить заказ']");

    private final By bunsTab =
            By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Булки']]");

    private final By saucesTab =
            By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Соусы']]");

    private final By fillingsTab =
            By.xpath("//div[contains(@class,'tab_tab')][.//span[text()='Начинки']]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://stellarburgers.education-services.ru/");
    }

    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickPersonalAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    public boolean isCreateOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(createOrderButton))
                .isDisplayed();
    }

    public void clickBuns() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
        waitUntilTabIsActive(bunsTab);
    }

    public void clickSauces() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
        waitUntilTabIsActive(saucesTab);
    }

    public void clickFillings() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        waitUntilTabIsActive(fillingsTab);
    }

    public boolean isBunsTabActive() {
        return isTabActive(bunsTab);
    }

    public boolean isSaucesTabActive() {
        return isTabActive(saucesTab);
    }

    public boolean isFillingsTabActive() {
        return isTabActive(fillingsTab);
    }

    private boolean isTabActive(By tab) {
        String classAttribute = driver.findElement(tab).getAttribute("class");

        return classAttribute != null
                && classAttribute.contains("tab_tab_type_current");
    }

    private void waitUntilTabIsActive(By tab) {
        wait.until(driver -> isTabActive(tab));
    }
}