package permissions;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Configures Chrome's notification permission and checks the page-visible permission state. */
public class NotificationPermissionTest {
    @Test
    public void notificationsCanBeBlockedThroughChromePreferences() {
        Map<String, Object> preferences = new HashMap<>();
        preferences.put("profile.default_content_setting_values.notifications", 2);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);
        options.addArguments("--headless=new", "--window-size=1440,900");

        ChromeDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://the-internet.herokuapp.com/");
            Object permission = driver.executeScript("return Notification.permission;");
            Assert.assertEquals(permission, "denied",
                    "Chrome notification permission should be blocked by the profile preference");
        } finally {
            driver.quit();
        }
    }
}
