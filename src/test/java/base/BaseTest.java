package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** Thread-safe TestNG browser lifecycle: each executing test thread owns its driver. */
public abstract class BaseTest {
    private final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    public void launchBrowser() {
        WebDriver webDriver = DriverFactory.createDriver();
        driver.set(webDriver);
        webDriver.manage().window().maximize();
    }

    protected WebDriver getDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver == null) {
            throw new IllegalStateException("No WebDriver is bound to this test thread");
        }
        return webDriver;
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        WebDriver webDriver = driver.get();
        try {
            if (webDriver != null) webDriver.quit();
        } finally {
            driver.remove();
        }
    }
}