package shadowdom;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 16: Locate elements inside a Shadow DOM using Selenium 4. */
public class ShadowDomTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://the-internet.herokuapp.com/shadowdom");
    }

    @Test
    public void shouldReadTextInsideShadowRoot() {
        WebElement host = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("my-paragraph")));
        SearchContext shadowRoot = host.getShadowRoot();

        String paragraph = shadowRoot.findElement(By.cssSelector("p")).getText();
        Assert.assertTrue(paragraph.contains("Let's have some different text!"));
    }

    @Test
    public void shouldLocateSecondShadowParagraph() {
        WebElement host = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("my-paragraph")));
        SearchContext shadowRoot = host.getShadowRoot();

        Assert.assertTrue(shadowRoot.findElement(By.cssSelector("slot")).isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}