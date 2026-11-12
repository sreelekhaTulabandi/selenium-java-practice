package dropdowns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Topic 05: Work with HTML select dropdowns using Selenium's Select class.
 * Practice page: https://the-internet.herokuapp.com/dropdown
 */
public class DropdownSelectionTest {

    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void shouldSelectOptionByVisibleText() {
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        dropdown.selectByVisibleText("Option 1");

        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 1");
    }

    @Test
    public void shouldSelectOptionByValueAndVerifySelection() {
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));

        dropdown.selectByValue("2");

        Assert.assertEquals(dropdown.getFirstSelectedOption().getText(), "Option 2");
        Assert.assertFalse(dropdown.isMultiple(), "This dropdown should be single-select");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
