package fluentwait;

import base.BaseTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Demonstrates FluentWait polling and a custom condition for delayed content. */
public class FluentWaitTest extends BaseTest {
    @Test
    public void waitForDynamicallyLoadedTextWithFluentWait() {
        getDriver().get("https://the-internet.herokuapp.com/dynamic_loading/1");
        getDriver().findElement(By.cssSelector("#start button")).click();

        FluentWait<org.openqa.selenium.WebDriver> wait = new FluentWait<>(getDriver())
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(NoSuchElementException.class);

        WebElement result = wait.until(driver -> {
            WebElement element = driver.findElement(By.id("finish"));
            return element.isDisplayed() ? element : null;
        });

        Assert.assertEquals(result.getText(), "Hello World!");
    }
}
