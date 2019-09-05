package parkingCalculator;

import static systemRelatedPackage.systemRelatedClass.*;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class parkingCalculator {
    private static final String chromeDriverPath = CHROME_DRIVE_PATH;
    private static final String parkingCalculatorUrl = "http://www.shino.de/parkcalc/";
    private static final String incognitoMode = "--incognito";
    private static final String maximizeWindow = "--start-maximized";
    private WebDriver driver;

    @BeforeMethod
    public void startBrowser(){
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments(incognitoMode, maximizeWindow);

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }

//    @DataProvider(name = "parkinglotdata")
//    public Object[][] provideParkingLotDaata(){
//        Map<String, String> map = readParkingLotData();
//        return new Object[][] {{map}};
//    }

    @Test
    public void firstTest(){
        driver.get(parkingCalculatorUrl);
        Assert.assertEquals(driver.getTitle(), "Parking Cost Calculator"); // check if we are at expected page
        WebElement chooseALot = driver.findElement(By.xpath("//tr[td[contains(text(), 'Choose a Parking Lot')]]/td/select[@id='ParkingLot']"));
//        chooseALot.sendKeys("Economy Parking"); // NOT the right way of working with select

        Select chooseALotSelect = new Select(chooseALot); // correct way
        chooseALotSelect.selectByVisibleText("Economy Parking");

        // Entry date and time
        WebElement entryDateField = driver.findElement(By.xpath("//tr[td[contains(text(), 'input entry')]]//input[@id='StartingDate']"));
        WebElement entryTimeField = driver.findElement(By.xpath("//tr[td[contains(text(), 'input entry')]]//input[@id='StartingTime']"));
        List<WebElement> startTimeAmPmRadioButtons = driver.findElements(By.xpath("//tr[td[contains(text(), 'input entry')]]//input[@type='radio']"));

        entryDateField.clear();
        entryDateField.sendKeys("09/01/2019");
        entryTimeField.clear();
        entryTimeField.sendKeys("9:20");
        selectTimeAmPmRadioButton(startTimeAmPmRadioButtons, "PM");

        // Leaving date and time
        WebElement leavingDateField = driver.findElement(By.xpath("//tr[td[contains(text(), 'input leaving')]]//input[@id='LeavingDate']"));
        WebElement leavingTimeField = driver.findElement(By.xpath("//tr[td[contains(text(), 'input leaving')]]//input[@id='LeavingTime']"));
        List<WebElement> leaveTimeAmPmRadioButtons = driver.findElements(By.xpath("//tr[td[contains(text(), 'input leaving')]]//input[@type='radio']"));

        leavingDateField.clear();
        leavingDateField.sendKeys("09/02/2019");
        leavingTimeField.clear();
        leavingTimeField.sendKeys("7:00");
        selectTimeAmPmRadioButton(leaveTimeAmPmRadioButtons, "PM");

        WebElement calculateButton = driver.findElement(By.xpath("//input[@name='Submit' and @value='Calculate']"));
        calculateButton.click();

        WebElement estimatedParkingCosts = driver.findElement(By.xpath("//tr[contains(., 'estimated Parking costs')]/td//b[contains(text(), '$')]"));
        WebElement parkingDays = driver.findElement(By.xpath("//tr[contains(., 'estimated Parking costs')]/td//b[contains(text(), 'Days')]"));

        Assert.assertEquals(estimatedParkingCosts.getText(), "$ 9.00");
        Assert.assertEquals(parkingDays.getText().trim(), "(0 Days, 21 Hours, 40 Minutes)");
    }

    public void selectTimeAmPmRadioButton(List<WebElement> timeAmPmRadioButtons, String selectValue){
        for (WebElement timeAmPmRadioButton : timeAmPmRadioButtons) {
            String paramValue = timeAmPmRadioButton.getAttribute("value");
            if (StringUtils.equals(selectValue, paramValue)){
                timeAmPmRadioButton.click();
                break;
            }
        }
    }

//    public Map<String, List> readParkingLotData(){
//        Map<String, String> map
//    }
}
