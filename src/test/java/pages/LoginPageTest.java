package pages;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

/** Tests express business behavior while LoginPage owns UI implementation details. */
public class LoginPageTest extends BaseTest {

    @Test
    public void shouldLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.enterUsername("tomsmith")
                .enterPassword("SuperSecretPassword!")
                .submit();

        Assert.assertTrue(driver.getCurrentUrl().endsWith("/secure"));
        Assert.assertTrue(loginPage.getFlashMessage().contains("You logged into a secure area!"));
    }

    @Test
    public void shouldShowErrorForInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver).open();
        loginPage.enterUsername("wrong-user")
                .enterPassword("wrong-password")
                .submit();

        Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid!"));
    }
}