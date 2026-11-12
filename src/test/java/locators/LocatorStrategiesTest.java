package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Topic 02: Find elements using Selenium locator strategies.
 * Practice page: https://the-internet.herokuapp.com/login
 */
public class LocatorStrategiesTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void shouldLocateLoginFormUsingIdNameCssAndXpath() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));
        WebElement heading = driver.findElement(By.xpath("//div[@class='example']/h2"));

        Assert.assertTrue(username.isDisplayed());
        Assert.assertEquals(password.getAttribute("type"), "password");
        Assert.assertEquals(loginButton.getText(), "Login");
        Assert.assertEquals(heading.getText(), "Login Page");
    }

    @Test
    public void shouldUseLinkTextToOpenLoginPage() {
        driver.get("https://the-internet.herokuapp.com/");

        driver.findElement(By.linkText("Form Authentication")).click();

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/login"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
