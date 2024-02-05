package lt.techin.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class TestUtils {

    private static final Logger log = getLogger(lookup().lookupClass());


    public static void takeScreenshot(WebDriver driver, String fileName) {

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String directoryPath = System.getProperty("user.dir")
                + File.separator + "test-output"
                + File.separator + "screenshots"
                + File.separator + getTodaysDate();

        String filePath = directoryPath
                + File.separator + getSystemTime()
                + "_" + fileName + ".png";

        try {
            Path directory = Paths.get(directoryPath);
            Files.createDirectories(directory);

            Path destination = Paths.get(filePath);
            Files.move(scrFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);
            log.info("Screenshot moved to {}", destination);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error moving screenshot: ", e);
        }

    }


    private static String getTodaysDate() {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());

    }


    private static String getSystemTime() {
        return DateTimeFormatter.ofPattern("HHmmssSSS").format(LocalDateTime.now());
    }

    public static String getComplexRandomMail(){
        String lowerCharacters = "abcdefghijklmnoprstuvwxyz";
        String upperCharacters = "ABCDEFGHIJKLMOPRSTUVWXYZ";
        String numberCharacters = "0123456789";
        return RandomStringUtils.random(3, lowerCharacters)
                + RandomStringUtils.random(3, numberCharacters)
                + RandomStringUtils.random(3, upperCharacters)
                + System.currentTimeMillis()
                + "@mail.com";
    }
}

