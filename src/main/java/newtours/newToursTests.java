package newtours;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static systemRelatedPackage.systemRelatedClass.CHROME_DRIVE_PATH;

public class newToursTests {
    private static final String chromeDriverPath = CHROME_DRIVE_PATH;
    private static final String newToursURL = "http://www.newtours.demoaut.com/";
    private static final String newToursHomePageTitle = "Welcome: Mercury Tours";
    private static final String incognitoMode = "--incognito";
    private static final String maximizeWindow = "--start-maximized";
    private WebDriver driver;
    private static String userName;
    private static String password;

    @BeforeMethod
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(incognitoMode, maximizeWindow);

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // waits max 5 seconds for element to appear
        driver.manage().timeouts().pageLoadTimeout(180, TimeUnit.SECONDS); // waits max 180 seconds to load the page
        driver.get(newToursURL);

        // Making sure that we are at expected home page
        Assert.assertEquals(driver.getTitle(), newToursHomePageTitle);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(4000);
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }

    @Test
    public void userCreateTest(){
        String firstName = "Maxim";
        String lastName = "Lastname";
        String userName = "mlastname@test.net";
        String password = "test123";

        WebElement registerLink = driver.findElement(By.xpath("//td/a[text()='REGISTER']"));
        registerLink.click();

        // Making sure that we are at expected page
        Assert.assertEquals(driver.getTitle(), "Register: Mercury Tours");
        // Double check by checking that expected image is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/images/masts/mast_register.gif']")).isDisplayed());

        // Contact information section
        WebElement firstNameInput = driver.findElement(By.xpath("//input[@name='firstName']"));
        firstNameInput.sendKeys("Maxim");

        WebElement lastNameInput = driver.findElement(By.xpath("//input[@name='lastName']"));
        lastNameInput.sendKeys("Lastname");

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        phoneInput.sendKeys("99999999");

        WebElement emailInput = driver.findElement(By.xpath("//input[@name='userName']"));
        emailInput.sendKeys("mlastname@test.com");

        // Mailing information section
        WebElement addressFirstInput = driver.findElement(By.xpath("//input[@name='address1']"));
        addressFirstInput.sendKeys("Address Line 1");

        WebElement addressSecondInput = driver.findElement(By.xpath("//input[@name='address2']"));
        addressSecondInput.sendKeys("Address Line 2");

        WebElement cityInput = driver.findElement(By.xpath("//input[@name='city']"));
        cityInput.sendKeys("Mycity");

        WebElement stateProvinceInput = driver.findElement(By.xpath("//input[@name='state']"));
        stateProvinceInput.sendKeys("State");

        WebElement postalCodeInput = driver.findElement(By.xpath("//input[@name='postalCode']"));
        postalCodeInput.sendKeys("PO2020");

        Select countryDropDown = new Select(driver.findElement(By.xpath("//select[@name='country']")));
        countryDropDown.selectByVisibleText("RUSSIA");

        // User information section
        WebElement userNameInput = driver.findElement(By.xpath("//input[@name='email']"));
        userNameInput.sendKeys(userName);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys(password);

        WebElement passwordConfirmInput = driver.findElement(By.xpath("//input[@name='confirmPassword']"));
        passwordConfirmInput.sendKeys(password);

        WebElement submitBtn = driver.findElement(By.xpath("//input[@name='register']"));
        submitBtn.click();

        new WebDriverWait(driver, 30) // Wait max 30 seconds for element with text Dear firstName lastName,
                .until(ExpectedConditions.visibilityOf(
                        driver.findElement(
                                //By.xpath("//b[contains(text(), 'Dear " + firstName + " " + lastName + ",')]"))));
                                By.xpath("//b[contains(text(), 'Dear')]"))));


        WebElement dearMsg = driver.findElement(By.xpath("//b[contains(text(), 'Dear')]"));
        Assert.assertEquals(dearMsg.getText().trim(), "Dear " + firstName + " " + lastName + ",");

        WebElement noteMsg = driver.findElement(By.xpath("//b[contains(text(), 'Note:')]"));
//        Assert.assertEquals(noteMsg.getText().trim(), "Note: Your user name is "+ userName + ".");
        Assert.assertTrue(StringUtils.contains(noteMsg.getText(), userName));

        newToursTests.userName = userName; // Assign local variable userName to Class level variable userName
        newToursTests.password = password; // Assign local variable password to Class level variable password
    }

    @Test(dependsOnMethods = "userCreateTest")
    public void userSignOnTest(){
        WebElement signOnLink = driver.findElement(By.xpath("//a[text() = 'SIGN-ON']"));
        signOnLink.click();

        Assert.assertEquals(driver.getTitle(), "Sign-on: Mercury Tours");
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/images/masts/mast_signon.gif']")).isDisplayed());

        WebElement userNameInput = driver.findElement(By.xpath("//input[@name='userName']"));
        userNameInput.sendKeys(newToursTests.userName);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys(newToursTests.password);

        WebElement submitBtn = driver.findElement(By.xpath("//input[@name='login' and @value='Login']"));
        submitBtn.click();

        // Check that expected image is displayed
        Assert.assertTrue(driver.findElement(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")).isDisplayed());
    }
}
