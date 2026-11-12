package basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Topic 01: Launching Chrome and navigating between pages.
 *
 * Practice site: https://the-internet.herokuapp.com/
 * Selenium 4.6+ includes Selenium Manager, which can resolve the driver
 * automatically in supported environments.
 */
public class BrowserLaunchNavigationTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void shouldLaunchBrowserAndOpenHomePage() {
        driver.get("https://the-internet.herokuapp.com/");

        String actualTitle = driver.getTitle();
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(actualTitle, "The Internet");
        Assert.assertTrue(actualUrl.contains("the-internet.herokuapp.com"));
    }

    @Test
    public void shouldNavigateToDropdownPage() {
        driver.get("https://the-internet.herokuapp.com/");

        driver.navigate().to("https://the-internet.herokuapp.com/dropdown");

        Assert.assertTrue(
                driver.getCurrentUrl().endsWith("/dropdown"),
                "Browser should navigate to the Dropdown page"
        );
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
