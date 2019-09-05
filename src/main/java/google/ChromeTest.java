package google;

import static systemRelatedPackage.systemRelatedClass.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ChromeTest {
    private static final String chromeDriverPath = CHROME_DRIVE_PATH;
    private List<String> chromeOptionArguments = new ArrayList<>();
    private ChromeOptions options;
    private WebDriver driver;

    @Test
    public void LaunchChrome_Method1() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        options = new ChromeOptions();
        chromeOptionArguments.add("--start-maximized");
        chromeOptionArguments.add("--incognito");
        options.addArguments(chromeOptionArguments);

        driver = new ChromeDriver(options);
        driver.get("http://www.google.com");

        WebElement searchBar = driver.findElement(By.xpath("//div[@class='a4bIc']/input[@name='q' and @title='Search']"));
        searchBar.sendKeys("Hello world!");
        WebElement searchButton = driver.findElement(By.xpath("//input[starts-with(@name, 'btnK')]"));
        searchButton.click();

    }
}
