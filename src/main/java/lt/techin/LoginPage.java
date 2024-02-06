package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage{
    @FindBy(id = "email")
    WebElement inputEmail;

    @FindBy(id = "password")
    WebElement inputPassword;

    @FindBy(xpath = "//button[@class='btn btn-lg btn-primary d-block w-100']")
    WebElement loginButton;

    @FindBy(xpath = "//a[@class='btn btn-outline-primary me-2 mb-2 mb-sm-0']")
    WebElement profileButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    void setInputEmail(String email){
        inputEmail.sendKeys(email);
    }
    void setInputPassword(String password){
        inputPassword.sendKeys(password);
    }
    void clickLoginButton(){
        loginButton.click();
    }

    void login(String email, String password){
        setInputEmail(email);
        setInputPassword(password);
        clickLoginButton();
    }

    void viewProfile(){
        profileButton.click();
    }
}
