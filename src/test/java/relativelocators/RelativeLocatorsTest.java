package relativelocators;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Demonstrates locating elements by their spatial relationship to another element. */
public class RelativeLocatorsTest extends BaseTest {
    @Test
    public void locateLoginControlsRelativeToLabelsAndButtons() {
        getDriver().get("https://the-internet.herokuapp.com/login");

        WebElement usernameLabel = getDriver().findElement(By.cssSelector("label[for='username']"));
        WebElement passwordLabel = getDriver().findElement(By.cssSelector("label[for='password']"));
        WebElement username = getDriver().findElement(
                RelativeLocator.with(By.tagName("input")).below(usernameLabel));
        WebElement password = getDriver().findElement(
                RelativeLocator.with(By.tagName("input")).below(passwordLabel));
        WebElement loginButton = getDriver().findElement(
                RelativeLocator.with(By.tagName("button")).below(password));

        Assert.assertEquals(username.getAttribute("id"), "username");
        Assert.assertEquals(password.getAttribute("id"), "password");
        Assert.assertEquals(loginButton.getAttribute("type"), "submit");
    }
}
