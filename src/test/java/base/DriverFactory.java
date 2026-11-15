package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/** Creates browser instances; extend this factory when adding Firefox/Edge support. */
public final class DriverFactory {
    private DriverFactory() { }

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase();
        if (!browser.equals("chrome")) {
            throw new IllegalArgumentException("Unsupported browser: " + browser
                    + ". Currently supported: chrome");
        }
        return new ChromeDriver();
    }
}