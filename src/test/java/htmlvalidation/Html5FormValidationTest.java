package htmlvalidation;

import base.BaseTest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Verifies native required/email validation without relying on custom UI messages. */
public class Html5FormValidationTest extends BaseTest {
    @Test
    public void browserReportsInvalidAndValidEmailFormStates() {
        String html = """
                <!doctype html>
                <html><body>
                  <form id="signup">
                    <label for="email">Email</label>
                    <input id="email" name="email" type="email" required>
                    <button type="submit">Continue</button>
                  </form>
                </body></html>
                """;
        String encoded = Base64.getEncoder().encodeToString(html.getBytes(StandardCharsets.UTF_8));
        getDriver().get("data:text/html;charset=utf-8;base64," + encoded);

        WebElement email = getDriver().findElement(By.id("email"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        Assert.assertFalse((Boolean) js.executeScript(
                "return document.getElementById('signup').checkValidity();"));
        Assert.assertFalse((Boolean) js.executeScript(
                "return arguments[0].checkValidity();", email));
        String requiredMessage = (String) js.executeScript(
                "return arguments[0].validationMessage;", email);
        Assert.assertFalse(requiredMessage.isBlank(),
                "The browser should explain why the required field is invalid");

        email.sendKeys("not-an-email");
        Assert.assertFalse((Boolean) js.executeScript(
                "return arguments[0].checkValidity();", email));
        Assert.assertFalse(((String) js.executeScript(
                "return arguments[0].validationMessage;", email)).isBlank());

        email.clear();
        email.sendKeys("qa@example.com");
        Assert.assertTrue((Boolean) js.executeScript(
                "return arguments[0].checkValidity();", email));
        Assert.assertTrue((Boolean) js.executeScript(
                "return document.getElementById('signup').checkValidity();"));
    }
}
