package domproperties;

import base.BaseTest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Contrasts an element's HTML content attribute with its live DOM property. */
public class DomAttributePropertyTest extends BaseTest {
    @Test
    public void distinguishInitialValueAttributeFromLiveValueProperty() {
        String html = """
                <!doctype html>
                <html><body>
                  <input id="sample" value="initial">
                </body></html>
                """;
        String encoded = Base64.getEncoder().encodeToString(html.getBytes(StandardCharsets.UTF_8));
        getDriver().get("data:text/html;charset=utf-8;base64," + encoded);

        WebElement input = getDriver().findElement(By.id("sample"));
        Assert.assertEquals(input.getDomAttribute("value"), "initial");
        Assert.assertEquals(input.getDomProperty("value"), "initial");

        input.sendKeys("-updated");
        Assert.assertEquals(input.getDomAttribute("value"), "initial",
                "The HTML content attribute should retain its initial value");
        Assert.assertEquals(input.getDomProperty("value"), "initial-updated",
                "The live DOM property should reflect user input");
    }
}
