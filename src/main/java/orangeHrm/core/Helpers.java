package orangeHrm.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Helpers {

    public static void waitElementToBeVisible(WebDriver driver, WebElement element, int seconds){
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitTextToBe(WebDriver driver, By locator, String text, int seconds){
        new WebDriverWait(driver, seconds).until(ExpectedConditions.textToBe(locator, text));
    }

    public static void assertHeader(WebElement pageHeader, String expectedHeader){
        Assert.assertEquals(pageHeader.getText(), expectedHeader);
    }

    public static void assertElementDisplayed(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }
}
