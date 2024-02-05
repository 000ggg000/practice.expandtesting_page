package lt.techin;

import lt.techin.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class RegisterPageTest extends BasePageTest {
    private static final Logger log = getLogger(lookup().lookupClass());

    @Test
    void register() {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountLogedPage accountLogedPage = new AccountLogedPage(driver);
        registerPage.clickToRegister();
        String email = TestUtils.getComplexRandomMail();
        String password = "John123";
        createAccount.insertEmail(email);
        createAccount.insertName("John");
        createAccount.insertPassword(password);
        createAccount.insertConfirmPassword(password);
        createAccount.clickRegister();

        assertEquals("User account created successfully", createAccount.registerAllert.getText(), "Account was not created");
        log.info("Account created with email {}", email);
        TestUtils.takeScreenshot(driver, "accountCreated");
        accountPage.setLoginAfterRegister();
//        loginPage.setInputEmail(email);
//        loginPage.setInputPassword("John123");
//        loginPage.clickLoginButton();
        loginPage.login(email, password);

        assertEquals("Logout", accountLogedPage.logoutButton.getText(), "Were not succesfully loggged in");
        accountLogedPage.setLogoutButton();

        assertEquals("Login", registerPage.loginButton2.getText(), "were not succesfully logout");

        registerPage.clickToLogin();

        assertEquals("https://practice.expandtesting.com/notes/app/login", driver.getCurrentUrl(), "Were not redirected");

    }

    @Test
    void registerWithEmptyData() {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        registerPage.clickToRegister();
        createAccount.clickRegister();
        Assertions.assertTrue(createAccount.errormessage.isDisplayed());
    }

    @Test
    void registerWithWrongData() {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        registerPage.clickToRegister();
        createAccount.insertEmail("useruser12345678901012345678@mail.com");
        createAccount.insertName("John");
        createAccount.insertPassword("John123");
        createAccount.insertConfirmPassword("John123");
        createAccount.clickRegister();
        assertEquals("An account already exists with the same email address", createAccount.existingAccountMessage.getText(), "Account already exist");

    }

}
