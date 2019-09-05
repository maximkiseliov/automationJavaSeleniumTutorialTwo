package guruNineNineExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static systemRelatedPackage.systemRelatedClass.CHROME_DRIVE_PATH;

public class pageWithFrames {

    public static void main(String[] args) {
        String chromeDriverPath = CHROME_DRIVE_PATH;

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/selenium/deprecated.html");
        driver.switchTo().frame("classFrame");
        driver.findElement(By.linkText("Deprecated")).click();
        driver.close();
    }

}
