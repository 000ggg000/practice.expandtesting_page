package lt.techin;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class RegisterPage extends BasePage {




    public RegisterPage(WebDriver driver) {

        super(driver);
    }

    public void waiting() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }



}
