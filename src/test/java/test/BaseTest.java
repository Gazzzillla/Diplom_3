package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    private static final String CHROME_DRIVER =
            "C:\\WebDriver\\chromedriver-win64\\chromedriver.exe";

    private static final String YANDEX_BROWSER =
            "C:\\Program Files\\Yandex\\YandexBrowser\\Application\\browser.exe";

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");

        ChromeOptions options = new ChromeOptions();

        if ("yandex".equals(browser)) {
            System.clearProperty("webdriver.chrome.driver");

            WebDriverManager.chromedriver()
                    .browserVersion("150")
                    .setup();

            options.setBinary(YANDEX_BROWSER);
        } else {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
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