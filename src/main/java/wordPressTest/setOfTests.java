package wordPressTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static systemRelatedPackage.systemRelatedClass.*;

public class setOfTests {
    private static final String chromeDriverPath = CHROME_DRIVE_PATH;
    private static final String wordPressUserName = WORDPRESS_LOGIN;
    private static final String wordPressPassword = WORDPRESS_PASSWORD;
    private static final String INCOGNITO_MODE = "--incognito";
    private static final String MAXIMIZED_WINDOW = "--start-maximized";
    private WebDriver driver;


    @BeforeMethod
    public void setBrowser(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions chromeOptions = new ChromeOptions().addArguments(INCOGNITO_MODE, MAXIMIZED_WINDOW);

        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.get("https://maxkisqa.wordpress.com/");
    }

    @AfterMethod
    public void killBrowser(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void loginToWordPress() throws InterruptedException {
        driver.get(driver.getCurrentUrl() + "/login");

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='login__form-header-wrapper']")).getText(), "Log in to your account."); //Checking if we are at Login Page

        // Using XPath from below we are checking that required label is displayed before the input
        WebElement userNameInput = driver.findElement(By.xpath("//div[label[text()='Email Address or Username']]/input[@type='text' and @id='usernameOrEmail']")); //better than - input[@type='text' and @id='usernameOrEmail']
        userNameInput.sendKeys(wordPressUserName);

        WebElement continueBtn = driver.findElement(By.xpath("//button[@type='submit']")); //and contains(text(),'Continue') - only applicable for locale = en
        continueBtn.click();

        WebElement passwordInput = driver.findElement(By.xpath("//input[@type='password' and @id='password']"));
        passwordInput.sendKeys(wordPressPassword);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']")); //and contains(text(),'Log In')- only applicable for locale = en
        loginBtn.click();

        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(
                driver.findElement(
                        By.xpath("//img[@src='https://1.gravatar.com/avatar/1cc6ce658a6c24b63ae76fe841226bea?s=32&d=mm&r=G']")
                )));

        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='https://1.gravatar.com/avatar/1cc6ce658a6c24b63ae76fe841226bea?s=32&d=mm&r=G']")).isDisplayed()); //Checking if my avatar is displayed

        Thread.sleep(20000);
        WebElement meIconLink = driver.findElement(By.xpath("//a[@class='ab-item']"));
        meIconLink.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://wordpress.com/me");

        WebElement exitBtn = driver.findElement(By.xpath("//button[@type='button' and text()='Log Out']"));
        exitBtn.click();
    }
}
