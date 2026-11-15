package keyboard;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 17: Exercise keyboard input, modifier keys and key chords. */
public class KeyboardInteractionsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/key_presses");
    }

    @Test
    public void shouldReportPressedCharacter() {
        var input = driver.findElement(By.id("target"));
        input.sendKeys("S");
        Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("result"), "You entered: S")));
    }

    @Test
    public void shouldReportSpecialKey() {
        var input = driver.findElement(By.id("target"));
        new Actions(driver).click(input).sendKeys(Keys.ENTER).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("result"), "You entered: ENTER")));
    }

    @Test
    public void shouldSelectAndReplaceInputUsingKeyboardChord() {
        driver.get("https://the-internet.herokuapp.com/login");
        var username = driver.findElement(By.id("username"));
        username.sendKeys("temporary");
        new Actions(driver)
                .click(username)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .sendKeys("tomsmith")
                .perform();

        Assert.assertEquals(username.getAttribute("value"), "tomsmith");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}