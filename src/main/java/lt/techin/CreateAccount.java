package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CreateAccount extends BasePage {
    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "name")
    WebElement inputName;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(id = "confirmPassword")
    WebElement inputConfirmPasword;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-primary d-block w-100']")
    WebElement registerButton;

    @FindBy(css = "b")
    WebElement registerAllert;

    @FindBy(xpath = "//div[@class='invalid-feedback']")
    WebElement errormessage;

    @FindBy(xpath = "//div[@class='invalid-feedback']")
    List<WebElement> errormessages;

    @FindBy(xpath = "//div[@class='toast-body']")
    WebElement existingAccountMessage;

    public CreateAccount(WebDriver driver) {
        super(driver);
    }

    public void insertEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void insertName(String name) {
        inputName.sendKeys(name);
    }

    public void insertPassword(String password) {
        inputPassword.sendKeys(password);
    }

    public void insertConfirmPassword(String password) {
        inputConfirmPasword.sendKeys(password);
    }

    public void clickRegister() {
        registerButton.click();
    }

    boolean errorIsDisplayed() {

        return errormessage.isDisplayed();
    }

    public boolean isAlertWithTextVisible(String alertMessage) {

        return errormessages.stream().map(w -> w.getText().contains(alertMessage)).findAny().isPresent();
    }
}
