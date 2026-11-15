package pagination;

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

/** Topic 11: Navigate paginated DataTables and verify page state changes. */
public class PaginationTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://datatables.net/examples/basic_init/alt_pagination.html");
    }

    @Test
    public void shouldMoveToNextPageAndBack() {
        By info = By.id("example_info");
        By next = By.id("example_next");
        By previous = By.id("example_previous");

        String firstPageInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(info)).getText();
        Assert.assertTrue(firstPageInfo.contains("1 to 10"));

        wait.until(ExpectedConditions.elementToBeClickable(next)).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(info, "11 to 20"));
        String secondPageInfo = driver.findElement(info).getText();
        Assert.assertTrue(secondPageInfo.contains("11 to 20"));
        Assert.assertTrue(driver.findElement(previous).isEnabled());

        driver.findElement(previous).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(info, "1 to 10"));
        Assert.assertTrue(driver.findElement(info).getText().contains("1 to 10"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}