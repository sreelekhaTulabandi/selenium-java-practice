package actions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 09: Perform advanced mouse interactions using Actions. */
public class ActionsClassTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void shouldRevealHoverCaption() {
        driver.get("https://the-internet.herokuapp.com/hovers");
        var firstFigure = driver.findElement(By.cssSelector(".figure:nth-of-type(1)"));
        new Actions(driver).moveToElement(firstFigure).perform();
        var caption = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".figure:nth-of-type(1) .figcaption")));
        Assert.assertTrue(caption.getText().contains("name: user1"));
    }

    @Test
    public void shouldOpenContextMenuWithRightClick() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        new Actions(driver).contextClick(driver.findElement(By.id("hot-spot"))).perform();
        Assert.assertTrue(wait.until(ExpectedConditions.alertIsPresent()).getText()
                .contains("You selected a context menu"));
        driver.switchTo().alert().accept();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}