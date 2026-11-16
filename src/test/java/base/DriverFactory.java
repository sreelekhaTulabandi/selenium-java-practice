package base;

import java.util.Locale;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.Capabilities;

/** Builds local browser sessions from Maven system properties. */
public final class DriverFactory {
    private DriverFactory() { }

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").trim().toLowerCase(Locale.ROOT);
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        boolean privateMode = Boolean.parseBoolean(System.getProperty("privateMode", "false"));
        Capabilities options = createOptions(browser, headless, privateMode);

        return switch (browser) {
            case "chrome" -> new ChromeDriver((ChromeOptions) options);
            case "firefox" -> new FirefoxDriver((FirefoxOptions) options);
            case "edge" -> new EdgeDriver((EdgeOptions) options);
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported: chrome, firefox, edge");
        };
    }

    static Capabilities createOptions(String browser, boolean headless, boolean privateMode) {
        return switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications", "--window-size=1440,900");
                if (headless) options.addArguments("--headless=new");
                if (privateMode) options.addArguments("--incognito");
                options.setAcceptInsecureCerts(Boolean.getBoolean("acceptInsecureCerts"));
                yield options;
            }
            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--width=1440", "--height=900");
                if (headless) options.addArguments("--headless");
                if (privateMode) options.addArguments("-private");
                options.setAcceptInsecureCerts(Boolean.getBoolean("acceptInsecureCerts"));
                yield options;
            }
            case "edge" -> {
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--disable-notifications", "--window-size=1440,900");
                if (headless) options.addArguments("--headless=new");
                if (privateMode) options.addArguments("--inprivate");
                options.setAcceptInsecureCerts(Boolean.getBoolean("acceptInsecureCerts"));
                yield options;
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser + ". Supported: chrome, firefox, edge");
        };
    }
}