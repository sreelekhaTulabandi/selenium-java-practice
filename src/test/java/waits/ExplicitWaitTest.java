package waits;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Topic 03: Synchronize tests with explicit waits.
 * Practice page: https://the-internet.herokuapp.com/dynamic_loading/1
 */
public class ExplicitWaitTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void shouldWaitUntilHiddenElementBecomesVisible() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        driver.findElement(By.cssSelector("#start button")).click();

        WebElement finishMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assert.assertEquals(finishMessage.getText(), "Hello World!");
    }

    @Test
    public void shouldWaitUntilElementIsClickable() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("#start button"))
        );

        Assert.assertTrue(startButton.isEnabled());
        startButton.click();

        Assert.assertTrue(
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish"))).isDisplayed()
        );
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
