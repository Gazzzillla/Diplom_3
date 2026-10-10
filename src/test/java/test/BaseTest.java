package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    private static final String DEFAULT_YANDEX_BROWSER_PATH =
            "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(browser)) {
            WebDriverManager.chromedriver()
                    .browserVersion("150")
                    .setup();

            String yandexBrowserPath = System.getProperty(
                    "yandex.path",
                    DEFAULT_YANDEX_BROWSER_PATH
            );

            options.setBinary(yandexBrowserPath);
        } else {
            WebDriverManager.chromedriver().setup();
        }

        driver = new ChromeDriver(options);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}