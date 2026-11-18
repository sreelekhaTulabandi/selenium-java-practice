package browseroptions;

import base.BaseTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Topic 22: Check browser capabilities and exercise headless/normal launch modes. */
public class BrowserOptionsTest extends BaseTest {

    @Test
    public void shouldLaunchRequestedChromeSession() {
        Capabilities capabilities = ((HasCapabilities) getDriver()).getCapabilities();

        Assert.assertEquals(capabilities.getBrowserName().toLowerCase(), "chrome");
        Assert.assertNotNull(getDriver().getWindowHandle());
        Assert.assertTrue(getDriver().manage().window().getSize().getWidth() > 0);
    }

    @Test
    public void shouldOpenPageWithConfiguredLaunchOptions() {
        getDriver().get("https://the-internet.herokuapp.com/");
        Assert.assertTrue(getDriver().getTitle().contains("The Internet"));
    }
}