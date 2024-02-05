package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{

@FindBy(css = "[class='text-decoration-none me-3']")
    WebElement loginAfterRegister;
    public AccountPage(WebDriver driver) {
        super(driver);
    }

    void setLoginAfterRegister(){
        loginAfterRegister.click();
    }
}
