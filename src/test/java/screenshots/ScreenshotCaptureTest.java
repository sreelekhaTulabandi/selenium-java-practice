package screenshots;

import base.BaseTest;
import java.nio.file.Files;
import java.nio.file.Path;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ScreenshotUtils;

/** Topic 23: Capture a screenshot and verify the generated PNG artifact. */
public class ScreenshotCaptureTest extends BaseTest {

    @Test
    public void shouldCapturePageScreenshot() throws Exception {
        getDriver().get("https://the-internet.herokuapp.com/");
        Path screenshot = ScreenshotUtils.capture(getDriver(), "home-page");

        Assert.assertTrue(Files.exists(screenshot), "Screenshot file was not created");
        Assert.assertTrue(Files.size(screenshot) > 0, "Screenshot file is empty");
        Assert.assertTrue(screenshot.toString().endsWith(".png"));
    }
}