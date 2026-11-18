package navigation;

import base.BaseTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Covers browser back/forward behavior and URL-based synchronization. */
public class BrowserNavigationHistoryTest extends BaseTest {

    @Test
    public void browserBackAndForwardRestorePreviousPages() {
        String home = "https://the-internet.herokuapp.com/";
        String login = home + "login";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

        getDriver().get(home);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        getDriver().get(login);
        wait.until(ExpectedConditions.urlToBe(login));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        getDriver().navigate().back();
        wait.until(ExpectedConditions.urlToBe(home));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));
        Assert.assertTrue(getDriver().getCurrentUrl().startsWith(home));

        getDriver().navigate().forward();
        wait.until(ExpectedConditions.urlToBe(login));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        Assert.assertEquals(getDriver().getCurrentUrl(), login);
    }
}
