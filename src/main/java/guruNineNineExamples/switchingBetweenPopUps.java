package guruNineNineExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static systemRelatedPackage.systemRelatedClass.CHROME_DRIVE_PATH;

public class switchingBetweenPopUps {
    public static void main(String[] args) {
        String chromeDriverPath = CHROME_DRIVE_PATH;

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        String alertMessage = "";

        driver.get("http://jsbin.com/usidix/1");
        driver.findElement(By.cssSelector("input[value=\"Go!\"]")).click();
        alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        System.out.println(alertMessage);
        driver.quit();

    }
}
