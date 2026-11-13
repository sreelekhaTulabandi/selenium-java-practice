package frames;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 07: Switch into frames and return to the top-level document. */
public class FramesTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void shouldSwitchIntoSingleFrameAndBack() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("mce_0_ifr")));
        var editor = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("tinymce")));
        editor.clear();
        editor.sendKeys("Frame automation practice");
        Assert.assertEquals(editor.getText(), "Frame automation practice");
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.tagName("h3")).getText().contains("Editor"));
    }

    @Test
    public void shouldSwitchToNestedFrame() {
        driver.get("https://the-internet.herokuapp.com/nested_frames");
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("frame-top")));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("frame-left")));
        Assert.assertEquals(driver.findElement(By.tagName("body")).getText().trim(), "LEFT");
        driver.switchTo().defaultContent();
        Assert.assertTrue(driver.findElement(By.tagName("frameset")).isDisplayed());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}