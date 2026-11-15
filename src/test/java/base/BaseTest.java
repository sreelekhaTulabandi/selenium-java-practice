package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/** Shared TestNG lifecycle for new framework-style page-object tests. */
public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void launchBrowser() {
        driver = DriverFactory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}