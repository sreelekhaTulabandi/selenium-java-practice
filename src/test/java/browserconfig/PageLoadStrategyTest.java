package browserconfig;

import base.BaseTest;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Demonstrates configuring and inspecting Selenium's page-load strategy. */
public class PageLoadStrategyTest extends BaseTest {

    @Test
    public void chromeSessionUsesConfiguredPageLoadStrategy() {
        WebDriver driver = getDriver();
        String configured = System.getProperty("pageLoadStrategy", "normal")
                .trim().toUpperCase();

        Assert.assertEquals(configured, PageLoadStrategy.NORMAL.toString(),
                "The standard suite uses NORMAL; run this class separately with eager/none.");
        Assert.assertEquals(
                driver.manage().timeouts() != null, true,
                "The browser session should expose timeout management.");
        Assert.assertEquals(
                driver.getCapabilities().getCapability("pageLoadStrategy").toString().toLowerCase(),
                configured.toLowerCase());
    }
}
