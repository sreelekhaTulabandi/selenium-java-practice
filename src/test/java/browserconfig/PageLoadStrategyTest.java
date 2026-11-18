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
        PageLoadStrategy expected = PageLoadStrategy.valueOf(
                System.getProperty("pageLoadStrategy", "normal").trim().toUpperCase());

        Assert.assertEquals(
                driver.getCapabilities().getCapability("pageLoadStrategy").toString().toLowerCase(),
                expected.toString().toLowerCase());
    }
}
