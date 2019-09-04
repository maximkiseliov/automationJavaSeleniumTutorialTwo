package guruNineNineExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class uploadFile {
    public static void main(String[] args) {
        String chromeDriverPath = "C:\\Users\\maximk\\IdeaProjects\\chromedriver_win32\\chromedriver.exe";
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
