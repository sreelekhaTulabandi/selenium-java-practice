package tables;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/** Topic 10: Read table rows/cells and locate records by business data. */
public class WebTablesTest {
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void shouldReadHeadersAndFindRowByLastName() {
        List<WebElement> headers = driver.findElements(By.cssSelector("#table1 thead th"));
        Assert.assertEquals(headers.size(), 6);
        Assert.assertEquals(headers.get(0).getText(), "Last Name");

        List<WebElement> rows = driver.findElements(By.cssSelector("#table1 tbody tr"));
        WebElement matchingRow = rows.stream()
                .filter(row -> row.findElement(By.cssSelector("td:nth-child(1)"))
                        .getText().equals("Smith"))
                .findFirst().orElseThrow();

        Assert.assertEquals(matchingRow.findElement(By.cssSelector("td:nth-child(2)")).getText(), "John");
        Assert.assertEquals(matchingRow.findElement(By.cssSelector("td:nth-child(3)")).getText(),
                "jsmith@gmail.com");
    }

    @Test
    public void shouldIterateAllRowsAndValidateEmailFormat() {
        List<WebElement> rows = driver.findElements(By.cssSelector("#table1 tbody tr"));
        Assert.assertEquals(rows.size(), 4);

        for (WebElement row : rows) {
            String email = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            Assert.assertTrue(email.contains("@") && email.contains("."),
                    "Expected a valid-looking email in row: " + row.getText());
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}