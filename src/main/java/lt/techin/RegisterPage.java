package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;

public class RegisterPage extends BasePage {

    @FindBy(xpath = "//a[@class = 'btn btn-outline-secondary btn-lg px-4']")
    WebElement createAnAccount;

    @FindBy(css = "[class='btn btn-primary btn-lg px-4 me-md-2']")
    WebElement loginButton2;


    public RegisterPage(WebDriver driver) {

        super(driver);
    }

    public void waiting() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }


    public void clickToRegister() {
        createAnAccount.click();
    }

    public void clickToLogin() {
        loginButton2.click();
    }
}
