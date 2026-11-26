package elementscreenshot;

import base.BaseTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Captures an individual WebElement rather than the complete browser viewport. */
public class ElementScreenshotTest extends BaseTest {
    @Test
    public void captureAndValidateElementScreenshot() throws IOException {
        getDriver().get("https://the-internet.herokuapp.com/login");
        WebElement loginForm = getDriver().findElement(By.id("login"));

        byte[] screenshot = loginForm.getScreenshotAs(OutputType.BYTES);
        Path outputDirectory = Path.of("target", "element-screenshots");
        Files.createDirectories(outputDirectory);
        Path outputFile = outputDirectory.resolve("login-form.png");
        Files.write(outputFile, screenshot);

        Assert.assertTrue(Files.exists(outputFile), "Element screenshot should be written");
        Assert.assertTrue(Files.size(outputFile) > 0, "Element screenshot should not be empty");
        Assert.assertEquals(screenshot[0] & 0xFF, 0x89, "Expected PNG signature");
        Assert.assertEquals(screenshot[1] & 0xFF, 0x50, "Expected PNG signature");
    }
}
