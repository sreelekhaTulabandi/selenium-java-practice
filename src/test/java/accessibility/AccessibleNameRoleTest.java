package accessibility;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Uses Selenium's accessibility-facing WebElement metadata methods. */
public class AccessibleNameRoleTest extends BaseTest {
    @Test
    public void inspectAccessibleNameAndRoleOfLoginControls() {
        getDriver().get("https://the-internet.herokuapp.com/login");

        WebElement username = getDriver().findElement(By.id("username"));
        WebElement loginButton = getDriver().findElement(By.cssSelector("button[type='submit']"));

        Assert.assertEquals(username.getAccessibleName(), "Username");
        Assert.assertEquals(username.getAriaRole(), "textbox");
        Assert.assertEquals(loginButton.getAccessibleName(), "Login");
        Assert.assertEquals(loginButton.getAriaRole(), "button");
    }
}
