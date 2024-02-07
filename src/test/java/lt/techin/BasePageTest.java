package lt.techin;

import lt.techin.utils.FailureWatcher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
@ExtendWith(FailureWatcher.class)
public class BasePageTest {
    private static final Logger log = getLogger(lookup().lookupClass());
    WebDriver driver;

    public BasePageTest() {
    }

    @BeforeEach
    void setup() {

        this.driver = new ChromeDriver();
        this.driver.get("https://practice.expandtesting.com/notes/app");
        this.driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        FailureWatcher.driver = driver;
    }

    @AfterEach
    void closeDown() {

        this.driver.quit();
        log.info("WebDriver closed");
    }

}

//    public static void waiting() {
//        try {
//            Thread.sleep(3000L);
//        } catch (InterruptedException var1) {
//        }
//
//    }
//}
