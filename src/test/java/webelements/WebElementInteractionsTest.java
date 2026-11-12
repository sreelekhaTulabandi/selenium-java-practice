package webelements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Topic 04: Read and interact with common WebElement properties.
 * Practice pages: login and checkboxes on The Internet.
 */
public class WebElementInteractionsTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void shouldReadTextAttributesAndElementState() {
        driver.get("https://the-internet.herokuapp.com/login");

        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button.radius"));

        Assert.assertTrue(username.isDisplayed());
        Assert.assertTrue(username.isEnabled());
        Assert.assertEquals(username.getAttribute("type"), "text");
        Assert.assertEquals(password.getAttribute("type"), "password");
        Assert.assertEquals(loginButton.getText(), "Login");

        username.sendKeys("tomsmith");
        Assert.assertEquals(username.getAttribute("value"), "tomsmith");
        username.clear();
        Assert.assertEquals(username.getAttribute("value"), "");
    }

    @Test
    public void shouldSelectAndDeselectCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement firstCheckbox = driver.findElement(By.cssSelector("#checkboxes input:nth-of-type(1)"));
        WebElement secondCheckbox = driver.findElement(By.cssSelector("#checkboxes input:nth-of-type(2)"));

        Assert.assertFalse(firstCheckbox.isSelected());
        Assert.assertTrue(secondCheckbox.isSelected());

        firstCheckbox.click();
        secondCheckbox.click();

        Assert.assertTrue(firstCheckbox.isSelected());
        Assert.assertFalse(secondCheckbox.isSelected());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
