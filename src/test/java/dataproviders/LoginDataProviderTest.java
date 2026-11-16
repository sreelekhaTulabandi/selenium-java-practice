package dataproviders;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

/** Topic 19: Reuse one test flow with multiple input/expected-result combinations. */
public class LoginDataProviderTest extends BaseTest {

    @DataProvider(name = "loginScenarios")
    public Object[][] loginScenarios() {
        return new Object[][] {
                {"tomsmith", "SuperSecretPassword!", "You logged into a secure area!"},
                {"wrong-user", "wrong-password", "Your username is invalid!"},
                {"tomsmith", "wrong-password", "Your password is invalid!"}
        };
    }

    @Test(dataProvider = "loginScenarios")
    public void shouldDisplayExpectedLoginFeedback(
            String username, String password, String expectedMessage) {
        LoginPage loginPage = new LoginPage(getDriver()).open();
        loginPage.enterUsername(username)
                .enterPassword(password)
                .submit();

        Assert.assertTrue(loginPage.getFlashMessage().contains(expectedMessage),
                "Unexpected login feedback for username: " + username);
    }
}