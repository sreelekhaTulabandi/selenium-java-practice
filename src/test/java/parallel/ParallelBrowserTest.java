package parallel;

import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Topic 20: Independent browser sessions suitable for TestNG method parallelism. */
public class ParallelBrowserTest extends BaseTest {

    @Test
    public void shouldOpenHomePageInIsolatedBrowserSession() {
        getDriver().get("https://the-internet.herokuapp.com/");
        Assert.assertTrue(getDriver().getTitle().contains("The Internet"));
    }

    @Test
    public void shouldOpenLoginPageInIsolatedBrowserSession() {
        getDriver().get("https://the-internet.herokuapp.com/login");
        Assert.assertTrue(getDriver().findElement(By.id("username")).isDisplayed());
        Assert.assertTrue(getDriver().findElement(By.id("password")).isDisplayed());
    }

    @Test
    public void shouldOpenCheckboxPageInIsolatedBrowserSession() {
        getDriver().get("https://the-internet.herokuapp.com/checkboxes");
        Assert.assertEquals(getDriver().findElements(By.cssSelector("#checkboxes input")).size(), 2);
    }
}