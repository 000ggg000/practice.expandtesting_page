package lt.techin;

import lt.techin.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.slf4j.Logger;

import static java.lang.invoke.MethodHandles.lookup;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
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
        ProfilePage profilePage = new ProfilePage(driver);
        registerPage.clickToRegister();
        String email = TestUtils.getComplexRandomMail();
        log.trace("Generated mail {}", email);
        String password = "John123";
        String name = "John";
        createAccount.insertEmail(email);
        createAccount.insertName(name);
        createAccount.insertPassword(password);
        createAccount.insertConfirmPassword(password);
        createAccount.clickRegister();
        assertEquals("User account created successfully", createAccount.registerAllert.getText(), "Account was not created");
        log.info("Account created with email {}", email);
        TestUtils.takeScreenshot(driver, "account Created");
        accountPage.setLoginAfterRegister();
//        loginPage.setInputEmail(email);
//        loginPage.setInputPassword("John123");
//        loginPage.clickLoginButton();
        loginPage.login(email, password);
        loginPage.viewProfile();
        assertEquals(name, profilePage.registrationAuthentificationName(), "User logged in to another account name");
        assertEquals(email.toLowerCase(), profilePage.registrationAuthentificationEmail(), "User logged in to another account email");
        log.debug("The user successfully logged into the page");
        assertEquals("Logout", accountLogedPage.logoutButton.getText(), "Were not successfully logged in");
        TestUtils.takeScreenshot(driver, "Logged out from the account.");
        accountLogedPage.setLogoutButton();
        assertEquals("Login", registerPage.loginButton2.getText(), "were not successfully logout");
        log.debug("The user successfully logged out from the page");
        registerPage.clickToLogin();
        assertEquals("https://practice.expandtesting.com/notes/app/login", driver.getCurrentUrl(), "Were not redirected");
        log.info("The user was redirected to the login page");

    }

    @Test
    void registerWithEmptyData() {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        registerPage.clickToRegister();
        createAccount.clickRegister();
        assertTrue(createAccount.errorIsDisplayed());
        log.debug("Error message appeared.");
        TestUtils.takeScreenshot(driver, "Error messages appeared.");
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
        log.info("Account already exist message.");
        TestUtils.takeScreenshot(driver, "Error messages appeared.");
    }

    @ParameterizedTest
    @CsvFileSource(files = "src/main/resources/logins.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromFile(String email, String name, String password, String confirm, String errorMessage) {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountLogedPage accountLogedPage = new AccountLogedPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        registerPage.clickToRegister();
        log.info("{} {} {} {} {}", email, name, password, confirm, errorMessage);
        createAccount.insertEmail(email);
        createAccount.insertName(name);
        createAccount.insertPassword(password);
        createAccount.insertConfirmPassword(confirm);
        createAccount.clickRegister();
        assertTrue(createAccount.isAlertWithTextVisible(errorMessage), "Account was not created: " + errorMessage);


    }

}
