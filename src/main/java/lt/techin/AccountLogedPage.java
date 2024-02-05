package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountLogedPage extends BasePage{

    @FindBy(xpath = "//button[@class='btn btn-outline-danger']")
    WebElement logoutButton;

    public AccountLogedPage(WebDriver driver) {
        super(driver);
    }

    void setLogoutButton(){
        logoutButton.click();
    }
}
