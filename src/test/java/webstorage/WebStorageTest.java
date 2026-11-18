package webstorage;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Topic 25: Use JavaScript to inspect and manage origin-scoped Web Storage. */
public class WebStorageTest extends BaseTest {

    private JavascriptExecutor js() {
        return (JavascriptExecutor) getDriver();
    }

    @Test
    public void shouldPersistLocalStorageAcrossReload() {
        getDriver().get("https://the-internet.herokuapp.com/");
        js().executeScript("window.localStorage.setItem('practice-theme', 'dark')");

        getDriver().navigate().refresh();

        Object value = js().executeScript("return window.localStorage.getItem('practice-theme')");
        Assert.assertEquals(value, "dark");
        js().executeScript("window.localStorage.removeItem('practice-theme')");
    }

    @Test
    public void shouldReadAndClearSessionStorage() {
        getDriver().get("https://the-internet.herokuapp.com/");
        js().executeScript("window.sessionStorage.setItem('practice-session', 'active')");

        Object value = js().executeScript("return window.sessionStorage.getItem('practice-session')");
        Assert.assertEquals(value, "active");

        js().executeScript("window.sessionStorage.clear()");
        Assert.assertNull(js().executeScript("return window.sessionStorage.getItem('practice-session')"));
    }
}