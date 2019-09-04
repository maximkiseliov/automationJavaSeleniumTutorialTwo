package guruNineNineExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class pageWithFrames {

    public static void main(String[] args) {
        String chromeDriverPath = "C:\\Users\\maximk\\IdeaProjects\\chromedriver_win32\\chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();
        driver.get("http://demo.guru99.com/selenium/deprecated.html");
        driver.switchTo().frame("classFrame");
        driver.findElement(By.linkText("Deprecated")).click();
        driver.close();
    }

}
