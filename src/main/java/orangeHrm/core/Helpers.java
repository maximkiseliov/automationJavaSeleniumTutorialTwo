package orangeHrm.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helpers {

    public static void waitElementToBeVisible(WebDriver driver, WebElement element, int seconds){
        new WebDriverWait(driver, seconds).until(ExpectedConditions.visibilityOf(element));
    }
}
