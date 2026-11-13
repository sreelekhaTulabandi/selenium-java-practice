package windows;

import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 08: Work with window handles and switch between tabs/windows. */
public class MultipleWindowsTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void shouldSwitchToNewWindowAndReturnToParent() {
        driver.get("https://the-internet.herokuapp.com/windows");
        String parent = driver.getWindowHandle();
        driver.findElement(By.linkText("Click Here")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> handles = driver.getWindowHandles();
        Assert.assertEquals(handles.size(), 2);
        String child = handles.stream().filter(handle -> !handle.equals(parent))
                .findFirst().orElseThrow();
        driver.switchTo().window(child);
        Assert.assertEquals(driver.findElement(By.tagName("h3")).getText(), "New Window");

        driver.close();
        driver.switchTo().window(parent);
        Assert.assertTrue(driver.getCurrentUrl().endsWith("/windows"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}