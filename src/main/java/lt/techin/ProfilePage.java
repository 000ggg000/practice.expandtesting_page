package lt.techin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProfilePage extends BasePage{
    @FindBy(xpath = "//input[@name='email']")
    WebElement profileEmailInput;

    @FindBy(xpath = "//input[@name='name']")
    WebElement profileNameInput;
    public ProfilePage(WebDriver driver) {
        super(driver);
    }

     String  registrationAuthentificationEmail(){
         Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
         wait.until(d -> profileEmailInput.isDisplayed());

        return profileEmailInput.getAttribute("value");

    }
    String  registrationAuthentificationName(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> profileNameInput.isDisplayed());

        return profileNameInput.getAttribute("value");

    }
}
