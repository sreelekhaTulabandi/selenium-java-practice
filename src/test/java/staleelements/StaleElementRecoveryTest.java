package staleelements;

import base.BaseTest;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Shows why stale references must be discarded and elements located again. */
public class StaleElementRecoveryTest extends BaseTest {
    @Test
    public void reacquireElementAfterItsDomNodeIsReplaced() {
        getDriver().get("https://the-internet.herokuapp.com/dynamic_controls");
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        By checkboxLocator = By.cssSelector("#checkbox");

        WebElement originalCheckbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkboxLocator));
        getDriver().findElement(By.cssSelector("#checkbox-example button")).click();

        wait.until(ExpectedConditions.stalenessOf(originalCheckbox));
        getDriver().findElement(By.cssSelector("#checkbox-example button")).click();

        WebElement replacementCheckbox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(checkboxLocator));
        Assert.assertNotSame(replacementCheckbox, originalCheckbox);
        Assert.assertTrue(replacementCheckbox.isDisplayed());

        Assert.assertThrows(StaleElementReferenceException.class, originalCheckbox::isDisplayed);
    }
}
