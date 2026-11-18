package viewports;

import base.BaseTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Changes the browser viewport and checks the page's effective CSS width. */
public class ResponsiveViewportTest extends BaseTest {

    @Test
    public void pageCanBeCheckedAtMobileAndDesktopWidths() {
        getDriver().get("https://the-internet.herokuapp.com/");

        getDriver().manage().window().setSize(new Dimension(390, 844));
        long mobileWidth = ((Number) ((JavascriptExecutor) getDriver())
                .executeScript("return window.innerWidth;")).longValue();
        Assert.assertTrue(mobileWidth > 0 && mobileWidth < 600,
                "Expected a narrow mobile-sized CSS viewport.");

        getDriver().manage().window().setSize(new Dimension(1280, 900));
        long desktopWidth = ((Number) ((JavascriptExecutor) getDriver())
                .executeScript("return window.innerWidth;")).longValue();
        Assert.assertTrue(desktopWidth > 900,
                "Expected a desktop-sized CSS viewport.");
        Assert.assertTrue(desktopWidth > mobileWidth,
                "The desktop viewport should be wider than the mobile viewport.");
    }
}
