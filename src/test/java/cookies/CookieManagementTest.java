package cookies;

import java.time.Duration;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 15: Create, inspect and delete browser cookies with Selenium. */
public class CookieManagementTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/");
    }

    @Test
    public void shouldAddReadAndDeleteCookie() {
        Cookie practiceCookie = new Cookie.Builder("sdet-session", "practice-123")
                .path("/")
                .isHttpOnly(true)
                .build();

        driver.manage().addCookie(practiceCookie);
        Cookie actual = driver.manage().getCookieNamed("sdet-session");
        Assert.assertNotNull(actual);
        Assert.assertEquals(actual.getValue(), "practice-123");
        Assert.assertEquals(actual.getPath(), "/");
        Assert.assertTrue(actual.isHttpOnly());

        driver.manage().deleteCookieNamed("sdet-session");
        Assert.assertNull(driver.manage().getCookieNamed("sdet-session"));
    }

    @Test
    public void shouldClearCookiesForCurrentDomain() {
        driver.manage().addCookie(new Cookie("first", "one"));
        driver.manage().addCookie(new Cookie("second", "two"));
        Assert.assertEquals(driver.manage().getCookies().size(), 2);

        driver.manage().deleteAllCookies();
        Assert.assertTrue(driver.manage().getCookies().isEmpty());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}