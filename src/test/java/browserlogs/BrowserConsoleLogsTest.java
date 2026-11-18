package browserlogs;

import base.BaseTest;
import java.util.logging.Level;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Topic 24: Read JavaScript console output through Selenium's browser log interface. */
public class BrowserConsoleLogsTest extends BaseTest {

    @Test
    public void shouldCaptureConsoleLogFromPageScript() {
        String marker = "selenium-practice-console-entry";
        getDriver().get("data:text/html,<html><body><script>console.log('" + marker + "');</script></body></html>");

        boolean markerFound = getDriver().manage().logs().get(LogType.BROWSER).getAll().stream()
                .map(LogEntry::getMessage)
                .anyMatch(message -> message.contains(marker));

        Assert.assertTrue(markerFound, "Expected JavaScript console message was not captured");
    }

    @Test
    public void shouldExposeBrowserLogEntries() {
        getDriver().get("https://the-internet.herokuapp.com/");
        Assert.assertNotNull(getDriver().manage().logs().get(LogType.BROWSER));
    }
}