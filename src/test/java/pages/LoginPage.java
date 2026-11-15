package pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/** Page Object: keeps login-page locators and user actions out of test assertions. */
public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginButton = By.cssSelector("button.radius");
    private final By flashMessage = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        return this;
    }

    public LoginPage enterUsername(String value) {
        driver.findElement(username).clear();
        driver.findElement(username).sendKeys(value);
        return this;
    }

    public LoginPage enterPassword(String value) {
        driver.findElement(password).clear();
        driver.findElement(password).sendKeys(value);
        return this;
    }

    public void submit() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getFlashMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(flashMessage)).getText();
    }
}