package performance;

import base.BaseTest;
import java.time.Duration;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Reads the W3C Navigation Timing entry through the browser Performance API. */
public class NavigationTimingTest extends BaseTest {

    @Test
    @SuppressWarnings("unchecked")
    public void navigationTimingEntryContainsUsefulMetrics() {
        getDriver().get("https://the-internet.herokuapp.com/");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(driver -> ((Number) ((JavascriptExecutor) driver).executeScript(
                "return performance.getEntriesByType('navigation').length;")).intValue() > 0);

        Map<String, Object> timing = (Map<String, Object>) ((JavascriptExecutor) getDriver())
                .executeScript("""
                    const entry = performance.getEntriesByType('navigation')[0];
                    return {
                      type: entry.type,
                      duration: entry.duration,
                      domContentLoaded: entry.domContentLoadedEventEnd,
                      loadEvent: entry.loadEventEnd
                    };
                    """);

        Assert.assertNotNull(timing.get("type"));
        Assert.assertTrue(((Number) timing.get("duration")).doubleValue() >= 0);
        Assert.assertTrue(((Number) timing.get("domContentLoaded")).doubleValue() >= 0);
        Assert.assertTrue(((Number) timing.get("loadEvent")).doubleValue() >= 0);
    }
}
