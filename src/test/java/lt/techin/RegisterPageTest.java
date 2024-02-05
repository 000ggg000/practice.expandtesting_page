package lt.techin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RegisterPageTest extends BasePageTest {

    @Test
    void register() {
        RegisterPage registerPage = new RegisterPage(driver);
        CreateAccount createAccount = new CreateAccount(driver);
        AccountPage accountPage = new AccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        AccountLogedPage accountLogedPage = new AccountLogedPage(driver);
        registerPage.clickToRegister();
        createAccount.insertEmail("useruser12345678901012345678@mail.com");
        createAccount.insertName("John");
        createAccount.insertPassword("John123");
        createAccount.insertConfirmPassword("John123");
        createAccount.clickRegister();
        waiting();
        Assertions.assertEquals("User account created successfully", createAccount.registerAllert.getText(), "Account was not created");
        accountPage.setLoginAfterRegister();
        loginPage.setInputEmail("useruser12345678901012345678@mail.com");
        loginPage.setInputPassword("John123");
        loginPage.clickLoginButton();
        waiting();
        Assertions.assertEquals("Logout", accountLogedPage.logoutButton.getText(), "Were not succesfully loggged in");
        accountLogedPage.setLogoutButton();
        waiting();
        Assertions.assertEquals("Login", registerPage.loginButton2.getText(), "were not succesfully logout");
        waiting();
        registerPage.clickToLogin();
        waiting();
        Assertions.assertEquals("https://practice.expandtesting.com/notes/app/login", driver.getCurrentUrl(), "Were not redirected");

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
        waiting();
        Assertions.assertEquals("An account already exists with the same email address", createAccount.existingAccountMessage.getText(), "Account already exist");

    }

}
