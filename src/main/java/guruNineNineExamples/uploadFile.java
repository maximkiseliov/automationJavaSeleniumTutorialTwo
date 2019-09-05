package guruNineNineExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static systemRelatedPackage.systemRelatedClass.CHROME_DRIVE_PATH;


public class uploadFile {
    public static void main(String[] args) {
        String chromeDriverPath = CHROME_DRIVE_PATH;
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        String baseUrl = "http://demo.guru99.com/test/upload/";

        WebDriver driver = new ChromeDriver();

        driver.get(baseUrl);
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));

        // enter the file path onto the file-selection input field
        uploadElement.sendKeys("C:\\Users\\maximk\\Desktop\\New Text Document.txt");

        // check the "I accept the terms of service" check box
        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
    }
}
