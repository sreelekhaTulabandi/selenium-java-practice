package base;

import java.util.logging.Level;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LoggingPreferences;

/** Creates configured Chrome sessions for local and headless CI execution. */
public final class DriverFactory {
    private DriverFactory() { }

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase();
        if (!browser.equals("chrome")) {
            throw new IllegalArgumentException("Unsupported browser: " + browser
                    + ". Currently supported: chrome");
        }

        ChromeOptions options = new ChromeOptions();
        String pageLoadStrategy = System.getProperty("pageLoadStrategy", "normal")
                .trim().toUpperCase();
        try {
            options.setPageLoadStrategy(PageLoadStrategy.valueOf(pageLoadStrategy));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unsupported pageLoadStrategy: " + pageLoadStrategy
                    + ". Supported values: normal, eager, none", exception);
        }
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new", "--window-size=1440,900");
        }
        if (Boolean.parseBoolean(System.getProperty("incognito", "false"))) {
            options.addArguments("--incognito");
        }
        if (Boolean.parseBoolean(System.getProperty("disableNotifications", "true"))) {
            options.addArguments("--disable-notifications");
        }

        LoggingPreferences logging = new LoggingPreferences();
        logging.enable("browser", Level.ALL);
        options.setCapability("goog:loggingPrefs", logging);
        return new ChromeDriver(options);
    }
}